package com.bloodbank.riyaz;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by riyaz-1396 on 08/04/17.
 */


public class GetProfileActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    private static final int PERMISSIONS_REQUEST_PHONE_CALL = 100;
    Toolbar toolbar;
    String distName = null;
    String countName = null;
    TextView contName = null;
    TextView contDesgn = null;
    TextView contPhon = null;
    TextView contDist = null;
    TextView distLbl =null;
    String name;
    String dest;
    String phoneNo;
    String message;
    Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        contName = (TextView) findViewById(R.id.cont_name);
        contDesgn = (TextView) findViewById(R.id.desgn);
        contPhon = (TextView) findViewById(R.id.phone);
        contDist = (TextView) findViewById(R.id.dist);
        distLbl = (TextView) findViewById(R.id.distLabel);

        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        if (getIntent() != null && getIntent().hasExtra("distName")) {
            distName = getIntent().getStringExtra("distName");
            countName = getIntent().getStringExtra("countryName");
        }
        String distNamefor_xml= distName.toLowerCase();
        distNamefor_xml=distNamefor_xml.replaceAll(" ","_");

        int holderint = getResources().getIdentifier(distNamefor_xml, "array",
                this.getPackageName());

        String profile[] = getResources().getStringArray(holderint);

        name = profile[0];
        phoneNo =profile[1] ;
        dest = profile[2];

        contName.setText(name);
        contPhon.setText(phoneNo);
        contDesgn.setText(dest);
        contDist.setText(distName);
        if(!countName.equals("India")){
            distLbl.setText("Province");
        }



        final ImageView call = (ImageView) findViewById(R.id.callIcn);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.my_anim_set);
                call.startAnimation(animFadein);
                makeCall();
            }
        });


        final ImageView sms = (ImageView) findViewById(R.id.smsIcn);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.my_anim_set);
                sms.startAnimation(animFadein);
                new AlertDialog.Builder(GetProfileActivity.this)
                        .setTitle("Send SMS?")
                        .setMessage("SMS charge may be incurred as per your network plan!")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // continue with ok
                                sendSMSMessage();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(R.drawable.ic_alert_icon)
                        .show();
            }
        });

        final ImageView share = (ImageView) findViewById(R.id.shrIcn);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.my_anim_set);
                share.startAnimation(animFadein);
                Intent i=new Intent(android.content.Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Urgent Need");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "Emergency blood organizer - Medical Wing \nName :"+name+"\nDesignation :"+dest+"\nContact :"+phoneNo);
                startActivity(Intent.createChooser(i,"Share via"));
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                aboutUs();
                return true;
            case R.id.rate:
                rateApp();
                return true;
            case android.R.id.home:
                if(distName.equals("India")){
                Intent homeIntent = new Intent(this, GetDistrictActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                homeIntent.putExtra("provinceName",getIntent().getStringExtra("provinceName"));
                homeIntent.putExtra("countryName",getIntent().getStringExtra("countryName"));
                startActivity(homeIntent);
                }else{
                    Intent homeIntent = new Intent(this, GetProvinceActivity.class);
                    homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    homeIntent.putExtra("provinceName",getIntent().getStringExtra("provinceName"));
                    homeIntent.putExtra("countryName",getIntent().getStringExtra("countryName"));
                    startActivity(homeIntent);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void aboutUs() {
        final PopupWindow mpopup;
        View popUpView = getLayoutInflater().inflate(R.layout.popup, null); // inflating popup layout
        LinearLayout popupParent = (LinearLayout) popUpView.findViewById(R.id.popup_layout_parent);
        mpopup = new PopupWindow(popUpView, LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT, true);
        mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
        mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0); // Displaying popup
        mpopup.setOutsideTouchable(true);
        mpopup.setFocusable(true);
        mpopup.setBackgroundDrawable(new BitmapDrawable());
        popupParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mpopup.dismiss();
                } catch (Exception e) {

                }
            }
        });
    }

    private void rateApp() {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try {
            startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }






    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {


        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
//                    sendSMSMessage();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
            case PERMISSIONS_REQUEST_PHONE_CALL:{
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted
                    makeCall();
                } else {
                    Toast.makeText(this, "Sorry!!! Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
            default:break;
        }

}

    private void makeCall() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST_PHONE_CALL);
        } else {
            //Open call function
//            String number = "+270744105238";
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNo));
            startActivity(intent);
        }
    }

    protected void sendSMSMessage() {
        message = "Urgent! Blood donor need!! Message shared via app";

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        else {
//            Toast.makeText(getApplicationContext(), "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
            SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNo, null, message, null, null);
                Toast.makeText(getApplicationContext(), "SMS sent.",
                        Toast.LENGTH_LONG).show();
        }
    }


}
