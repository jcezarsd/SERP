package com.example.aluno.projetores;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfessoresActivity extends ListActivity {

    ArrayList<Professor> profEstaticos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciar_professor);

        profEstaticos.add(new Professor("Rafael Durelli", "0000000000", "DCC", 0));
        profEstaticos.add(new Professor("Ricardo Terra", "1111111111", "DCC", 1));
        profEstaticos.add(new Professor("Luiz Henrique", "2222222222", "DCC", 2));
        profEstaticos.add(new Professor("Antonio Maria", "3333333333", "DCC", 3));
        profEstaticos.add(new Professor("Marluce Pereira", "4444444444", "DCC", 4));

        ArrayAdapter adapter;

//        profEstaticos.add("Professor 1");
//        profEstaticos.add("Professor 2");
//        profEstaticos.add("Professor 3");
//        profEstaticos.add("Professor 4");
//        profEstaticos.add("Professor 5");
//        profEstaticos.add("Professor 6");

        String[] listItems = new String[profEstaticos.size()];
// 3
        for(int i = 0; i < profEstaticos.size(); i++){
            Professor professor = profEstaticos.get(i);
            listItems[i] = professor.getNome();
        }

        ListView listProfessores = (ListView) findViewById(R.id.listViewProfessores);

        adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listItems);

        listProfessores.setAdapter(adapter);

    }

}
