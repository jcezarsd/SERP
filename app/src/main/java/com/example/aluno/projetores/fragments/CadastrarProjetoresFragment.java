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
import com.example.aluno.projetores.models.Projetor;

public class CadastrarProjetoresFragment extends Fragment {

    private EditText etMarca;
    private EditText etModelo;
    private EditText etPatrimonio;
    private AppCompatButton acbCadastrar;

    public CadastrarProjetoresFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cadastrar_projetores, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateViews();
        acbCadastrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cadastrarProjetor();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void instantiateViews() {
        etMarca = (EditText) getView().findViewById(R.id.etMarca);
        etModelo = (EditText) getView().findViewById(R.id.etModelo);
        etPatrimonio = (EditText) getView().findViewById(R.id.etPatrimonio);
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

    public void cadastrarProjetor() {

        Projetor novoProjetor = new Projetor(etMarca.getText().toString(), etModelo.getText().toString(), 0, etPatrimonio.getText().toString(), 0);

        Projetor.cadastrarProjetor(getContext(), novoProjetor);
    }
}
