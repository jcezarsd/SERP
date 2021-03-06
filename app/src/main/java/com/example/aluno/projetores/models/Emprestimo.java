package com.example.aluno.projetores.models;

import android.content.Context;

import com.example.aluno.projetores.fragments.EmprestimosFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by aluno on 06/02/17.
 */
public class Emprestimo implements Serializable{

    private Integer id;
    private Integer idProjetor;
    private Integer idProfessor;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Boolean isAtivo;

    public Emprestimo(Integer idProjetor, Integer idProfessor, Date dataEmprestimo, Date dataDevolucao, Integer id) {
        this.id = id;
        this.idProjetor = idProjetor;
        this.idProfessor = idProfessor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
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

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void devolver() {

        this.isAtivo = false;
        this.setDataDevolucao(new Date());
    }

    public static Emprestimo findByIdProjetor (Context context, Integer idProjetor) {

        EmprestimosFragment emprestimosFragment = new EmprestimosFragment();
        ArrayList<Emprestimo> emprestimos = emprestimosFragment.buscarEmprestimos(context);

        for (Emprestimo emprestimo: emprestimos) {

            if(emprestimo.getIdProjetor().equals(idProjetor) && emprestimo.getAtivo()) {
                return emprestimo;
            }
        }
        return null;
    }

    public static void finalizarEmprestimo(Context context, Emprestimo emprestimo) {

        EmprestimosFragment emprestimosFragment = new EmprestimosFragment();
        ArrayList<Emprestimo> emprestimos = emprestimosFragment.buscarEmprestimos(context);

        for (Emprestimo emprestimoFinalizado: emprestimos) {
            if (emprestimoFinalizado.getId().equals(emprestimo.getId())) {
                emprestimoFinalizado.devolver();
            }
        }

        emprestimosFragment.save(emprestimos, context);

    }

    public static void cadastrarEmprestimo(Context context, Emprestimo emprestimo) {

        EmprestimosFragment emprestimosFragment = new EmprestimosFragment();
        ArrayList<Emprestimo> emprestimos = emprestimosFragment.buscarEmprestimos(context);

        if (emprestimos == null) {
            emprestimos = new ArrayList<>();
        }


        Emprestimo newEmprestimo;

        if(emprestimos.size() > 0) {

            newEmprestimo = new Emprestimo(emprestimo.getIdProjetor(),
                    emprestimo.getIdProfessor(),
                    emprestimo.getDataEmprestimo(),
                    emprestimo.getDataDevolucao(),
                    (emprestimos.get(emprestimos.size() - 1).getId()) + 1);
        } else {
            newEmprestimo = new Emprestimo(emprestimo.getIdProjetor(),
                    emprestimo.getIdProfessor(),
                    emprestimo.getDataEmprestimo(),
                    emprestimo.getDataDevolucao(),
                    emprestimo.getId());
        }

        emprestimos.add(newEmprestimo);

        emprestimosFragment.save(emprestimos, context);

    }

}
