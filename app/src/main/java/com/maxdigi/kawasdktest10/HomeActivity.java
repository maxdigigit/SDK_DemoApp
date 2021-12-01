package com.maxdigi.kawasdktest10;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kawasdk.Utils.Common;
import com.kawasdk.Utils.KawaMap;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
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
    private static final int PERMISSION_REQUEST_CODE = 100;
    Button startKawaBtn;
    EditText nameTxt, addressTxt, companyTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();
        setContentView(R.layout.activity_home);
        startKawaBtn = findViewById(R.id.startKawaBtn);
        nameTxt = findViewById(R.id.nameTxt);
        addressTxt = findViewById(R.id.addressTxt);
        companyTxt = findViewById(R.id.companyTxt);
        companyTxt.setVisibility(View.GONE);
        startKawaBtn.setOnClickListener(viewV -> gotoFarmLocation());
    }

    private void gotoFarmLocation() {
        Log.e("TAG>>", nameTxt.getText().toString());
        String nameStr = nameTxt.getText().toString().trim();
        String addressStr = addressTxt.getText().toString().trim();
        String companyStr = companyTxt.getText().toString().trim();
        if (nameStr.isEmpty()) {
            Toast.makeText(HomeActivity.this, getResources().getString(R.string.error_name), Toast.LENGTH_LONG).show();
            return;
        }
//        else if (companyStr.isEmpty()) {
//            Toast.makeText(HomeActivity.this,getResources().getString(R.string.error_company), Toast.LENGTH_LONG).show();
//            return;
//        }
        else if (addressStr.isEmpty()) {
            Toast.makeText(HomeActivity.this, getResources().getString(R.string.error_address), Toast.LENGTH_LONG).show();
            return;
        } else {
            Common.USER_NAME = nameTxt.getText().toString();
            Common.USER_ADDRESS = addressTxt.getText().toString();
            Common.USER_COMPANY = companyTxt.getText().toString();
            //segmentInit(HomeActivity.this);
            Intent intent = new Intent(HomeActivity.this, kawaHomeActivity.class);
            startActivity(intent);
        }
    }

    public void setLocale() {

        String languageToLoad = "in"; // use in fo bahasha lanuage.
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }


}
