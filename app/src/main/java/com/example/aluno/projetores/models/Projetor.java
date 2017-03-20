package com.example.aluno.projetores.models;

import android.content.Context;

import com.example.aluno.projetores.fragments.ProjetoresFragment;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Julio_Cezar on 30/01/17.
 */
public class Projetor implements Serializable {

    public static final String PROJETOR_DISPONIVEL = "Disponível";
    public static final String PROJETOR_EMPRESTADO = "Emprestado";
    public static final String PROJETOR_ESTRAGADO = "Com defeito";

    private Integer id;
    private String marca;
    private String modelo;
    private Integer situacao; //0-Disponivel; 1-Emprestado; 2-Estragado
    private String numPatrimonio;

    public Projetor(String marca, String modelo, Integer situacao, String numPatrimonio, Integer id) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.situacao = situacao;
        this.numPatrimonio = numPatrimonio;
    }

    public Integer getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getSituacao() {
        switch (situacao) {
            case 0:
                return PROJETOR_DISPONIVEL;
            case 1:
                return PROJETOR_EMPRESTADO;
            case 2:
                return PROJETOR_ESTRAGADO;
            default:
                return "";
        }
    }

    public String getNumPatrimonio() {
        return numPatrimonio;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public static Projetor findById (ArrayList<Projetor> projetores, Integer id){
        for (Projetor projetor: projetores) {
            if (projetor.id.equals(id)){
                return projetor;
            }
        }
        return null;
    }

    public static Projetor findByPatrimonio (Context context, String numPatrimonio){

        ProjetoresFragment projetoresFragment = new ProjetoresFragment();
        ArrayList<Projetor> projetores = projetoresFragment.buscarProjetores(context);

        for (Projetor projetor: projetores) {
            if (projetor.numPatrimonio.equals(numPatrimonio)){
                return projetor;
            }
        }
        return null;
    }

    public static void cadastrarProjetor(Context context, Projetor projetor) {

        ProjetoresFragment projetoresFragment = new ProjetoresFragment();
        ArrayList<Projetor> projetores = projetoresFragment.buscarProjetores(context);

        Projetor newProjetor;

        if (projetores == null) {
            projetores = new ArrayList<>();
        }

        if(projetores.size() > 0) {

            newProjetor = new Projetor(projetor.getMarca(),
                    projetor.getModelo(),
                    0,
                    projetor.getNumPatrimonio(),
                    (projetores.get(projetores.size()-1).getId()) +1);
        } else {
            newProjetor = new Projetor(projetor.getMarca(),
                    projetor.getModelo(),
                    0,
                    projetor.getNumPatrimonio(),
                    projetor.getId());
        }

        projetores.add(newProjetor);

        projetoresFragment.save(projetores, context);



    }

    public static void emprestarProjetor(Context context, Projetor projetor) {

        ProjetoresFragment projetoresFragment = new ProjetoresFragment();
        ArrayList<Projetor> projetores = projetoresFragment.buscarProjetores(context);

        for (Projetor projetorCadastrado: projetores) {
            if (projetorCadastrado.getNumPatrimonio().equals(projetor.getNumPatrimonio())) {
                projetorCadastrado.setSituacao(1);
            }
        }

        projetoresFragment.save(projetores, context);

    }

    public static void devolverProjetor(Context context, Projetor projetor) {

        ProjetoresFragment projetoresFragment = new ProjetoresFragment();
        ArrayList<Projetor> projetores = projetoresFragment.buscarProjetores(context);

        for (Projetor projetorCadastrado: projetores) {
            if (projetorCadastrado.getNumPatrimonio().equals(projetor.getNumPatrimonio())) {
                projetorCadastrado.setSituacao(0);
            }
        }

        projetoresFragment.save(projetores, context);

    }

    public static void settarEstragadoProjetor(Context context, Projetor projetor) {

        ProjetoresFragment projetoresFragment = new ProjetoresFragment();
        ArrayList<Projetor> projetores = projetoresFragment.buscarProjetores(context);

        for (Projetor projetorCadastrado: projetores) {
            if (projetorCadastrado.getNumPatrimonio().equals(projetor.getNumPatrimonio())) {
                projetorCadastrado.setSituacao(2);
            }
        }

        projetoresFragment.save(projetores, context);

    }
}