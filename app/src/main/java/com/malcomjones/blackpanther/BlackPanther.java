package com.malcomjones.blackpanther;

import android.app.Application;
import android.util.Log;

import com.millennialmedia.MMException;
import com.millennialmedia.MMSDK;

/**
 * Created by majones95 on 7/31/18.
 */

public class BlackPanther extends Application{
    private static final String TAG = "SubclassActivity";
    private static final String SITE_ID = "2c9d2b4f016464d63b8cd72b85ed0011";
//    private static final String FLURRY_API_KEY = "23PWRR432MZ78CRF882Q";
    /**
     * Initialize the SDK and set the SiteID
     *
     * appInfo can also call setCoppa(boolean c) and setMediator(String m)
     *
     * throw an exception if the initialization fails
     */
    @Override
    public void onCreate() {
        super.onCreate();

        //Initialize the Millennial SDK
        try{
            MMSDK.initialize(this);

//            //Set Site ID
//            AppInfo appInfo = new AppInfo();
//            appInfo.setSiteId(SITE_ID);
//            MMSDK.setAppInfo(appInfo);

        } catch (MMException e){
            Log.e(TAG, "SDK didn't initialize", e);
        }


        // Initialize Flurry
//        new FlurryAgent.Builder()
//                .withLogEnabled(true)
//                .withCaptureUncaughtExceptions(true)
//                .withContinueSessionMillis(10)
//                .build(this, FLURRY_API_KEY);

    }

    public String getSiteId(){
        return SITE_ID;
    }
}
