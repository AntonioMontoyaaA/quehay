package com.antonio.recauderiaefiquehay.fragments;

import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.antonio.recauderiaefiquehay.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CardNuevoAdapter extends RecyclerView.Adapter<CardNuevoAdapter.CardViewHolder> {

    private List<CardNuevo> data;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public CardNuevoAdapter(@NonNull List<CardNuevo> data,
                                  @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_nuevo, parent, false);
        return new CardViewHolder(row);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        CardNuevo cardNuevo = data.get(position);
        holder.getNombreView().setText(cardNuevo.getNombre());
        holder.getUnidadMedida().setText(cardNuevo.getUnidadMedida());

        Picasso.get().load(cardNuevo.getUrlArchivo()).placeholder(R.drawable.loding).into((ImageView)holder.getUrlArchivo());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private ImageView urlArchivo;
        private TextView nombre;
        private TextView unidadMedida;


        public CardViewHolder(View itemView) {
            super(itemView);
            urlArchivo = itemView.findViewById(R.id.imagenNovedad);
            nombre = itemView.findViewById(R.id.nombre);
            unidadMedida = itemView.findViewById(R.id.unidadMedida);
            itemView.setOnClickListener(this);
        }

        public TextView getNombreView() {
            return nombre;
        }

        public TextView getUnidadMedida() {
            return unidadMedida;
        }

        public ImageView getUrlArchivo() {
            return urlArchivo;
        }

        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }
    }
}
