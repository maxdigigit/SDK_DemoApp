package com.maxdigi.kawasdktest10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class HomeActivity extends AppCompatActivity {
    Button startKawaBtn;
    private static final int PERMISSION_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startKawaBtn = findViewById(R.id.startKawaBtn);
        startKawaBtn.setOnClickListener(viewV -> gotoFarmLocation());

    }

    private void gotoFarmLocation() {
       Intent intent = new Intent(HomeActivity.this, kawaHomeActivity.class);
        startActivity(intent);

    }



}
