package com.example.aluno.projetores.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.aluno.projetores.database.Database;
import com.example.aluno.projetores.database.SerializeObject;
import com.example.aluno.projetores.models.Emprestimo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmprestimosFragment extends Fragment {

    public static final String NOME_ARQUIVO = "serp_emprestimos.dat";
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void save(ArrayList<Emprestimo> emprestimo, Context context) {

        Database.save(context, emprestimo, NOME_ARQUIVO);

    }

    public ArrayList<Emprestimo> buscarEmprestimos(Context context){

        ArrayList<Emprestimo> returnClass = null;

        String ser = SerializeObject.ReadSettings(context, NOME_ARQUIVO);

        if (ser != null && !ser.equalsIgnoreCase("")) {

            Object obj = SerializeObject.stringToObject(ser);

            if (obj instanceof ArrayList) {

                returnClass = (ArrayList<Emprestimo>)obj;
            }

        }

        return returnClass;

    }
}
