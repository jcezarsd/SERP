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

import com.example.aluno.projetores.R;
import com.example.aluno.projetores.fragments.ProfessoresFragment;
import com.example.aluno.projetores.models.Emprestimo;
import com.example.aluno.projetores.models.Professor;
import com.example.aluno.projetores.models.Projetor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bzeymer on 20/03/17.
 */

public class Emprestar extends AppCompatActivity {

    private TextView tvMarca;
    private TextView tvModelo;
    private TextView tvPatriminio;
    private Spinner spProfessores;
    private AppCompatButton acbEmprestar;
    private ArrayList<Professor> professores;
    private Projetor projetor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprestar);

        instantiateViews();

        preencherProjetorData();

        fetchProfessores(getApplicationContext());

        configurarBotao();




    }

    private void instantiateViews(){
        tvMarca = (TextView) findViewById(R.id.tvMarcaEmprestar);
        tvModelo = (TextView) findViewById(R.id.tvModeloEmprestar);
        tvPatriminio = (TextView) findViewById(R.id.tvPatrimonioEmprestar);
        spProfessores = (Spinner) findViewById(R.id.spProfessor);
        acbEmprestar = (AppCompatButton) findViewById(R.id.acbEmprestar);

    }

    private void preencherProjetorData() {

        Intent intent = getIntent();
        projetor = (Projetor) intent.getExtras().get("PROJETOR");

        tvMarca.setText(projetor.getMarca());
        tvModelo.setText(projetor.getModelo());
        tvPatriminio.setText(projetor.getNumPatrimonio());
    }

    private void fetchProfessores(Context context) {

        ProfessoresFragment professoresFragment = new ProfessoresFragment();

        professores = professoresFragment.buscarProfessores(context);

        preencherSpinner();
    }

    private void preencherSpinner() {

        ArrayList<String> nomesProfessores = new ArrayList<>();

        for (Professor professor: professores) {
            nomesProfessores.add(professor.getNome());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomesProfessores);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProfessores.setAdapter(adapter);

    }

    private void configurarBotao() {

        acbEmprestar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cadastrarEmprestimo();
            }
        });
    }

    private void cadastrarEmprestimo() {

        String nomeSelecionado = spProfessores.getSelectedItem().toString();
        Professor professorSelecionado = Professor.findByNome(professores, nomeSelecionado);

        if (projetor != null && professorSelecionado != null) {

            Emprestimo emprestimo = new Emprestimo(projetor.getId(), professorSelecionado.getId(), new Date(), null);

            Emprestimo.cadastrarEmprestimo(getApplicationContext(), emprestimo);
        }

        finish();

    }
}

