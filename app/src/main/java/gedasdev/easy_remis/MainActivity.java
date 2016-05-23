package gedasdev.easy_remis;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import gedasdev.easy_remis.Activity.LoginActivity;
import gedasdev.easy_remis.Activity.New_Service_Activity;
import gedasdev.easy_remis.Activity.PickerDateActivity;
import gedasdev.easy_remis.Activity.RegisterActivity;
import gedasdev.easy_remis.Fragment.List_Services_Fragment;
import gedasdev.easy_remis.Fragment.Map_Fragment;
import gedasdev.easy_remis.Interface.IFragmentToActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFragmentToActivity {

    private Fragment currentFragment;
    ViewPager pager;
    private long timeBackPressed = 0;
    private int currentPagerPosition;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        pager = (ViewPager) findViewById(R.id.viewPager);

        if (savedInstanceState == null) {
            currentPagerPosition =0;
            currentFragment = new Map_Fragment();
            FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction3.add(R.id.frame, currentFragment);
            fragmentTransaction3.commit();

        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else {
            switch(currentPagerPosition)
            {
                case 0:
                        ValidateTwoBackPressed();
                    break;
                case 1:
                    if(currentFragment != null) {
                        BackFragment(currentPagerPosition);
                        currentPagerPosition = currentPagerPosition-1;
                    }
                    break;
                case 2:
                    if(currentFragment != null) {
                        BackFragment(currentPagerPosition);
                        currentPagerPosition = currentPagerPosition-1;
                    }
                    break;
            }
            //super.onBackPressed();
        }
    }

    private void ValidateTwoBackPressed() {
        if (this.timeBackPressed + 2000 > System.currentTimeMillis()) {
            this.finish();
        } else {
            this.timeBackPressed = System.currentTimeMillis();
            Toast.makeText(this, this.getString(R.string.press_back_again), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        Drawable drawable = menu.getItem(0).getIcon();
        if(drawable != null) {
            drawable.mutate();
            drawable.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_favorite) {
            Toast.makeText(this, "PICKER!!", Toast.LENGTH_LONG).show();
            Intent intent   = new Intent(this, PickerDateActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            currentFragment = null;


        } else if (id == R.id.nav_mis_servicios) {

            List_Services_Fragment fragment3 = new List_Services_Fragment();
            FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
            fragmentTransaction3.replace(R.id.frame, fragment3);
            fragmentTransaction3.commit();
            currentFragment = null;

        } else if (id == R.id.nav_servicio) {
            if (currentFragment == null) {
                currentFragment = new Map_Fragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, currentFragment);
                fragmentTransaction.commit();


            }
        } else if (id == R.id.nav_recorrido) {

            Intent intent = new Intent(this, New_Service_Activity.class);

            currentFragment = null;

            startActivity(intent);
        } else if (id == R.id.nav_settings) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer != null) {
            drawer.closeDrawer(GravityCompat.START);
        }
        item.setChecked(true);
        currentPagerPosition = 0;

        return true;
    }

    @Override
    public void showToast(String msg) {

    }

    @Override
    public void NextFragment(int poscurrentFragment) {


        switch (poscurrentFragment) {
            case 0:
                Toast.makeText(this, "Fragment "+ poscurrentFragment ,Toast.LENGTH_SHORT).show();
                if(currentFragment != null)
                {
                    Map_Fragment obj;
                    obj = (Map_Fragment) currentFragment;
                    obj.ChangeTab(poscurrentFragment + 1);
                    currentPagerPosition = poscurrentFragment + 1;
                }
                break;
            case 1:
                if(currentFragment != null)
                {
                    Map_Fragment obj;
                    obj = (Map_Fragment) currentFragment;
                    obj.ChangeTab(poscurrentFragment + 2);
                    currentPagerPosition = poscurrentFragment + 1;
                }
                break;
            case 2:

                break;
            default:
                if(currentFragment != null)
                {
                    Map_Fragment obj;
                    obj = (Map_Fragment) currentFragment;
                    obj.ChangeTab(poscurrentFragment + 2);
                }
            break;

        }

    }

    @Override
    public void BackFragment(int poscurrentFragment) {
        switch (poscurrentFragment) {
            case 0:
                break;
            case 1:
                if(currentFragment != null)
                {
                    Map_Fragment obj;
                    obj = (Map_Fragment) currentFragment;
                    obj.ChangeTab(poscurrentFragment -1);
                }
                break;
            case 2:
                if(currentFragment != null)
                {
                    Map_Fragment obj;
                    obj = (Map_Fragment) currentFragment;
                    obj.ChangeTab(poscurrentFragment -1);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://gedasdev.easy_remis/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://gedasdev.easy_remis/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
