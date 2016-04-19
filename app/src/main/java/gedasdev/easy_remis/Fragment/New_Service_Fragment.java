package gedasdev.easy_remis.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;

import gedasdev.easy_remis.R;

/**
 * Created by Colorado on 14/04/2016.
 */
public class New_Service_Fragment extends Fragment {

    CardView origen;
    CardView destino;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment_new_services,container,false);

        origen = (CardView) v.findViewById(R.id.card_origen);

        origen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Origen action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        destino = (CardView) v.findViewById(R.id.card_destino);

        destino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Destino action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return v;
    }
}
