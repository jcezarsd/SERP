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

import com.example.aluno.projetores.fake.FakeData;
import com.example.aluno.projetores.models.Professor;
import com.example.aluno.projetores.R;
import com.example.aluno.projetores.adapters.ProfessorAdapter;
import com.example.aluno.projetores.decorators.GridSpacingItemDecoration;

import java.util.ArrayList;

public class ProfessoresFragment extends Fragment {
    private RecyclerView rvProfessores;

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

        professores = new FakeData().getProfessores();

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
}
/*

public class ProfessoresFragment extends Fragment {

    RecyclerView rvProfessores;

    ArrayList<Professor> profEstaticos = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gerenciar_professores);

//        ListView listProfessores = (ListView) findViewById(R.id.listViewProfessores);

        profEstaticos.add(new Professor("Rafael Durelli", "0000000000", "DCC", 0));
        profEstaticos.add(new Professor("Ricardo Terra", "1111111111", "DCC", 1));
        profEstaticos.add(new Professor("Luiz Henrique", "2222222222", "DCC", 2));
        profEstaticos.add(new Professor("Antonio Maria", "3333333333", "DCC", 3));
        profEstaticos.add(new Professor("Marluce Pereira", "4444444444", "DCC", 4));
        bindViews();
        setProfessorData();
//        final ArrayList<String> listItems = new ArrayList<>();
//        for(int i = 0; i < profEstaticos.size(); i++){
//            Professor professor = profEstaticos.get(i);
//            listItems.add(professor.getNome());
//        }
//
//        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
//
//        listProfessores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view,
//                                    int position, long id) {
//                final String item = (String) parent.getItemAtPosition(position);
//                view.animate().setDuration(2000).alpha(0)
//                        .withEndAction(new Runnable() {
//                            @Override
//                            public void run() {
//                                listItems.remove(item);
//                                adapter.notifyDataSetChanged();
//                                view.setAlpha(1);
//                            }
//                        });
//            }
//
//        });
//
//        listProfessores.setAdapter(adapter);

    }

    private void bindViews() {
        rvProfessores = (RecyclerView) findViewById(R.id.rvProfessores);
    }

    private void setProfessorData(){

        rvProfessores.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvProfessores.setLayoutManager(mLayoutManager);
        rvProfessores.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        rvProfessores.setItemAnimator(new DefaultItemAnimator());

        ProfessorAdapter adapter = new ProfessorAdapter(profEstaticos, this);
        rvProfessores.setAdapter(adapter);

    }

    */
/**
     * Converting dp to pixel
     *//*

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }

}
*/
