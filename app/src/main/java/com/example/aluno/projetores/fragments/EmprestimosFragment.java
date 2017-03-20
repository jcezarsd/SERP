package com.example.aluno.projetores.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aluno.projetores.R;
import com.example.aluno.projetores.adapters.EmprestimoAdapter;
import com.example.aluno.projetores.database.Database;
import com.example.aluno.projetores.database.SerializeObject;
import com.example.aluno.projetores.decorators.GridSpacingItemDecoration;
import com.example.aluno.projetores.models.Emprestimo;

import java.util.ArrayList;

public class EmprestimosFragment extends Fragment {
    private RecyclerView rvEmprestimos;

    public static final String NOME_ARQUIVO = "serp_emprestimos.dat";
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gerenciar_emprestimos, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateViews();

        fetchEmprestimos();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void fetchEmprestimos() {

        emprestimos = buscarEmprestimos(getContext());

        setEmprestimoData();
    }

    private void setEmprestimoData(){

        rvEmprestimos.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        rvEmprestimos.setLayoutManager(mLayoutManager);
        rvEmprestimos.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        rvEmprestimos.setItemAnimator(new DefaultItemAnimator());

        EmprestimoAdapter adapter = new EmprestimoAdapter(emprestimos, getContext());
        rvEmprestimos.setAdapter(adapter);

    }

    private void instantiateViews() {
        rvEmprestimos = (RecyclerView) getView().findViewById(R.id.rvEmprestimos);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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
