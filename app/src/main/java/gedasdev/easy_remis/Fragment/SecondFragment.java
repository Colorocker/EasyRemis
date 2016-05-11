package gedasdev.easy_remis.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import gedasdev.easy_remis.Interface.IFragmentToActivity;
import gedasdev.easy_remis.R;

/**
 * Fragment class.
 *
 * Created by wagatsumakenju on 2015/08/25.
 */
public class SecondFragment extends Fragment {
    private IFragmentToActivity mCallback;

    /**
     * fields
     */
    private static SecondFragment instance = null;
    TextView textDireccion;
    TextView textDistricto;

    /**
     * Create fragment view when paginated.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_frag, container, false);

//        TextView textView = (TextView) v.findViewById(R.id.tvFragSecond);
//        textView.setText(getArguments().getString("msg"));

        textDireccion = (TextView) v.findViewById(R.id.txt_direccion_search_pagetwo);
        textDistricto = (TextView) v.findViewById(R.id.txt_distrito_search_pagetwo);

        return v;
    }

    /**
     * Returns new instance.
     *
     * @param text
     * @return
     */
    public static SecondFragment newInstance(String text){

        if(instance == null){
            // new instance
            instance = new SecondFragment();

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

    public void SetUbicacionOrigen(String direccion, String districto) {
        Toast.makeText(getActivity(), "Fragment 2: Get Direccion!!.",
                Toast.LENGTH_SHORT).show();
        if(textDireccion != null) {
            textDireccion.setText(direccion);
            textDistricto.setText(districto);
        }
    }
}
