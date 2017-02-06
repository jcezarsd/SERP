package com.example.aluno.projetores;

/**
 * Created by aluno on 30/01/17.
 */
public class Professor {

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

}
