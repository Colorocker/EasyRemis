package gedasdev.easy_remis.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gedasdev.easy_remis.R;


/**
 * Created by Colorado on 04/04/2016.
 */
public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Registracion");
        setContentView(R.layout.content_fragment_register);
    }




//    @Nullable
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.content_fragment_register,container,false);
//        return v;
//    }

}
