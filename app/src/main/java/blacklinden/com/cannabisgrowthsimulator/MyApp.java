package blacklinden.com.cannabisgrowthsimulator;

import android.app.Application;
import android.content.Context;

import blacklinden.com.cannabisgrowthsimulator.ui.typef.TypefaceUtil;

public class MyApp extends Application {

    private static Application app;

    public void onCreate() {
        super.onCreate();
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "font/angelive.ttf");
        MyApp.app = this;

    }

    public static Application getAppContext() {
        return MyApp.app;
    }
}