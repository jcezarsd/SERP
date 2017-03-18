package com.example.aluno.projetores.fake;

import com.example.aluno.projetores.models.Emprestimo;
import com.example.aluno.projetores.models.Professor;
import com.example.aluno.projetores.models.Projetor;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bzeymer on 18/03/17.
 */

public class FakeData {

    private ArrayList<Professor> professores = new ArrayList<>();
    private ArrayList<Projetor> projetores = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public FakeData() {
        //Preenche professores
        professores.add(new Professor("Rafael Durelli", "0000000000", "DCC", 0));
        professores.add(new Professor("Ricardo Terra", "1111111111", "DCC", 1));
        professores.add(new Professor("Luiz Henrique", "2222222222", "DCC", 2));
        professores.add(new Professor("Antonio Maria", "3333333333", "DCC", 3));
        professores.add(new Professor("Marluce Pereira", "4444444444", "DCC", 4));

        //Preenche projetores
        projetores.add(new Projetor("Phillips", "BBR1001", 0, "0101010101", 0));
        projetores.add(new Projetor("Phillips", "KXT-8785", 0, "0202020202", 1));
        projetores.add(new Projetor("Epson", "Mod2342", 1, "0303030303", 2));
        projetores.add(new Projetor("Epson", "Mod9783", 1, "0404040404", 3));
        projetores.add(new Projetor("Multilaser", "Ruim", 2, "0505050505", 4));
        projetores.add(new Projetor("DaChina", "SM", 2, "0606060606", 5));
        projetores.add(new Projetor("DaChina", "SM", 2, "0707070707", 6));
        projetores.add(new Projetor("DaChina", "SM", 1, "0808080808", 7));
        projetores.add(new Projetor("DaChina", "SM", 0, "0909090909", 8));

        //Preenche emprestimos
        emprestimos.add(new Emprestimo(0, 0, new Date()));
        emprestimos.add(new Emprestimo(1, 1, new Date()));
        emprestimos.add(new Emprestimo(2, 2, new Date()));
        emprestimos.add(new Emprestimo(3, 0, new Date()));
        emprestimos.add(new Emprestimo(4, 1, new Date()));
        emprestimos.add(new Emprestimo(5, 2, new Date()));
        emprestimos.add(new Emprestimo(6, 3, new Date()));


    }

    public ArrayList<Professor> getProfessores() {
        return professores;
    }

    public ArrayList<Projetor> getProjetores() {
        return projetores;
    }

    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
