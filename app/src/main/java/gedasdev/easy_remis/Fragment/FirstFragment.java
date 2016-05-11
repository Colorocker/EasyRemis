package gedasdev.easy_remis.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import gedasdev.easy_remis.Activity.SearchActivity;
import gedasdev.easy_remis.Interface.IFragmentToActivity;
import gedasdev.easy_remis.R;

/**
 * Fragment class.
 * <p/>
 * Created by wagatsumakenju on 2015/08/25.
 */
public class FirstFragment extends Fragment {
    private IFragmentToActivity mCallback;

    /**
     * fields
     */
    private static FirstFragment instance = null;
    CardView search_origen;
    CardView origen;
    String DireccionOrigen = "";
    TextView textDireccion;
    TextView textDistricto;

    /**
     * Create fragment view when paginated.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.first_frag, container, false);


        search_origen = (CardView) v.findViewById(R.id.card_search);
        textDireccion = (TextView) v.findViewById(R.id.txt_direccion_search);
        textDistricto = (TextView) v.findViewById(R.id.txt_distrito_search);

        search_origen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "SEARCH", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getContext(), SearchActivity.class);
                startActivityForResult(i, 1);

            }
        });

        origen = (CardView) v.findViewById(R.id.card_origen);

        origen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "ORIGEN", Toast.LENGTH_LONG).show();

                textDireccion = (TextView) v.findViewById(R.id.txt_direccion_search);

                mCallback.NextFragment();

            }
        });

        return v;
    }

    /**
     * Returns new instance.
     *
     * @param text
     * @return
     */
    public static FirstFragment newInstance(String text) {

        if (instance == null) {
            // new instance
            instance = new FirstFragment();

            // sets data to bundle
            Bundle bundle = new Bundle();
            bundle.putString("msg", text);

            // set data to fragment
            instance.setArguments(bundle);

            return instance;
        } else {

            return instance;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (IFragmentToActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Comprobamos si el resultado de la segunda actividad es "RESULT_CANCELED".
        if (resultCode == Activity.RESULT_CANCELED) {
            // Si es así mostramos mensaje de cancelado por pantalla.
            //Toast.makeText(this, "Resultado cancelado", Toast.LENGTH_SHORT)                    .show();
        } else {
            // De lo contrario, recogemos el resultado de la segunda actividad.
            String resultado = data.getExtras().getString("result");
            Toast.makeText(getContext(), resultado, Toast.LENGTH_LONG).show();
            textDireccion.setText(resultado);
        }
    }

    public void SetUbicacionOrigen(String direccion, String districto) {
        Toast.makeText(getActivity(), "Fragment 1: GET DIRECCION.",
                Toast.LENGTH_SHORT).show();
        if(textDireccion != null) {
            textDireccion.setText(direccion);
            textDistricto.setText(districto);
        }
    }



}
