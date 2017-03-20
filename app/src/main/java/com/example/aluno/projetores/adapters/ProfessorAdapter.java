package com.example.aluno.projetores.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.projetores.R;
import com.example.aluno.projetores.models.Professor;

import java.util.List;

/**
 * Created by bzeymer on 17/03/17.
 */

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private List<Professor> professores;
    private Context mContext;


    public ProfessorAdapter(List<Professor> professores, Context mContext) {
        this.professores= professores;
        this.mContext = mContext;
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.professor_list_item, parent, false);
        ProfessorViewHolder placeHolder = new ProfessorViewHolder(v);
        return placeHolder;
    }

    @Override
    public void onBindViewHolder(final ProfessorViewHolder holder, int position) {

        holder.nome.setText(professores.get(position).getNome());
        holder.departamento.setText(professores.get(position).getDepartamento());

    }

    @Override
    public int getItemCount() {
        return professores == null ? 0 : professores.size();
    }

    public class ProfessorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nome;
        private TextView departamento;

        public ProfessorViewHolder(View itemView) {
            super(itemView);
            nome = (TextView)itemView.findViewById(R.id.tvNome);
            departamento = (TextView)itemView.findViewById(R.id.tvDepartamento);


            departamento.setOnClickListener(this);
            nome.setOnClickListener(this);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(mContext, "CRICOUUUUT", Toast.LENGTH_LONG);
        }

    }

}

