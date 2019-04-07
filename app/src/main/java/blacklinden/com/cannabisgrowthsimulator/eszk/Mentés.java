package blacklinden.com.cannabisgrowthsimulator.eszk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;

import blacklinden.com.cannabisgrowthsimulator.MainActivity;
import blacklinden.com.cannabisgrowthsimulator.MyApp;


public class Mentés {
    private static final String SETTINGS_NAME = "default_settings";
    private static Mentés sSharedPrefs;
    private SharedPreferences mPref;
    private Gson gson;
    private SharedPreferences.Editor mEditor;
    private boolean mBulkUpdate = false;


    public enum Key {

        TRMS_LST,
        ERllT_LST,
        VGTRMK_LST,
        PARATART,
        TESZT_OBJ,
        SAMPLE_SZINT,
        SAMPLE_ZSETON,
        B1,
        B2,
        B3,
        B4,
        B5,
        B6,
        SAMPLE_STR,
        SAMPLE_CAN,
        SAMPLE_INT,
        SAMPLE_NUT,
        SAMPLE_POT,
        BELEP
    }

    private Mentés(Context context) {
        mPref = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }


    public static Mentés getInstance(Context context) {
        if (sSharedPrefs == null) {
            sSharedPrefs = new Mentés(context.getApplicationContext());
        }
        return sSharedPrefs;
    }

    public static Mentés getInstance() {
        if (sSharedPrefs != null) {
            return sSharedPrefs;
        }


        else return getInstance(MyApp.getAppContext().getApplicationContext());

    }

    public void put(Key key, String val) {
        doEdit();
        mEditor.putString(key.name(), val);
        doCommit();
    }


    public void put(Key key, int val) {
        doEdit();
        mEditor.putInt(key.name(), val);
        doCommit();
    }

    public void put(Key key, boolean val) {
        doEdit();
        mEditor.putBoolean(key.name(), val);
        doCommit();
    }

    public void put(Key key, float val) {
        doEdit();
        mEditor.putFloat(key.name(), val);
        doCommit();
    }

    public String gsonra(Object o){
        return gson.toJson(o);
    }

    public Object javara(String s, Class o){
        return gson.fromJson(s,o);
    }


    public void put(Key key, double val) {
        doEdit();
        mEditor.putString(key.name(), String.valueOf(val));
        doCommit();
    }

    public void put(Key key, long val) {
        doEdit();
        mEditor.putLong(key.name(), val);
        doCommit();
    }

    public String getString(Key key, String defaultValue) {
        return mPref.getString(key.name(), defaultValue);
    }

    public String getString(Key key) {
        return mPref.getString(key.name(), null);
    }

    public int getInt(Key key) {
        return mPref.getInt(key.name(), 0);
    }

    public int getInt(Key key, int defaultValue) {
        return mPref.getInt(key.name(), defaultValue);
    }

    public long getLong(Key key) {
        return mPref.getLong(key.name(), 0);
    }

    public long getLong(Key key, long defaultValue) {
        return mPref.getLong(key.name(), defaultValue);
    }

    public float getFloat(Key key) {
        return mPref.getFloat(key.name(), 0);
    }

    public float getFloat(Key key, float defaultValue) {
        return mPref.getFloat(key.name(), defaultValue);
    }


    public double getDouble(Key key) {
        return getDouble(key, 0);
    }

    private double getDouble(Key key, double defaultValue) {
        try {
            return Double.valueOf(mPref.getString(key.name(), String.valueOf(defaultValue)));
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public boolean getBoolean(Key key, boolean defaultValue) {
        return mPref.getBoolean(key.name(), defaultValue);
    }

    public boolean getBoolean(Key key) {
        return mPref.getBoolean(key.name(), false);
    }

    /**
     * Remove keys from SharedPreferences.
     *
     * @param keys The enum of the key(s) to be removed.
     */
    public void remove(Key... keys) {
        doEdit();
        for (Key key : keys) {
            mEditor.remove(key.name());
        }
        doCommit();
    }

    /**
     * Remove all keys from SharedPreferences.
     */
    public void clear() {
        doEdit();
        mEditor.clear();
        doCommit();
    }

    @SuppressLint("CommitPrefEdits")
    public void edit() {
        mBulkUpdate = true;
        mEditor = mPref.edit();
    }

    public void commit() {
        mBulkUpdate = false;
        mEditor.commit();
        mEditor = null;
    }

    @SuppressLint("CommitPrefEdits")
    private void doEdit() {
        if (!mBulkUpdate && mEditor == null) {
            mEditor = mPref.edit();
        }
    }

    private void doCommit() {
        if (!mBulkUpdate && mEditor != null) {
            mEditor.commit();
            mEditor = null;
        }
    }
}
