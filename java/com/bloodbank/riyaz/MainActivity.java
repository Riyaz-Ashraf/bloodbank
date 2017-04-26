package com.bloodbank.riyaz;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    RecyclerView countryList;
    RegionAdapter mAdapter;
    Toolbar toolbar;
    Context context;
    private int mTotalScrolled = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countryList = (RecyclerView) findViewById(R.id.country_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("Countries");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Countries");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<String> name = new ArrayList<>();
        try {
            Set<String> distSet = AppController.getInstance().fetchDetails("countryList");
            name = new ArrayList<>(Arrays.asList(distSet.toArray(new String[distSet.size()])));
            Collections.sort(name, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    return s1.compareToIgnoreCase(s2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        mAdapter = new RegionAdapter(name);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        countryList.setLayoutManager(mLayoutManager);
        countryList.setItemAnimator(new DefaultItemAnimator());
        countryList.setAdapter(mAdapter);

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
                finish();
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


    public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.MyViewHolder> {
        ArrayList<String> name;

        RegionAdapter(ArrayList<String> list) {
            this.name = list;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView countryName;
            LinearLayout countryDetails;

            public MyViewHolder(View view) {
                super(view);
                countryName = (TextView) view.findViewById(R.id.country_name);
                countryDetails = (LinearLayout) view.findViewById(R.id.country_details);
                context = itemView.getContext();
            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.country_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            try {

                holder.countryName.setText(name.get(position));
                holder.countryDetails.setTag(name.get(position));
                holder.countryDetails.setOnClickListener(getLocDetLis);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public int getItemCount() {
            return name.size();
        }
    }

    View.OnClickListener getLocDetLis = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent provincePage = new Intent(MainActivity.this, GetProvinceActivity.class);
            provincePage.putExtra("countryName", v.getTag().toString());
            MainActivity.this.startActivity(provincePage);
        }
    };
}