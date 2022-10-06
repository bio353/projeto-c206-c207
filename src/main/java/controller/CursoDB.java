package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Curso;

public class CursoDB extends Database {
    private boolean check = false;

    public boolean insertCurso(Curso curso) {
        connect();
        String sql = "INSERT INTO Curso (nome) VALUES (?);";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, curso.getNome());
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

    public ArrayList<Curso> selectCurso() {
        connect();
        ArrayList<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM Curso;";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                Curso curso = new Curso(result.getString("nome"));
                curso.setId(result.getInt("id"));
                System.out.println("id = " + result.getInt("id"));
                System.out.println("nome = " + result.getString("nome"));
                System.out.println("--------------------");
                cursos.add(curso);
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
        return cursos;
    }

    public boolean updateCurso(int id, String nome) {
        connect();
        String sql = "UPDATE Curso SET nome = ? WHERE id = ?;";
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

    public boolean deleteCurso(int id) {
        connect();
        String sql = "DELETE FROM Curso WHERE id = ?;";
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
