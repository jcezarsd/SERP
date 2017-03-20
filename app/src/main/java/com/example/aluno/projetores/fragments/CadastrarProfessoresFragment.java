package com.example.aluno.projetores.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.aluno.projetores.R;
import com.example.aluno.projetores.models.Professor;

public class CadastrarProfessoresFragment extends Fragment {

    private EditText etNome;
    private EditText etMatricula;
    private EditText etDepartamento;
    private AppCompatButton acbCadastrar;

    public CadastrarProfessoresFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastrar_professores, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateViews();
        acbCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cadastrarProfessor();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void instantiateViews() {
        etNome = (EditText) getView().findViewById(R.id.etNome);
        etMatricula = (EditText) getView().findViewById(R.id.etMatricula);
        etDepartamento = (EditText) getView().findViewById(R.id.etDepartamento);
        acbCadastrar = (AppCompatButton) getView().findViewById(R.id.acbCadastrar);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void cadastrarProfessor() {

        Professor novoProfessor = new Professor(etNome.getText().toString(), etMatricula.getText().toString(), etDepartamento.getText().toString(), 0);

        Professor.cadastrarProfessor(getContext(), novoProfessor);
    }
}
