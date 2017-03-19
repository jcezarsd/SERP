package com.example.aluno.projetores.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aluno.projetores.R;
import com.example.aluno.projetores.models.Projetor;

import java.util.List;

/**
 * Created by bzeymer on 17/03/17.
 */

public class ProjetorAdapter extends RecyclerView.Adapter<ProjetorAdapter.ProjetorViewHolder> {

    private List<Projetor> projetores;
    private Context mContext;

    public ProjetorAdapter(List<Projetor> projetores, Context mContext) {
        this.projetores = projetores;
        this.mContext = mContext;
    }

    @Override
    public ProjetorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.projetor_list_item, parent, false);
        ProjetorViewHolder placeHolder = new ProjetorViewHolder(v);
        return placeHolder;
    }

    @Override
    public void onBindViewHolder(final ProjetorViewHolder holder, int position) {

        holder.marca.setText(projetores.get(position).getMarca());
        holder.patrimonio.setText(projetores.get(position).getNumPatrimonio());
        holder.estado.setText(projetores.get(position).getSituacao());

    }

    @Override
    public int getItemCount() {
        return projetores == null ? 0 : projetores.size();
    }

    public class ProjetorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView marca;
        private TextView patrimonio;
        private TextView estado;

        public ProjetorViewHolder(View itemView) {
            super(itemView);
            marca = (TextView)itemView.findViewById(R.id.tvMarca);
            patrimonio = (TextView)itemView.findViewById(R.id.tvPatrimonio);
            estado = (TextView)itemView.findViewById(R.id.tvEstado);


            marca.setOnClickListener(this);
            patrimonio.setOnClickListener(this);
            estado.setOnClickListener(this);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(mContext, "CRICOUUUUT", Toast.LENGTH_LONG);
        }

    }

}

