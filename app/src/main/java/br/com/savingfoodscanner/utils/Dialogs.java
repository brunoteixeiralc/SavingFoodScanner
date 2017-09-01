package br.com.savingfoodscanner.utils;

import android.content.Context;
import android.graphics.Color;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;

/**
 * Created by brunolemgruber on 31/08/17.
 */

public final class Dialogs {

    private static SimpleArcDialog simpleArcDialog;
    private static ArcConfiguration arcConfiguration;

    public static void openDialog(Context context, String text){

        int[] colors = {Color.parseColor("#729762")};

        arcConfiguration = new ArcConfiguration(context);
        arcConfiguration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        arcConfiguration.setColors(colors);
        arcConfiguration.setText(text);
        simpleArcDialog = new SimpleArcDialog(context);
        simpleArcDialog.setConfiguration(arcConfiguration);
        simpleArcDialog.show();
    }

    public static void closeDialog(Context context){
        if(simpleArcDialog != null)
            simpleArcDialog.dismiss();
    }
}
