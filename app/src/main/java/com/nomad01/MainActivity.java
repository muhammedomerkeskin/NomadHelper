package com.nomad01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {
    private ImageButton btnHome, btnGeolocation, btnDestination, btnTrigonometry, btnAlert;
    private InterstitialAd mInterstitialAd;
    private int adFreqHome=0,adFreqGeo=0,adFreqDestination=0,adFreqTrigonometry=0,adFreqAlert=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        AdView adView = findViewById(R.id.AdView_Main);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        InterstitialAd.load(this, "ca-app-pub-9376578491124053/1784553406",
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        //super.onAdFailedToLoad(loadAdError);
                        mInterstitialAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        //super.onAdLoaded(interstitialAd);
                        mInterstitialAd = interstitialAd;
                    }
                });
        btnHome = findViewById(R.id.img_btn_main_home);
        btnHome.setOnClickListener(v ->
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentMain, Home.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("")
                        .commit());

        btnGeolocation = findViewById(R.id.img_btn_main_geolocation);
        btnGeolocation.setOnClickListener(v ->{
            if (mInterstitialAd != null && adFreqGeo%2==0) {
                mInterstitialAd.show(MainActivity.this);
            }
            adFreqGeo++;
            if (adFreqGeo==100){
                adFreqGeo=0;
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentMain, Geolocation.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("")
                    .commit();
        });
        btnDestination = findViewById(R.id.img_btn_main_destination);
        btnDestination.setOnClickListener(v ->{
            if (mInterstitialAd != null && adFreqDestination%2==0) {
                mInterstitialAd.show(MainActivity.this);
            }
            adFreqDestination++;
            if (adFreqDestination==100){
                adFreqDestination=0;
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentMain, Destination.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("")
                    .commit();
        });
        btnTrigonometry = findViewById(R.id.img_btn_main_trigonometry);
        btnTrigonometry.setOnClickListener(v ->{
            if (mInterstitialAd != null && adFreqTrigonometry%2==0) {
                mInterstitialAd.show(MainActivity.this);
            }
            adFreqTrigonometry++;
            if (adFreqTrigonometry==100){
                adFreqTrigonometry=0;
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentMain, Trigonometry.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("")
                    .commit();
        });
        btnAlert = findViewById(R.id.img_btn_main_location_alert);
        btnAlert.setOnClickListener(v ->{
            if (mInterstitialAd != null && adFreqAlert%2==0) {
                mInterstitialAd.show(MainActivity.this);
            }
            adFreqAlert++;
            if (adFreqAlert==100){
                adFreqAlert=0;
            }
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentMain, LocationAlert.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("")
                    .commit();
        });
    }
}