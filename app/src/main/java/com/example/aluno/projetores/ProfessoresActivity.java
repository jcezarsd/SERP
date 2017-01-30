package com.example.aluno.projetores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfessoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ListView listProfessores = (ListView) findViewById(R.id.listView);

        ArrayList<Professor> profEstaticos = new ArrayList<>();

        new Professor("Rafael Durelli", "0000000000", "DCC", 0);
        new Professor("Ricardo Terra", "1111111111", "DCC", 1);
        new Professor("Luiz Henrique", "2222222222", "DCC", 2);
        new Professor("Antonio Maria", "3333333333", "DCC", 3);
        new Professor("Marluce Pereira", "4444444444", "DCC", 4);
        
    }
}
