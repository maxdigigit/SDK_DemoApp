package com.maxdigi.kawasdktest10;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.JsonObject;
import com.kawasdk.Fragment.fragmentFarmLocation;
import com.kawasdk.Utils.Common;
import com.kawasdk.Utils.InterfaceKawaEvents;
import com.kawasdk.Utils.KawaMap;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.kawasdk.R.layout.activity_kawa_home);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.kawaMapView, new fragmentFarmLocation()).commit();
        KawaMap.initKawaMap("kawa_PHVlzp8lwgcFVWD6gWzdYg");
        KawaMap.isMergeEnable = true;
        KawaMap.isEditEnable = false;
        KawaMap.isFarmDetailsEnable = true;
        KawaMap.isOtherFarmDetailsEnable = false;
        KawaMap.isFormEnable = false;
        KawaMap.isSaveResultEnable = false;
        KawaMap.isBahasaEnable = false;
    }
    @Override
    public void initKawaMap(boolean isValid) {
        Log.e("TAG", "initKawaMap: >>> " + isValid);
        if (isValid == true) {
            KawaMap.setFooterBtnBgColorAndTextColor(Color.BLUE, Color.WHITE);
            KawaMap.setInnerBtnBgColorAndTextColor(Color.WHITE, Color.BLACK);
            KawaMap.setHeaderBgColorAndTextColor(Color.BLUE, Color.WHITE);
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