package com.antonio.recauderiaefiquehay.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.antonio.recauderiaefiquehay.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArticuloAdapter extends RecyclerView.Adapter<ArticuloAdapter.ArticuloCardViewHolder> implements Filterable {

    private List<ArticuloCard> listaArticulos;
    private List<ArticuloCard> listaArticulosFiltered;
    private Context context;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public ArticuloAdapter(Context context, @NonNull final List<ArticuloCard> listaArticulos,
                            @NonNull RecyclerViewOnItemClickListener
                                    recyclerViewOnItemClickListener) {
        this.listaArticulos = listaArticulos;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
        this.context = context;
        if(this.listaArticulos == null){
            this.listaArticulos = listaArticulos;
            this.listaArticulosFiltered = listaArticulos;
            notifyItemChanged(0, listaArticulosFiltered.size());
        } else {
            final DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ArticuloAdapter.this.listaArticulos.size();
                }

                @Override
                public int getNewListSize() {
                    return listaArticulos.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ArticuloAdapter.this.listaArticulos.get(oldItemPosition).getNombre() == listaArticulos.get(newItemPosition).getNombre();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    ArticuloCard newArticulo = ArticuloAdapter.this.listaArticulos.get(oldItemPosition);
                    ArticuloCard oldArticulo = listaArticulos.get(newItemPosition);
                    return newArticulo.getNombre() == oldArticulo.getNombre() ;
                }
            });
            this.listaArticulos = listaArticulos;
            this.listaArticulosFiltered = listaArticulos;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public ArticuloAdapter.ArticuloCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.articulo_card, parent, false);
        return new ArticuloAdapter.ArticuloCardViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ArticuloAdapter.ArticuloCardViewHolder holder, int position) {
        ArticuloCard articuloCard = listaArticulosFiltered.get(position);
        holder.getNombreView().setText(articuloCard.getNombre());
        Picasso.get().load(articuloCard.getUrlArchivo()).placeholder(R.drawable.loding).into((ImageView)holder.getUrlArchivo());
    }

    @Override
    public int getItemCount() {
        if(listaArticulos != null){
            return listaArticulosFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    listaArticulosFiltered = listaArticulos;
                } else {
                    List<ArticuloCard> filteredList = new ArrayList<>();
                    for (ArticuloCard articulo : listaArticulos) {
                        if (articulo.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(articulo);
                        }
                    }
                    listaArticulosFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listaArticulosFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listaArticulosFiltered = (ArrayList<ArticuloCard>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ArticuloCardViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private ImageView urlArchivo;
        private TextView nombre;

        public ArticuloCardViewHolder(View itemView) {
            super(itemView);
            urlArchivo = itemView.findViewById(R.id.imagenArticulo);
            nombre = itemView.findViewById(R.id.nombreArticulo);
            itemView.setOnClickListener(this);
        }

        public TextView getNombreView() {
            return nombre;
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


