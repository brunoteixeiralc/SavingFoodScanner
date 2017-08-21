package br.com.savingfoodscanner;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by brunolemgruber on 21/08/17.
 */

public class SavingFoodScannerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Nexa-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

