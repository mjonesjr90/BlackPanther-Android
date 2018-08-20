package com.malcomjones.blackpanther;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CustomActivity extends AppCompatActivity {

    private int AD_TYPE_FLAG;
    private int SSP_FLAG;
    private boolean SA_FLAG;
    private int AUCTION_FLAG;
    private String siteID;
    private String placementID;
    private String adUnitID;
    private String admobID;
//    RadioGroup _adGroup;
    RadioButton _rBanner;
    RadioButton _rMREC;
    RadioButton _rInterstitial;
    RadioButton _rNative;
    CheckBox _cSuperAuction;
    EditText _siteIDHolder;
    EditText _placementIDHolder;
    EditText _adUnitIDHolder;
    EditText _admobIDHolder;
    Button _requestButton;
    private static final String SAVED_SITE_ID = "SITE_ID";
    private static final String SAVED_PLACEMENT_ID = "PLACEMENT_ID";
    private static final String SAVED_ADUNIT_ID = "ADUNIT_ID";
    private static final String SAVED_ADMOB_ID = "ADMOB_ID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

//        _adGroup = findViewById(R.id.adRadioGroup);
        _rBanner = findViewById(R.id.radio_banner);
        _rMREC = findViewById(R.id.radio_mrec);
        _rInterstitial = findViewById(R.id.radio_interstitial);
        _rNative = findViewById(R.id.radio_native);
        _cSuperAuction = findViewById(R.id.sa_toggle);

        _siteIDHolder = findViewById(R.id.editSite);
        _placementIDHolder = findViewById(R.id.editPlacement);
        _adUnitIDHolder = findViewById(R.id.editAdUnit);
        _admobIDHolder = findViewById(R.id.editAdMobID);

        _requestButton = findViewById(R.id.requestButton);

        //Use prefs to load up text that was in place before app closing
        _siteIDHolder.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence s, int start, int count, int after) {

           }

           @Override
           public void onTextChanged(CharSequence s, int start, int before, int count) {

           }

           @Override
           public void afterTextChanged(Editable s) {
               prefs.edit().putString(SAVED_SITE_ID, s.toString()).apply();
           }
        });
        _placementIDHolder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.edit().putString(SAVED_PLACEMENT_ID, s.toString()).commit();
            }
        });
        _adUnitIDHolder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.edit().putString(SAVED_ADUNIT_ID, s.toString()).commit();
            }
        });
        _admobIDHolder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                prefs.edit().putString(SAVED_ADMOB_ID, s.toString()).commit();
            }
        });

        if(prefs != null){
            _siteIDHolder.setText(prefs.getString(SAVED_SITE_ID, ""));
            _placementIDHolder.setText(prefs.getString(SAVED_PLACEMENT_ID, ""));
            _adUnitIDHolder.setText(prefs.getString(SAVED_ADUNIT_ID, ""));
            _admobIDHolder.setText(prefs.getString(SAVED_ADMOB_ID, ""));
        }

        /**
         * AD_TYPE_FLAG can have 5 different values
         * 0: no ad type has been selected
         * 1: banner
         * 2: mrec
         * 3: interstitial
         * 4: native
         */
        AD_TYPE_FLAG = 0;

        /**
         * SSP_FLAG can have 6 different values
         * 0: no ssp has been selected
         * 1: one mobile
         * 2: mopub
         * 3: dfp
         * 4: admob
         * 5: flurry
         */
        SSP_FLAG = 0;
    }

    public void sspRadioClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_one_mobile:
                if (checked)
                    SSP_FLAG = 1;
                    _rBanner.setVisibility(View.VISIBLE);
                    _rMREC.setVisibility(View.VISIBLE);
                    _rInterstitial.setVisibility(View.VISIBLE);
                    _rNative.setVisibility(View.VISIBLE);
//                    _cSuperAuction.setVisibility(View.VISIBLE);
                    _siteIDHolder.setVisibility(View.VISIBLE);
                    _placementIDHolder.setVisibility(View.VISIBLE);
                    _adUnitIDHolder.setVisibility(View.GONE);
                    _admobIDHolder.setVisibility(View.GONE);
                    _requestButton.setVisibility(View.VISIBLE);
                break;
            case R.id.radio_mopub:
                if (checked)
                    _rBanner.setVisibility(View.VISIBLE);
                    _rMREC.setVisibility(View.GONE);
                    _rInterstitial.setVisibility(View.GONE);
                    _rNative.setVisibility(View.GONE);
                    _cSuperAuction.setVisibility(View.GONE);
                    _siteIDHolder.setVisibility(View.GONE);
                    _placementIDHolder.setVisibility(View.GONE);
                    _adUnitIDHolder.setVisibility(View.VISIBLE);
                    _admobIDHolder.setVisibility(View.GONE);
                    _requestButton.setVisibility(View.VISIBLE);
                    SSP_FLAG = 2;
                break;
            case R.id.radio_dfp:
                if (checked)
                    _rBanner.setVisibility(View.VISIBLE);
                    _rMREC.setVisibility(View.GONE);
                    _rInterstitial.setVisibility(View.GONE);
                    _rNative.setVisibility(View.GONE);
                    _cSuperAuction.setVisibility(View.GONE);
                    _siteIDHolder.setVisibility(View.GONE);
                    _placementIDHolder.setVisibility(View.GONE);
                    _adUnitIDHolder.setVisibility(View.VISIBLE);
                    _admobIDHolder.setVisibility(View.GONE);
                    _requestButton.setVisibility(View.VISIBLE);
                    SSP_FLAG = 3;
                break;
            case R.id.radio_admob:
                if (checked)
                    _rBanner.setVisibility(View.VISIBLE);
                    _rMREC.setVisibility(View.GONE);
                    _rInterstitial.setVisibility(View.GONE);
                     _rNative.setVisibility(View.GONE);
                    _cSuperAuction.setVisibility(View.GONE);
                    _siteIDHolder.setVisibility(View.GONE);
                    _placementIDHolder.setVisibility(View.GONE);
                    _adUnitIDHolder.setVisibility(View.GONE);
                    _admobIDHolder.setVisibility(View.VISIBLE);
                    _requestButton.setVisibility(View.VISIBLE);
                    SSP_FLAG = 4;
                break;
            case R.id.radio_flurry:
                if (checked)
                    _rBanner.setVisibility(View.GONE);
                    _rMREC.setVisibility(View.GONE);
                    _rInterstitial.setVisibility(View.GONE);
                    _rNative.setVisibility(View.GONE);
                    _cSuperAuction.setVisibility(View.GONE);
                    _siteIDHolder.setVisibility(View.GONE);
                    _placementIDHolder.setVisibility(View.GONE);
                    _adUnitIDHolder.setVisibility(View.GONE);
                    _admobIDHolder.setVisibility(View.GONE);
                    _requestButton.setVisibility(View.GONE);
                    SSP_FLAG = 5;
                break;
        }
    }

    public void adRadioClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_banner:
                if (checked)
                    AD_TYPE_FLAG = 1;
                    if(SSP_FLAG == 1){
                        _cSuperAuction.setVisibility(View.VISIBLE);
                    }
                break;
            case R.id.radio_mrec:
                if (checked)
                    AD_TYPE_FLAG = 2;
                    if(SSP_FLAG == 1){
                        _cSuperAuction.setVisibility(View.VISIBLE);
                    }
                break;
            case R.id.radio_interstitial:
                if (checked)
                    AD_TYPE_FLAG = 3;
                    if(SSP_FLAG == 1){
                        _cSuperAuction.setVisibility(View.GONE);
                    }
                break;
            case R.id.radio_native:
                if (checked)
                    AD_TYPE_FLAG = 4;
                    if(SSP_FLAG == 1){
                        _cSuperAuction.setVisibility(View.GONE);
                    }
                break;
        }
    }

    public void setSuperAuction(View view){
        // Is the button now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.sa_toggle:
                if (checked) {
                    SA_FLAG = true;
                    Toast.makeText(CustomActivity.this, "Expect a Bid Response", Toast.LENGTH_LONG).show();
                }
                else{
                    SA_FLAG = false;
                    Toast.makeText(CustomActivity.this, "Expect a Waterfall Response", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    public boolean validate(){
        boolean valid = true;

        siteID = _siteIDHolder.getText().toString();
        placementID = _placementIDHolder.getText().toString();
        adUnitID = _adUnitIDHolder.getText().toString();
        admobID = _admobIDHolder.getText().toString();

        if(AD_TYPE_FLAG == 0){
            valid = false;
        }

        if(SSP_FLAG == 0){
            valid = false;
        }

        if(_siteIDHolder.getVisibility() == View.VISIBLE && (siteID.isEmpty() || !siteID.matches("[a-zA-Z0-9.? ]*"))){
            _siteIDHolder.setError("Please Enter a SiteID");
            valid = false;
        }

        if(_placementIDHolder.getVisibility() == View.VISIBLE && placementID.isEmpty()){
            _placementIDHolder.setError("Please Enter a PlacementID");
            valid = false;
        }

        if(_adUnitIDHolder.getVisibility() == View.VISIBLE && (adUnitID.isEmpty() || !adUnitID.matches("[a-zA-Z0-9.? ]*"))){
            _adUnitIDHolder.setError("Please Enter an Ad Unit ID");
            valid = false;
        }

        if(_admobIDHolder.getVisibility() == View.VISIBLE && (admobID.isEmpty() || !admobID.matches("[a-zA-Z0-9.? ]*"))){
            _admobIDHolder.setError("Please Enter an AdMob ID");
            valid = false;
        }

        return valid;
    }

    public void requestAd(View view){
        //Check if all fields are filled in
        if(validate()){
            Toast.makeText(CustomActivity.this, "All requirements met", Toast.LENGTH_LONG).show();

            //Check which ad was requested
            //ONE Mobile
            if(SSP_FLAG == 1){
                if(AD_TYPE_FLAG == 1){
                    Intent bannerIntent;
                    if(SA_FLAG){
                        bannerIntent = new Intent(this, SuperAuctionBannerActivity.class);
                    }
                    else {
                        bannerIntent = new Intent(this, BannerActivity.class);
                    }
                    bannerIntent.putExtra("customSite", siteID);
                    bannerIntent.putExtra("customPlacement", placementID);
                    startActivity(bannerIntent);
                }
                if(AD_TYPE_FLAG == 2){
                    Intent mrecIntent;
                    if(SA_FLAG) {
                        mrecIntent = new Intent(this, SuperAuctionMRECActivity.class);
                    }
                    else {
                        mrecIntent = new Intent(this, MRECActivity.class);
                    }
                    mrecIntent.putExtra("customSite", siteID);
                    mrecIntent.putExtra("customPlacement", placementID);
                    startActivity(mrecIntent);
                }
                if(AD_TYPE_FLAG == 3){
                    Intent interstitialIntent = new Intent(this, InterstitialActivity.class);
                    interstitialIntent.putExtra("customSite", siteID);
                    interstitialIntent.putExtra("customPlacement", placementID);
                    startActivity(interstitialIntent);
                }
            }
        }
        else{
            Toast.makeText(CustomActivity.this, "Missing Information", Toast.LENGTH_LONG).show();
        }
    }
}
