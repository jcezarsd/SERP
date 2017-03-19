package com.example.aluno.projetores.models;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.content.Context.*;

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
            if (professor.id.equals(id)) {
                return professor;
            }
            ;
        }
        return null;
    }

}
