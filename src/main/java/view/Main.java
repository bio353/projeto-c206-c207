package view;

import java.util.List;

import controller.AlunoDB;
import controller.CursoDB;
import model.Aluno;
import model.Curso;

public final class Main {
    public static void executeOperation(String operation, boolean check) {
        System.out.print(operation + ": ");
        if (check) {
            System.out.println("Success! =)");
        } else {
            System.out.println("Fail! =(");
        }
        System.out.print("\n");
    }

    public static void showCourses(List<Curso> courses) {
        System.out.println("Alunos");
        for (Curso course : courses) {
            System.out.println("--------------------");
            System.out.println("id = " + course.getId());
            System.out.println("nome = " + course.getNome());
        }
        System.out.print("\n");
    }

    public static void showStudents(List<Aluno> students) {
        System.out.println("Cursos");
        for (Aluno student : students) {
            System.out.println("--------------------");
            System.out.println("id = " + student.getId());
            System.out.println("nome = " + student.getNome());
            System.out.println("matricula = " + student.getMatricula());
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        // Conectando ...
        AlunoDB alunoDB = new AlunoDB();
        CursoDB cursoDB = new CursoDB();
        alunoDB.connect();
        cursoDB.connect();

        // Instanciando Alunos ...
        Aluno aluno1 = new Aluno("Fulano", 33);
        Aluno aluno2 = new Aluno("Beltrano", 45);

        // Instanciando Cursos ...
        Curso curso1 = new Curso("Engenharia de Software");
        Curso curso2 = new Curso("Engenharia de Computacao");

        // Variáveis Auxiliares ...
        List<Aluno> alunos;
        List<Curso> cursos;

        // Deletando Tudo (P/ Fins de Testes) ...
        alunos = alunoDB.selectAluno();
        for (Aluno aluno : alunos) {
            alunoDB.deleteAluno(aluno.getId());
        }
        cursos = cursoDB.selectCurso();
        for (Curso curso : cursos) {
            cursoDB.deleteCurso(curso.getId());
        }

        // Inserindo Alunos ...
        executeOperation("Insert #1", alunoDB.insertAluno(aluno1));
        executeOperation("Insert #2", alunoDB.insertAluno(aluno2));

        // Inserindo Cursos ...
        executeOperation("Insert #3", cursoDB.insertCurso(curso1));
        executeOperation("Insert #4", cursoDB.insertCurso(curso2));

        // Atribuindo Curso aos Alunos ...
        executeOperation("Update FK #1", alunoDB.updateFkAluno(33, 1));
        executeOperation("Update FK #2", alunoDB.updateFkAluno(45, 2));

        // Selecionando Alunos ...
        alunos = alunoDB.selectAluno();
        showStudents(alunos);

        // Selecionando Cursos ...
        cursos = cursoDB.selectCurso();
        showCourses(cursos);

        // Atualizando 1 Aluno ...
        executeOperation("Update #1", alunoDB.updateAluno(1, "BiO"));

        // Deletando 1 Curso ...
        executeOperation("Delete #1", cursoDB.deleteCurso(2));

        // Selecionando Alunos Novamente ...
        alunos = alunoDB.selectAluno();
        showStudents(alunos);

        // Selecionando Cursos Novamente ...
        cursos = cursoDB.selectCurso();
        showCourses(cursos);
    }
}
