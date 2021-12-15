package com.maxdigi.kawasdktest10;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kawasdk.Fragment.fragmentFarmLocation;
import com.kawasdk.Utils.Common;
import com.kawasdk.Utils.InterfaceKawaEvents;
import com.kawasdk.Utils.KawaMap;
import com.segment.analytics.Analytics;
import com.segment.analytics.Properties;
import com.smartlook.sdk.smartlook.Smartlook;

import org.json.JSONObject;

import java.util.List;

public class kawaHomeActivity extends AppCompatActivity implements InterfaceKawaEvents  {
    public static final String TAG = "Kawa";

//    MaxDigi's API Key
//    kawa_RLkNIPN-z3TeWb8AWv089w
//    Client's API Key
//    kawa_TaWxc_Kq-_aVTZb3BunPhg

//    Client New API Key 3(I) -
//    kawa_y9zuyWNPjy2k_vGQz5w-pA
//    Client New API Key 2 (S) -
//    kawa_S974OwHCyXfipDmQOjvy2w

    // 434573882bf2d7079548eeb5344cd61e82131e76 kawa smartlook
    // 81ab38327bb3cbabb3f67fca628c0849d034aec0 maxdigi smartlook
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.kawasdk.R.layout.activity_kawa_home);
        KawaMap.initKawaMap(getResources().getString(R.string.kawa_api_key)); // kawa api key
        KawaMap.initSegment(kawaHomeActivity.this,getResources().getString(R.string.segment_api_key)); //Segment api key
        KawaMap.initSmartlook(getResources().getString(R.string.smartlook_api_key)); // Smartlook api key
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.kawaMapView, new fragmentFarmLocation()).commit();
    }
    @Override
    public void initKawaMap(boolean isValid) {
        Log.e("TAG", "initKawaMap: >>> " + isValid);
        if (isValid == true) {
            KawaMap.setFooterBtnBgColorAndTextColor(Color.BLUE, Color.WHITE);
            KawaMap.setInnerBtnBgColorAndTextColor(Color.WHITE, Color.BLACK);
            KawaMap.setHeaderBgColorAndTextColor(Color.BLUE, Color.WHITE);
            KawaMap.isEditEnable = false;
            KawaMap.isMergeEnable = true;
            KawaMap.isFarmDetailsEnable = true;
            KawaMap.isOtherFarmDetailsEnable = false;
            KawaMap.isFormEnable = false;
            KawaMap.isSaveResultEnable = true;
            KawaMap.isBahasaEnable = false;
            KawaMap.isFlyToLocationEnable = false;

        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(kawaHomeActivity.this).create();
            alertDialog.setTitle(getResources().getString(com.kawasdk.R.string.app_name));
            alertDialog.setMessage("KAWA api key not found.");
            alertDialog.setIcon(com.kawasdk.R.mipmap.ic_launcher);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertDialog.show();
            //Toast.makeText(this, "KAWA api key not found.", Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public void onkawaSelect(JsonObject data) {
        //Toast.makeText(this, String.valueOf(data), Toast.LENGTH_LONG).show();
        Log.e(TAG, "SelectJson: " + data);
    }

    @Override
    public void onkawaUpdate(JSONObject data) {
        Log.e(TAG, String.valueOf(data));
    }

    @Override
    public void onkawaSubmit(String data) {
           // Toast.makeText(this, (CharSequence) data, Toast.LENGTH_LONG).show();
            Log.e(TAG, "SubmitJson: " + data);

    }
}