package edu.galileo.android.tipcalc;

import android.app.Application;

/**
 * Created by javie on 31/05/2016.
 */
public class TipCalcApp extends Application {
    private final static String aboutUrl =  "https://google.com";

    public String getAboutUrl() {
        return aboutUrl;
    }
}
