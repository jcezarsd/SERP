package com.example.aluno.projetores.models;

import android.content.Context;

import com.example.aluno.projetores.fragments.ProfessoresFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by aluno on 30/01/17.
 */
public class Professor implements Serializable {

    private Integer id;
    private String nome;
    private String matricula;
    private String departamento;

    public Professor(String nome, String matricula, String departamento, Integer id) {
        this.nome = nome;
        this.matricula = matricula;
        this.departamento = departamento;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public static Professor findById (ArrayList<Professor> professores, Integer id) {
        for (Professor professor : professores) {
            if (professor.getId().equals(id)) {
                return professor;
            }
        }
        return null;
    }


    public static Professor findByNome (ArrayList<Professor> professores, String nome) {
        for (Professor professor : professores) {
            if (professor.getNome().equals(nome)) {
                return professor;
            }
        }
        return null;
    }

    public static void cadastrarProfessor(Context context, Professor professor) {

        ProfessoresFragment professoresFragment = new ProfessoresFragment();
        ArrayList<Professor> professores = professoresFragment.buscarProfessores(context);

        if (professores == null) {
            professores = new ArrayList<>();
        }

        Professor newProfessor;

        if(professores.size() > 0) {

            newProfessor = new Professor(professor.getNome(),
                    professor.getMatricula(),
                    professor.getDepartamento(),
                    (professores.get(professores.size()-1).getId()) +1);
        } else {
            newProfessor = new Professor(professor.getNome(),
                    professor.getMatricula(),
                    professor.getDepartamento(),
                    professor.getId());
        }

        professores.add(newProfessor);

        professoresFragment.save(professores, context);

    }

}
