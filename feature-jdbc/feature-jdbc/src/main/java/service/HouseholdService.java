package service;

import model.Household;
import util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseholdService {
    public void createHousehold(int id, String address) {
        String sql = "INSERT INTO households (id, address) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, address);
                stmt.executeUpdate();
                conn.commit();
                System.out.println("Домогосподарство успішно створено.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Не вдалося створити домогосподарство.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Household getHouseholdById(int id) {
        String sql = "SELECT * FROM households WHERE id = ?";
        Household household = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                household = new Household(rs.getInt("id"), rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return household;
    }

    public void deleteHousehold(int id) {
        String deleteMembersSql = "DELETE FROM members WHERE household_id = ?";
        String deleteHouseholdSql = "DELETE FROM households WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement deleteMembersStmt = conn.prepareStatement(deleteMembersSql);
                 PreparedStatement deleteHouseholdStmt = conn.prepareStatement(deleteHouseholdSql)) {
                deleteMembersStmt.setInt(1, id);
                deleteMembersStmt.executeUpdate();
                deleteHouseholdStmt.setInt(1, id);
                deleteHouseholdStmt.executeUpdate();
                conn.commit();
                System.out.println("Домогосподарство та члени успішно видалені.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Не вдалося видалити домогосподарство.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Household> getAllHouseholds() {
        List<Household> households = new ArrayList<>();
        String sql = "SELECT * FROM households";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                households.add(new Household(rs.getInt("id"), rs.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return households;
    }
}
