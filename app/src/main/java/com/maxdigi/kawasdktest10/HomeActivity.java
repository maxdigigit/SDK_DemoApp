package com.maxdigi.kawasdktest10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.kawasdk.Utils.Common;
import com.smartlook.sdk.smartlook.Smartlook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;


public class HomeActivity extends AppCompatActivity {
    Button startKawaBtn;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Smartlook.setUserIdentifier("SAMPLE DEMO");
        //Smartlook.setupAndStartRecording("cb8c6000d31264049b53ee7b55b2a926fdfc668f");
        Smartlook.setUserIdentifier("KAWA SDK");
        Smartlook.SetupOptionsBuilder builder = new Smartlook.SetupOptionsBuilder("cb8c6000d31264049b53ee7b55b2a926fdfc668f")
                .setFps(2)
                .setExperimental(true)
                .setActivity(null);
        Smartlook.setupAndStartRecording(builder.build());
        setLocale();
        setContentView(R.layout.activity_home);
        startKawaBtn = findViewById(R.id.startKawaBtn);
        startKawaBtn.setOnClickListener(viewV -> gotoFarmLocation());

    }

    private void gotoFarmLocation() {
       Intent intent = new Intent(HomeActivity.this, kawaHomeActivity.class);
        startActivity(intent);

    }

    public void setLocale() {

        String languageToLoad  = "en"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }



}
