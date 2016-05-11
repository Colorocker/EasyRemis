package gedasdev.easy_remis.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import gedasdev.easy_remis.Class.MyPagerAdapter;
import gedasdev.easy_remis.Interface.IFragmentToActivity;
import gedasdev.easy_remis.R;

/**
 * Created by Colorado on 30/04/2016.
 */
public class Map_Fragment extends Fragment implements IFragmentToActivity, ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener
        , OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleApiClient.ConnectionCallbacks
        , GoogleApiClient.OnConnectionFailedListener, LocationListener, ActivityCompat.OnRequestPermissionsResultCallback

{

    private final String LOG_TAG = "Map Fragment";

    private static final int NUMBER_OF_PAGES = 3;
    private RadioGroup radioGroup;
    ViewPager pager;

    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    TextView textViewDireccion;
    TextView textViewDistrito;
    MyPagerAdapter myadapter;

    LatLng latLng;
    GoogleMap mGoogleMap;
    SupportMapFragment mFragment;
    Marker currLocationMarker;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_map, container, false);

        textViewDireccion = (TextView) v.findViewById(R.id.txt_direccion_search);
        textViewDistrito = (TextView) v.findViewById(R.id.txt_distrito_search);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        pager = (ViewPager) view.findViewById(R.id.viewPager);

        myadapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());

        pager.setAdapter(myadapter);

        pager.addOnPageChangeListener(this);

        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(this);


        mFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        //mFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        //map = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();


        if (mFragment != null) {
            mFragment.getMapAsync(this);


            View mapView = mFragment.getView();

            if (mapView != null && mapView.findViewById(Integer.parseInt("1")) != null) {
                // Get the button view
                View locationButton = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
                // and next place it, on bottom right (as Google Maps app)
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)
                        locationButton.getLayoutParams();
                // position on right bottom
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);

                layoutParams.setMargins(0, 150, 30, 30);
            }
        } else {
            Toast.makeText(this.getContext(), "NULLLLL!!!", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButton1:
                pager.setCurrentItem(0);
                break;
            case R.id.radioButton2:
                pager.setCurrentItem(1);
                break;
            case R.id.radioButton3:
                pager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                radioGroup.check(R.id.radioButton1);
                break;
            case 1:
                radioGroup.check(R.id.radioButton2);
                break;
            case 2:
                radioGroup.check(R.id.radioButton3);
                break;
            default:
                radioGroup.check(R.id.radioButton1);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setOnMyLocationButtonClickListener(this);

        enableMyLocation();

        buildGoogleApiClient();

        mGoogleApiClient.connect();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    protected synchronized void buildGoogleApiClient() {
        Toast.makeText(this.getContext(), "buildGoogleApiClient", Toast.LENGTH_SHORT).show();
        mGoogleApiClient = new GoogleApiClient.Builder(this.getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location mLastLocation = null;


        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            //PermissionUtils.requestPermission(this.getActivity(), LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mGoogleMap != null) {
            // Access to the location has been granted to the app.
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }


        if (mLastLocation != null) {
            //place marker at current position
            //mGoogleMap.clear();
            latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Posicion Origen");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currLocationMarker = mGoogleMap.addMarker(markerOptions);
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000); //5 seconds
        mLocationRequest.setFastestInterval(3000); //3 seconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //mLocationRequest.setSmallestDisplacement(0.1F); //1/10 meter

        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            // PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mGoogleMap != null) {
            // Access to the location has been granted to the app.
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this.getContext(), "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this.getContext(), "onConnectionFailed", Toast.LENGTH_SHORT).show();
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            //PermissionUtils.requestPermission(this.getActivity(), LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mGoogleMap != null) {
            // Access to the location has been granted to the app.
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (currLocationMarker != null) {
            currLocationMarker.remove();
        }
        //if (currLocationMarker != null) {
        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Posicion Origen");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        currLocationMarker = mGoogleMap.addMarker(markerOptions);

        Toast.makeText(this.getContext(), "Location Changed", Toast.LENGTH_SHORT).show();

        GetDirecctionLocation_Search(location.getLatitude(), location.getLongitude());
        //zoom to current position:
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(latLng).zoom(15).build();

        mGoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        //If you only need one location, unregister the listener
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    public void GetDirecctionLocation_Search(double latitude, double longitude) {
        try {
            Geocoder geocoder = new Geocoder(this.getContext(), Locale.getDefault());
            List<Address> list = geocoder.getFromLocation(latitude, longitude, 1);
            if (!list.isEmpty()) {
                Address address = list.get(0);
                //Toast.makeText(this.getContext(), address.getAddressLine(0), Toast.LENGTH_SHORT).show();
                //Toast.makeText(this.getContext(), textViewDireccion.getText(), Toast.LENGTH_SHORT).show();

                if (textViewDireccion != null) {
                    textViewDireccion.setText(address.getAddressLine(0));
                    textViewDistrito.setText(address.getLocality());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void NextFragment() {

    }

    @Override
    public void BackFragment() {

    }

    @Override
    public void communicateToFragment2() {
        SecondFragment fragment = (SecondFragment) myadapter.getItem(1);
        if (fragment != null) {
            fragment.SetUbicacionOrigen("","");
        } else {
            Log.i(LOG_TAG, "Fragment 2 is not initialized");
        }

    }
}
