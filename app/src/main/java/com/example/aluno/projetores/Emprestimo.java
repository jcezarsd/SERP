package com.example.aluno.projetores;

import java.util.Date;

/**
 * Created by aluno on 06/02/17.
 */
public class Emprestimo {

    private Integer id;
    private Integer idProjetor;
    private Integer idProfessor;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Boolean isAtivo;

    public Emprestimo(Integer idProjetor, Integer idProfessor, Date dataEmprestimo) {
        this.idProjetor = idProjetor;
        this.idProfessor = idProfessor;
        this.dataEmprestimo = dataEmprestimo;
        this.isAtivo = true;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdProjetor() {
        return idProjetor;
    }

    public Integer getIdProfessor() {
        return idProfessor;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void devolver() {
        this.dataDevolucao = new Date();
        this.isAtivo = false;
    }
}
