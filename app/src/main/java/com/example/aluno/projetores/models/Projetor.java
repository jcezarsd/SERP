package com.example.aluno.projetores.models;

import java.util.ArrayList;

/**
 * Created by Julio_Cezar on 30/01/17.
 */
public class Projetor {

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
                return "Disponível";
            case 1:
                return "Emprestado";
            case 2:
                return "Com defeito";
            default:
                return "";
        }
    }

    public String getNumPatrimonio() {
        return numPatrimonio;
    }

    public static Projetor findById (ArrayList<Projetor> projetores, Integer id){
        for (Projetor projetor: projetores) {
            if (projetor.id.equals(id)){
                return projetor;
            };
        }
        return null;
    }
}