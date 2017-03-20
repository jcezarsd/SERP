package com.example.aluno.projetores.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.projetores.R;
import com.example.aluno.projetores.formatters.Formatters;
import com.example.aluno.projetores.fragments.ProfessoresFragment;
import com.example.aluno.projetores.fragments.ProjetoresFragment;
import com.example.aluno.projetores.models.Emprestimo;
import com.example.aluno.projetores.models.Professor;
import com.example.aluno.projetores.models.Projetor;

import java.util.Date;
import java.util.List;

/**
 * Created by bzeymer on 17/03/17.
 */

public class EmprestimoAdapter extends RecyclerView.Adapter<EmprestimoAdapter.EmprestimoViewHolder> {

    private List<Emprestimo> emprestimos;
    private Context mContext;

    public EmprestimoAdapter(List<Emprestimo> emprestimos, Context mContext) {
        this.emprestimos = emprestimos;
        this.mContext = mContext;
    }

    @Override
    public EmprestimoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.emprestimo_list_item, parent, false);
        EmprestimoViewHolder placeHolder = new EmprestimoViewHolder(v);
        return placeHolder;
    }

    @Override
    public void onBindViewHolder(final EmprestimoViewHolder holder, int position) {
        
        Projetor projetor = Projetor.findById(new ProjetoresFragment().buscarProjetores(mContext), emprestimos.get(position).getIdProjetor());
        Professor professor = Professor.findById(new ProfessoresFragment().buscarProfessores(mContext), emprestimos.get(position).getIdProfessor());

        if (projetor != null && professor != null) {

            holder.marcaProjetor.setText(projetor.getMarca());
            holder.nomeProfessor.setText(professor.getNome());
        } else {

            holder.marcaProjetor.setText("");
            holder.nomeProfessor.setText("");
        }

        holder.dataEmprestimo.setText("Emprestado em: " + Formatters.dateToString(emprestimos.get(position).getDataEmprestimo()));
        Date dataDevolucao = emprestimos.get(position).getDataDevolucao();
        if (dataDevolucao != null) {
            holder.dataDevolucao.setText("Devolvido em: " + Formatters.dateToString(dataDevolucao));
        } else {
            holder.dataDevolucao.setText("");
        }

    }

    @Override
    public int getItemCount() {
        return emprestimos == null ? 0 : emprestimos.size();
    }

    public class EmprestimoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView marcaProjetor;
        private TextView nomeProfessor;
        private TextView dataEmprestimo;
        private TextView dataDevolucao;

        public EmprestimoViewHolder(View itemView) {
            super(itemView);
            marcaProjetor = (TextView)itemView.findViewById(R.id.tvMarcaProjetor);
            nomeProfessor = (TextView)itemView.findViewById(R.id.tvNomeProfessor);
            dataEmprestimo = (TextView)itemView.findViewById(R.id.tvDataEmprestimo);
            dataDevolucao = (TextView)itemView.findViewById(R.id.tvDataDevolucao);


            marcaProjetor.setOnClickListener(this);
            nomeProfessor.setOnClickListener(this);
            dataEmprestimo.setOnClickListener(this);
            dataDevolucao.setOnClickListener(this);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(mContext, "CRICOUUUUT", Toast.LENGTH_LONG);
        }

    }

}

