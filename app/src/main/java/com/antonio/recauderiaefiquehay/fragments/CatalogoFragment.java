package com.antonio.recauderiaefiquehay.fragments;

import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antonio.recauderiaefiquehay.MainActivity;
import com.antonio.recauderiaefiquehay.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CatalogoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CatalogoFragment extends Fragment {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private ArticuloAdapter articuloAdapter;
    private List<ArticuloCard> listaArticulos;
    private SearchView.OnQueryTextListener queryTextListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CatalogoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CatalogoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CatalogoFragment newInstance(String param1, String param2) {
        CatalogoFragment fragment = new CatalogoFragment();
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
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_catalogo, container, false);
        BottomNavigationView bottomNavigation = view.findViewById(R.id.bottom_navigation);;

        listaArticulos = new ArrayList<ArticuloCard>();
        listaArticulos.add(new ArticuloCard("Fresas", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_gala.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Zapote", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Yerbabuena", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_roja.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Epazote", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Pera", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Manzana Gala", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Manzana Golden", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Frjiloes", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Precio por kilo", 1));
        listaArticulos.add(new ArticuloCard("Nueces", "Esta fruta surgió en Nueva Zelanda en 1939 a partir del cruzamiento de Kidds Orange REd y Golden Delicious", "https://recauderiaefiapps.s3.amazonaws.com/catalogo/frutas/manzana_golden.jpg", "Precio por kilo", 1));


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerCatalogoView);
        articuloAdapter = new ArticuloAdapter(getContext(), listaArticulos, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = getLayoutInflater();
                View dialogLayout = inflater.inflate(R.layout.alert_dialog_with_imageview, null);
                Picasso.get().load(listaArticulos.get(position).getUrlArchivo()).placeholder(R.drawable.loding).into((ImageView)dialogLayout.findViewById(R.id.imagenModalArticulo));
                ((TextView)dialogLayout.findViewById(R.id.descripcionCompleta)).setText(listaArticulos.get(position).getDescripcion());
                dialogLayout.setBackgroundColor(getResources().getColor(R.color.colorBackMain));
                //builder.setPositiveButton("Aceptar", null);
                builder.setView(dialogLayout);
                builder.show();


            }
        });
        recyclerView.setAdapter(articuloAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) recyclerView.getLayoutManager()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_frutas:

                                return true;
                            case R.id.navigation_verduras:

                                return true;
                            case R.id.navigation_abarrotes:

                                return true;
                            case R.id.navigation_semillas:

                                return true;
                            case R.id.navigation_otros:

                                return true;
                        }
                        return false;
                    }
                };

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (item != null) {
            searchView = (SearchView) item.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    System.out.println("Al cambiar el texto");
                    articuloAdapter.getFilter().filter(newText);
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    System.out.println("Al dar enter");
                    articuloAdapter.getFilter().filter(query);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}