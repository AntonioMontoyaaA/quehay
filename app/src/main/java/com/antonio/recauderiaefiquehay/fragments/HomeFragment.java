package com.antonio.recauderiaefiquehay.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.antonio.recauderiaefiquehay.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    List<CardNuevo> listaCards;
    public NavController navController;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        listaCards = new ArrayList<CardNuevo>();
        listaCards.add(new CardNuevo("https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_gala.jpg", "Manzana Gala", "Precio por kilo", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious"));
        listaCards.add(new CardNuevo("https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Manzana Golden", "Precio por kilo", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious"));
        listaCards.add(new CardNuevo("https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_roja.jpg", "Manzana Roja", "Precio por Unidad", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious"));


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new CardNuevoAdapter(listaCards, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_imageview, null);
                Picasso.get().load(listaCards.get(position).getUrlArchivo()).placeholder(R.drawable.loding).into((ImageView)dialogLayout.findViewById(R.id.imagenModalArticulo));
                ((TextView)dialogLayout.findViewById(R.id.descripcionCompleta)).setText(listaCards.get(position).getDescripcion());
                dialogLayout.setBackgroundColor(getResources().getColor(R.color.colorBackMain));
                //builder.setPositiveButton("Aceptar", null);
                builder.setView(dialogLayout);
                builder.show();


            }
        }));
        //VERTICAL
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //HORIZONTAL
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        ((MaterialCardView) view.findViewById(R.id.cardTodos)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //NavDirections action = ListFragmentDirections.actionListFragmentToDetailFragment(navigator);
                //Navigation.findNavController(view).navigate(action);
                NavDirections action = new ActionOnlyNavDirections(R.id.action_firstFragment_to_secondFragment);
                Navigation.findNavController(view).navigate(action);
            }

        });



        return view; //inflater.inflate(R.layout.fragment_home, container, false);
    }
}