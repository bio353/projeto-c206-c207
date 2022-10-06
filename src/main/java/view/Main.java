package view;

import java.util.ArrayList;

import controller.AlunoDB;
import controller.CursoDB;
import model.Aluno;
import model.Curso;

public final class Main {
    public static void main(String[] args) {
        // Conectando ...
        AlunoDB alunoDB = new AlunoDB();
        CursoDB cursoDB = new CursoDB();
        alunoDB.connect();

        // Outros (Irrelevante) ...
        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Curso> cursos = new ArrayList<>();
        boolean check;

        // Instanciando Alunos ...
        Aluno aluno1 = new Aluno("Fulano", 33);
        Aluno aluno2 = new Aluno("Beltrano", 45);

        // Instanciando Cursos ...
        Curso curso1 = new Curso("Engenharia de Software");
        Curso curso2 = new Curso("Engenharia de Computacao");

        // Inserindo Alunos ...
        check = alunoDB.insertAluno(aluno1);
        check = alunoDB.insertAluno(aluno2);

        // Inserindo Cursos ...
        check = cursoDB.insertCurso(curso1);
        check = cursoDB.insertCurso(curso2);

        // Atribuindo Curso aos Alunos ...
        check = alunoDB.updateFkAluno(33, 1);
        check = alunoDB.updateFkAluno(45, 2);

        // Selecionando Alunos ...
        System.out.println("\nAlunos");
        alunos = alunoDB.selectAluno();

        // Selecionando Cursos ...
        System.out.println("\nCursos");
        cursos = cursoDB.selectCurso();

        // Atualizando 1 Aluno ...
        check = alunoDB.updateAluno(1, "BiO");

        // Deletando 1 Curso ...
        check = cursoDB.deleteCurso(2);

        // Selecionando Alunos Novamente ...
        System.out.println("\nAlunos");
        alunos = alunoDB.selectAluno();

        // Selecionando Cursos Novamente ...
        System.out.println("\nCursos");
        cursos = cursoDB.selectCurso();
    }
}
