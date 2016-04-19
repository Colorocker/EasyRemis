package gedasdev.easy_remis.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import gedasdev.easy_remis.Activity.RegisterActivity;

import gedasdev.easy_remis.R;


/**
 * Created by Colorado on 29/03/2016.
 */
public class LoginActivity extends AppCompatActivity {

    private TextView _signupLink;
    private static final int REQUEST_SIGNUP = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fragment_login);

        this.setTitle("Login");
        _signupLink = (TextView)this.findViewById(R.id.link_signup);

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

        Intent intent   = new Intent(v.getContext(), RegisterActivity.class);
        startActivity(intent);
            }
        });


    }

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.content_fragment_login,container,false);
//
//        getActivity().setTitle("Registracion");
//
//        _signupLink = (TextView)v.findViewById(R.id.link_signup);
//
//        _signupLink.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // Start the Signup activity
////                Intent intent = new Intent(getActivity().getApplicationContext(), RegisterFragment.class);
//                //                startActivityForResult(intent, REQUEST_SIGNUP);
//
//                    RegisterFragment fragment2 = new RegisterFragment();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.frame, fragment2);
//                    //fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();
//
//            }
//        });
//
//        return v;
//    }
}
