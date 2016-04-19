package gedasdev.easy_remis.Class;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import gedasdev.easy_remis.R;

/**
 * Created by Colorado on 17/04/2016.
 */
public class SolicitudAdapter  extends RecyclerView.Adapter<SolicitudAdapter.SolcitiudViewHolder> {
    private List<Solicitud> items;
    CustomItemClickListener listener;

    public static class SolcitiudViewHolder extends RecyclerView.ViewHolder   {
        // Campos respectivos de un item
        public ImageView imagen;

        CardView cv;
        public TextView origen;
        public TextView destino;
        public TextView precio;
        public TextView numeroSolicitud;

        Context mContext;
        CustomItemClickListener listener;

        public SolcitiudViewHolder(View v) {
            super(v);
            //imagen = (ImageView) v.findViewById(R.id.imagen);
            cv = (CardView)itemView.findViewById(R.id.cv);
            origen = (TextView) v.findViewById(R.id.origen);
            destino = (TextView) v.findViewById(R.id.destino);
            precio = (TextView) v.findViewById(R.id.precio);
            numeroSolicitud = (TextView) v.findViewById(R.id.numero);
        }


    }

    public SolicitudAdapter(List<Solicitud> items,CustomItemClickListener customItemClickListener) {
        this.items = items;
        this.listener = customItemClickListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public SolcitiudViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.solicitud_card, viewGroup, false);
        final SolcitiudViewHolder pvh = new SolcitiudViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView numero;
                numero = (TextView)  v.findViewById(R.id.numero);

//                listener.onItemClick(v, pvh.getLayoutPosition(),Integer.valueOf(numero.getText().toString()));
                listener.onItemClick(v, pvh.getLayoutPosition(),0);
            }
        });
        return pvh;
    }

    @Override
    public void onBindViewHolder(SolcitiudViewHolder viewHolder, int i) {
        viewHolder.origen.setText("Origen:" + items.get(i).getOrigenDireccion());
        viewHolder.destino.setText("Destino: " + items.get(i).getDestinoDireccion());
        viewHolder.precio.setText("Precio: "+ String.valueOf(items.get(i).getPrecioFinal()));
        viewHolder.numeroSolicitud.setText("Numero Solicitud: "+ String.valueOf(items.get(i).getNumeroSolicitud()));
    }



}


