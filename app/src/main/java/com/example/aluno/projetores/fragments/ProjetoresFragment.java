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
import com.example.aluno.projetores.adapters.ProjetorAdapter;
import com.example.aluno.projetores.database.Database;
import com.example.aluno.projetores.database.SerializeObject;
import com.example.aluno.projetores.decorators.GridSpacingItemDecoration;
import com.example.aluno.projetores.models.Projetor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProjetoresFragment extends Fragment {

    private RecyclerView rvProjetores;

    public static final String NOME_ARQUIVO = "serp_projetores.dat";
    ArrayList<Projetor> projetores = new ArrayList<>();

    public ProjetoresFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gerenciar_projetores, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        instantiateViews();

        fetchProjetores();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void fetchProjetores() {

        projetores = buscarProjetores(getContext());

        setProjetorData();
    }

    private void setProjetorData(){

        rvProjetores.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        rvProjetores.setLayoutManager(mLayoutManager);
        rvProjetores.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        rvProjetores.setItemAnimator(new DefaultItemAnimator());

        ProjetorAdapter adapter = new ProjetorAdapter(projetores, getContext());
        rvProjetores.setAdapter(adapter);

    }

    private void instantiateViews() {
        rvProjetores = (RecyclerView) getView().findViewById(R.id.rvProjetores);
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

    public void save(ArrayList<Projetor> projetor, Context context) {

        Database.save(context, projetor, NOME_ARQUIVO);
    }

    private void orderById(List<Projetor> list) {
        Collections.sort(list, new Comparator<Projetor>() {
            @Override
            public int compare(Projetor item2, Projetor item1) {

                return item2.getId().compareTo(item1.getId());
            }
        });
    }


    public ArrayList<Projetor> buscarProjetores(Context context){

        ArrayList<Projetor> returnClass = null;

        String ser = SerializeObject.ReadSettings(context, NOME_ARQUIVO);

        if (ser != null && !ser.equalsIgnoreCase("")) {

            Object obj = SerializeObject.stringToObject(ser);

            if (obj instanceof ArrayList) {

                returnClass = (ArrayList<Projetor>)obj;
            }

        }

        if(returnClass != null)
            orderById(returnClass);

        return returnClass;

    }
}
