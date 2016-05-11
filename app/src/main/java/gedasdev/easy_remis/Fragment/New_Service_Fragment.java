package gedasdev.easy_remis.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.CardView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;

import gedasdev.easy_remis.Activity.LoginActivity;
import gedasdev.easy_remis.Activity.MapActivity;
import gedasdev.easy_remis.Class.AutoCompleteAdapter;
import gedasdev.easy_remis.Class.AutoCompletePlace;
import gedasdev.easy_remis.R;

/**
 * Created by Colorado on 14/04/2016.
 */
public class New_Service_Fragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks {

    CardView origen;
    CardView destino;

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private ImageButton imgbtn;

    private int PLACE_PICKER_REQUEST = 1;
    private AutoCompleteAdapter mAdapter;

    private TextView mTextView;
    private AutoCompleteTextView mPredictTextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_fragment_new_services,container,false);



        mGoogleApiClient = new GoogleApiClient
                .Builder( this.getContext() )
//                .enableAutoManage( this, 0, this )
                .addApi( Places.GEO_DATA_API )
                //.addApi( Places.PLACE_DETECTION_API )
                .addConnectionCallbacks( this )
                .addOnConnectionFailedListener( this )
                .build();

        origen = (CardView) v.findViewById(R.id.card_origen);

        origen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Origen action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent = new Intent(v.getContext(), MapActivity.class);
//                startActivity(intent);
                  mTextView = (TextView) v.findViewById(R.id.adress_origen);
                  displayPlacePicker();

            }
        });

        destino = (CardView) v.findViewById(R.id.card_destino);

        destino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "Destino action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                Intent intent = new Intent(v.getContext(), MapActivity.class);
//                startActivity(intent);
                  mTextView = (TextView) v.findViewById(R.id.adress_destino);
                  displayPlacePicker();
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if( mGoogleApiClient != null )
            mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        if( mGoogleApiClient != null && mGoogleApiClient.isConnected() ) {
            mAdapter.setGoogleApiClient( null );
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    private void findPlaceById( String id ) {
        if( TextUtils.isEmpty( id ) || mGoogleApiClient == null || !mGoogleApiClient.isConnected() )
            return;

        Places.GeoDataApi.getPlaceById( mGoogleApiClient, id ) .setResultCallback(new ResultCallback<PlaceBuffer>() {
            @Override
            public void onResult(PlaceBuffer places) {
                if( places.getStatus().isSuccess() ) {
                    Place place = places.get( 0 );
                    displayPlace( place );
                    //mPredictTextView.setText( "" );
                    mAdapter.clear();
                }

                //Release the PlaceBuffer to prevent a memory leak
                places.release();
            }
        } );
    }

    private void displayPlace( Place place ) {
        if( place == null )
            return;

        String content = "";
        if( !TextUtils.isEmpty( place.getName() ) ) {
            content += "Name: " + place.getName() + "\n";
        }
        if( !TextUtils.isEmpty( place.getAddress() ) ) {
            content += "Address: " + place.getAddress() + "\n";
        }
        mTextView.setText( content );
    }


    @Override
    public void onConnected( Bundle bundle ) {
        if( mAdapter != null )
            mAdapter.setGoogleApiClient( mGoogleApiClient );
    }

    @Override
    public void onConnectionSuspended( int i ) {

    }

    @Override
    public void onConnectionFailed( ConnectionResult connectionResult ) {

    }

    private void displayPlacePicker() {


        if( mGoogleApiClient == null || !mGoogleApiClient.isConnected() )
            return;

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult( builder.build(this.getActivity()) , PLACE_PICKER_REQUEST );
        } catch ( GooglePlayServicesRepairableException e ) {
            Log.d( "PlacesAPI Demo", "GooglePlayServicesRepairableException thrown" );
        } catch ( GooglePlayServicesNotAvailableException e ) {
            Log.d( "PlacesAPI Demo", "GooglePlayServicesNotAvailableException thrown" );
        }
    }


}
