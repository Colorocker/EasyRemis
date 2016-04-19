package gedasdev.easy_remis.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gedasdev.easy_remis.Class.CustomItemClickListener;
import gedasdev.easy_remis.Class.Solicitud;
import gedasdev.easy_remis.Class.SolicitudAdapter;
import gedasdev.easy_remis.R;

/**
 * Created by Colorado on 17/04/2016.
 */
public class List_Services_Fragment extends Fragment //implements RecyclerViewClickListener
 {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    //private  RecyclerViewClickListener recyclerViewClickListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment_list_services, container, false);
        List<Solicitud> items = getSolicituds();


        // Obtener el Recycler
        recycler = (RecyclerView) v.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this.getContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
         adapter = new SolicitudAdapter(items, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position,int numero) {
                Toast.makeText(v.getContext(), "Clicked Item: "+position + "Numero:" + numero ,Toast.LENGTH_SHORT).show();
            }
        });


        recycler.setAdapter(adapter);


        return v;
    }

     @NonNull
     private List<Solicitud> getSolicituds() {
         // Inicializar Lista
         List<Solicitud> items = new ArrayList<>();

         items.add(new Solicitud(1,"Sitio de Montevideo 1145","Patagones 2550" , 230));
         items.add(new Solicitud(2,"Patagones 2550","Jujuy 360" , 230));
         items.add(new Solicitud(3,"Sitio de Montevideo 1145","Estados Unidos 2430" , 230));
         items.add(new Solicitud(4,"Sitio de Montevideo 1145","Patagones 2550" , 230));
         items.add(new Solicitud(5,"Patagones 2550","Jujuy 360" , 230));
         items.add(new Solicitud(6,"Sitio de Montevideo 1145","Estados Unidos 2430" , 230));
         return items;
     }
////    @Override
////    public void recyclerViewListClicked(View v, int position)
////    {
////
//    }


}
