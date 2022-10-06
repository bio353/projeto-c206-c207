package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Aluno;

public class AlunoDB extends Database {
    private boolean check = false;

    public boolean insertAluno(Aluno aluno) {
        connect();
        String sql = "INSERT INTO Aluno (nome, matricula) VALUES (?, ?);";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, aluno.getNome());
            pst.setInt(2, aluno.getMatricula());
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

    public ArrayList<Aluno> selectAluno() {
        connect();
        ArrayList<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM Aluno;";
        try {
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                Aluno aluno = new Aluno(result.getString("nome"), result.getInt("matricula"));
                aluno.setId(result.getInt("id"));
                System.out.println("id = " + result.getInt("id"));
                System.out.println("nome = " + result.getString("nome"));
                System.out.println("matricula = " + result.getString("matricula"));
                System.out.println("--------------------");
                alunos.add(aluno);
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
        return alunos;
    }

    public boolean updateAluno(int id, String nome) {
        connect();
        String sql = "UPDATE Aluno SET nome = ? WHERE id = ?;";
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

    public boolean updateFkAluno(int matricula, int id_curso) {
        connect();
        String sql = " UPDATE Aluno SET fk_id_curso = ? WHERE matricula = ?;";
        try {
            pst = connection.prepareStatement(sql);
            pst.setInt(1, id_curso);
            pst.setInt(2, matricula);
            pst.execute();
            check = true;
        } catch (SQLException error) {
            System.out.println("Operation Error: " + error.getMessage());
            check = false;
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

    public boolean deleteAluno(int id) {
        connect();
        String sql = "DELETE FROM Aluno WHERE id = ?;";
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
