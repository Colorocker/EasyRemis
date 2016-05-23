package gedasdev.easy_remis.Class;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by Colorado on 15/05/2016.
 */
public class DataConfiguration {

    private static final String PREFERENCES_NAME = "Config";
    private static final String ENVIRONMENT_NAME = "EnvironmentName";
    private static final String SERVICE_URL = "ServiceURL";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";
    private static final String ORIGIN = "Origin";
    private static final String DESTINY = "Destiny";
    private static final String DATE_RESERVE = "Date_Reserve";


    private SharedPreferences settings;


    public DataConfiguration(Context mContext) {
        this.settings = mContext.getSharedPreferences(PREFERENCES_NAME, 0);
    }



    public String getEnvironmentName() {
        String environmentName = null;
        if(this.settings.contains(ENVIRONMENT_NAME)) {
            environmentName = this.settings.getString(ENVIRONMENT_NAME, "");
        }
        return environmentName;
    }

    public boolean saveEnvironmentName(String environmentName) {
        boolean result = false;
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(ENVIRONMENT_NAME, environmentName);
        editor.commit();
        result = true;
        return result;
    }

    public String getServiceUrl() {
        String serviceUrl = null;
        if(this.settings.contains(SERVICE_URL)) {
            serviceUrl = this.settings.getString(SERVICE_URL, "");
        }
        return serviceUrl;
    }

    public boolean saveServiceURL(String serviceURL) {
        boolean result = false;
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(SERVICE_URL, serviceURL);
        editor.commit();
        result = true;
        return result;
    }

    public String getUsername() {
        String serviceUrl = null;
        if(this.settings.contains(USERNAME)) {
            serviceUrl = this.settings.getString(USERNAME, "");
        }
        return serviceUrl;
    }

    public boolean saveUsername(String username) {
        boolean result = false;
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(USERNAME, username);
        editor.commit();
        result = true;
        return result;
    }

    public String getPassword() {
        String serviceUrl = null;
        if(this.settings.contains(PASSWORD)) {
            serviceUrl = this.settings.getString(PASSWORD, "");
        }
        return serviceUrl;
    }

    public boolean savePassword(String password) {
        boolean result = false;
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(PASSWORD, password);
        editor.commit();
        result = true;
        return result;
    }

    public boolean hasLoggedUser() {
        boolean logged = true;
        logged &= this.getUsername()!=null && this.getUsername().length()>0;
        logged &= this.getPassword()!=null && this.getPassword().length()>0;;
        return logged;
    }

    public String getOrigin() {
        String origin = null;
        if(this.settings.contains(ORIGIN)) {
            origin = this.settings.getString(ORIGIN, "");
        }
        return origin;
    }

    public boolean saveOrigin(String origin) {
        boolean result = false;
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(ORIGIN, origin);
        editor.commit();
        result = true;
        return result;
    }

    public String getDestiny() {
        String destiny = null;
        if(this.settings.contains(DESTINY)) {
            destiny = this.settings.getString(DESTINY, "");
        }
        return destiny;
    }

    public boolean saveDestiny(String destiny) {
        boolean result = false;
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(DESTINY, destiny);
        editor.commit();
        result = true;
        return result;
    }

    public String getDateReserve() {
        String dateReserve = null;
        if(this.settings.contains(DATE_RESERVE)) {
            dateReserve = this.settings.getString(DATE_RESERVE, "");
        }
        return dateReserve;
    }

    public boolean saveDateReserve(String DateReserve) {
        boolean result = false;
        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString(DATE_RESERVE, DateReserve);
        editor.commit();
        result = true;
        return result;
    }




}
