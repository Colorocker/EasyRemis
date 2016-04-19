package gedasdev.easy_remis.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gedasdev.easy_remis.R;


/**
 * Created by Colorado on 29/03/2016.
 */
public class ServiceFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment_services,container,false);
        return v;
    }

}
