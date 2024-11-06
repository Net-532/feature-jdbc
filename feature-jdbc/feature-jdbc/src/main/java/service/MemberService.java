package service;

import model.Household;
import model.Member;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MemberService {
    public void addMemberToHousehold(Household household, int memberId, String name, int age) {
        String sql = "INSERT INTO members (id, name, age, household_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, memberId);
                stmt.setString(2, name);
                stmt.setInt(3, age);
                stmt.setInt(4, household.getId());
                stmt.executeUpdate();
                conn.commit();
                System.out.println("Члена сім'ї успішно додано.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Не вдалося додати члена сім'ї.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeMemberFromHousehold(Household household, int memberId) {
        String sql = "DELETE FROM members WHERE id = ? AND household_id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, memberId);
                stmt.setInt(2, household.getId());
                stmt.executeUpdate();
                conn.commit();
                System.out.println("Члена сім'ї успішно видалено.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Не вдалося видалити члена сім'ї.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
