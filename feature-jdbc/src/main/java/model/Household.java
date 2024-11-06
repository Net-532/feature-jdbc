package model;

import java.util.ArrayList;
import java.util.List;

public class Household {
    private int id;
    private String address;
    private List<Member> members = new ArrayList<>();

    public Household(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public List<Member> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "Домогосподарство{" +
                "id=" + id +
                ", адресса='" + address + '\'' +
                ", Члени сімї=" + members +
                '}';
    }
}
