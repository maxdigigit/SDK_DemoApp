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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kawasdk.Utils.Common;
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
    EditText nameTxt, addressTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLocale();
        Smartlook.setUserIdentifier("KAWA SDK");
        Smartlook.SetupOptionsBuilder builder = new Smartlook.SetupOptionsBuilder("e2db094332144a7b107aec48a24e2d585e540b93")
                .setFps(2)
                .setExperimental(true)
                .setActivity(null);
        Smartlook.setupAndStartRecording(builder.build());
        setContentView(R.layout.activity_home);
        startKawaBtn = findViewById(R.id.startKawaBtn);
        nameTxt = findViewById(R.id.nameTxt);
        addressTxt = findViewById(R.id.addressTxt);
        startKawaBtn.setOnClickListener(viewV -> gotoFarmLocation());
    }

    private void gotoFarmLocation() {
        Log.e("TAG>>", nameTxt.getText().toString());
        String nameStr = nameTxt.getText().toString().trim();
        String addressStr = addressTxt.getText().toString().trim();
        if (nameStr.isEmpty()) {
            Toast.makeText(HomeActivity.this,getResources().getString(R.string.error_name), Toast.LENGTH_LONG).show();
            return;
        }
        if (addressStr.isEmpty()) {
            Toast.makeText(HomeActivity.this,getResources().getString(R.string.error_address), Toast.LENGTH_LONG).show();
            return;
        }
        Common.USERNAME = nameTxt.getText().toString();
        Common.USERADDRESS = addressTxt.getText().toString();
        segmentInit(HomeActivity.this);
        Intent intent = new Intent(HomeActivity.this, kawaHomeActivity.class);
        startActivity(intent);

//        } else {
//            AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
//            alertDialog.setTitle(getResources().getString(com.kawasdk.R.string.app_name));
//            alertDialog.setMessage("Please Enter Name and Address");
//            alertDialog.setIcon(com.kawasdk.R.mipmap.ic_launcher);
//            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    alertDialog.hide();
//                }
//            });
//            alertDialog.show();
//
//        }
    }

    public static void segmentInit(Context context) {
        // Create an analytics client with the given context and Segment write key.
        try {
            Analytics analytics = new Analytics.Builder(context,"3xEbvDbIX1HG9vHY7cOPLtq6e3DuouA6")
                    // Enable this to record certain application events automatically!
                    .trackApplicationLifecycleEvents()
                    // Enable this to record screen views automatically!
                    .recordScreenViews()
                    .build();
            Analytics.setSingletonInstance(analytics);

            Properties properties = new Properties();
            String jString = "{\"user\":{\"name\":" + "\"" + Common.USERNAME + "\"" + ",\"address\":" + "\"" + Common.USERADDRESS + "\"" + "},\"metadata\":{\"message\":\"User Details Saved\"}}";
            JsonObject jsonObject = JsonParser.parseString(jString).getAsJsonObject();
            properties.putValue("data", jsonObject);
            Analytics.with(context).track("Map Initialisation", properties);

        } catch (Exception e) {
            Log.e("catch-error", String.valueOf(e.getMessage()));
            Toast.makeText(context, String.valueOf(e.getMessage()), Toast.LENGTH_LONG).show();
        }
    }


    public void setLocale() {

        String languageToLoad = "en"; // use in fo bahasha lanuage.
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }


}
