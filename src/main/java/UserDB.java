import java.sql.*;
import java.util.ArrayList;

public class UserDB extends Database {
    private boolean check = false;

    public boolean insertUser(User user) {
        connect();
        String sql = "INSERT INTO Usuario (nome, cpf) VALUES (?, ?);";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, user.getNome());
            pst.setString(2, user.getCpf());
            pst.execute();
            check = true;
        } catch (SQLException error) {
            System.out.println("Operation Error: " + error.getMessage());
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException error) {
                System.out.println("Connection Closure Error: " + error.getMessage());
            }
        }
        return check;
    }

    public ArrayList<User> selectUser() {
        connect();
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Usuario;";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                User user = new User(result.getString("nome"), result.getString("cpf"));
                user.setId(result.getInt("id"));
                System.out.println("id = " + result.getInt("id"));
                System.out.println("nome = " + result.getString("nome"));
                System.out.println("cpf = " + result.getString("cpf"));
                System.out.println("--------------------");
                users.add(user);
            }
        } catch (SQLException error) {
            System.out.println("Operation Error: " + error.getMessage());
        } finally {
            try {
                connection.close();
                statement.close();
                result.close();
            } catch (SQLException error) {
                System.out.println("Connection Closure Error: " + error.getMessage());
            }
        }
        return users;
    }

    public boolean updateUser(int id, String nome) {
        connect();
        String sql = "UPDATE Usuario SET nome = ? WHERE id = ?;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, nome);
            pst.setInt(2, id);
            pst.execute();
            check = true;
        } catch (SQLException error) {
            System.out.println("Operation Error: " + error.getMessage());
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException error) {
                System.out.println("Connection Closure Error: " + error.getMessage());
            }
        }
        return check;
    }

    public boolean deleteUser(int id) {
        connect();
        String sql = "DELETE FROM Usuario WHERE id = ?;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id);
            pst.execute();
            check = true;
        } catch (SQLException error) {
            System.out.println("Operation Error: " + error.getMessage());
        } finally {
            try {
                connection.close();
                pst.close();
            } catch (SQLException error) {
                System.out.println("Connection Closure Error: " + error.getMessage());
            }
        }
        return check;
    }
}
