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
import com.example.aluno.projetores.adapters.ProfessorAdapter;
import com.example.aluno.projetores.database.Database;
import com.example.aluno.projetores.database.SerializeObject;
import com.example.aluno.projetores.decorators.GridSpacingItemDecoration;
import com.example.aluno.projetores.models.Professor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProfessoresFragment extends Fragment {
    private RecyclerView rvProfessores;

    public static final String NOME_ARQUIVO = "serp_professores.dat";
    private ArrayList<Professor> professores = new ArrayList<>();


    public ProfessoresFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gerenciar_professores, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateViews();

        fetchProfessores();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void fetchProfessores() {

        professores = buscarProfessores(getContext());

        setProfessorData();
    }

    private void setProfessorData(){

        rvProfessores.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        rvProfessores.setLayoutManager(mLayoutManager);
        rvProfessores.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        rvProfessores.setItemAnimator(new DefaultItemAnimator());

        ProfessorAdapter adapter = new ProfessorAdapter(professores, getContext());
        rvProfessores.setAdapter(adapter);

    }

    private void instantiateViews() {
        rvProfessores = (RecyclerView) getView().findViewById(R.id.rvProfessores);
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
    public void save(ArrayList<Professor> professor, Context context) {

        Database.save(context, professor, NOME_ARQUIVO);

    }

    private void orderById(List<Professor> list) {
        Collections.sort(list, new Comparator<Professor>() {
            @Override
            public int compare(Professor item2, Professor item1) {

                return item1.getId().compareTo(item2.getId());
            }
        });
    }

    public ArrayList<Professor> buscarProfessores(Context context) {

        ArrayList<Professor> returnClass = null;

        String ser = SerializeObject.ReadSettings(context, NOME_ARQUIVO);

        if (ser != null && !ser.equalsIgnoreCase("")) {

            Object obj = SerializeObject.stringToObject(ser);

            if (obj instanceof ArrayList) {

                returnClass = (ArrayList<Professor>)obj;
            }

        }

        orderById(returnClass);

        return returnClass;

    }

}