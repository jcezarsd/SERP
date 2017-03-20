package com.example.aluno.projetores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aluno.projetores.fragments.ProfessoresFragment;
import com.example.aluno.projetores.models.Emprestimo;
import com.example.aluno.projetores.models.Professor;
import com.example.aluno.projetores.models.Projetor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bzeymer on 20/03/17.
 */

public class Devolver extends AppCompatActivity {

    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvPatriminio;
    private TextView tvNome;
    private TextView tvDepartamento;
    private AppCompatButton acbDevolver;
    private Projetor projetor;
    private Professor professor;
    private Emprestimo emprestimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devolver);

        instantiateViews();

        preencherEmprestimoData();

        configurarBotao();


    }

    private void instantiateViews(){
        tvMarca = (TextView) findViewById(R.id.tvMarcaDevolver);
        tvModelo = (TextView) findViewById(R.id.tvModeloDevolver);
        tvPatriminio = (TextView) findViewById(R.id.tvPatrimonioDevolver);
        tvNome = (TextView) findViewById(R.id.tvNomeDevolver);
        tvDepartamento = (TextView) findViewById(R.id.tvDepartamentoDevolver);
        acbDevolver = (AppCompatButton) findViewById(R.id.acbDevolver);

    }

    private void preencherEmprestimoData() {

        Intent intent = getIntent();
        projetor = (Projetor) intent.getExtras().get("PROJETOR");
        professor = (Professor) intent.getExtras().get("PROFESSOR");
        emprestimo = (Emprestimo) intent.getExtras().get("EMPRESTIMO");

        tvMarca.setText(projetor.getMarca());
        tvModelo.setText(projetor.getModelo());
        tvPatriminio.setText(projetor.getNumPatrimonio());
        tvNome.setText(professor.getNome());
        tvDepartamento.setText(professor.getDepartamento());
    }


    private void configurarBotao() {

        acbDevolver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finalizarEmprestimo();
            }
        });
    }

    private void finalizarEmprestimo() {

        Emprestimo.finalizarEmprestimo(getApplicationContext(), emprestimo);

        Projetor.devolverProjetor(getApplicationContext(), projetor);

        finish();

    }
}

