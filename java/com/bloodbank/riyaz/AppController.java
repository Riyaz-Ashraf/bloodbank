package com.bloodbank.riyaz;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by riyaz-1396 on 04/04/17.
 */

public class AppController extends Application {
    static AppController instance;
    static ArrayList<String> locationList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("--on app", "create");
        this.instance = this;
        storeData(this);
    }

    public static AppController getInstance() {
        return instance;
    }

    public static final String PREFS_NAME = "MyApp_Settings";

    public static void storeData(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor countryList = settings.edit();
        Set<String> countryListSet = new HashSet<String>();
        countryListSet.add("India");
        countryListSet.add("Sri Lanka");
        countryListSet.add("Malaysia");
        countryListSet.add("Saudi Arabia");
        countryListSet.add("UAE");
        countryListSet.add("Bahrain");
        countryListSet.add("Oman");
        countryListSet.add("Kuwait");
        countryListSet.add("Qatar");
        countryList.putStringSet("countryList", countryListSet);
        countryList.commit();

//        getLocationList(countryListSet);


        SharedPreferences.Editor INDprovinceList = settings.edit();
        Set<String> INDprovinceListSet = new HashSet<String>();
        INDprovinceListSet.add("TamilNadu");
        INDprovinceListSet.add("Pondicherry");
        INDprovinceListSet.add("Karnataka");
        INDprovinceListSet.add("Maharashtra");
        INDprovinceListSet.add("Kerala");
        INDprovinceListSet.add("Andhra");
        INDprovinceList.putStringSet("India", INDprovinceListSet);
        INDprovinceList.commit();
//        getLocationList(INDprovinceListSet);

        SharedPreferences.Editor SLprovinceList = settings.edit();
        Set<String> SLprovinceListSet = new HashSet<String>();
        SLprovinceListSet.add("Sri Lanka");
        SLprovinceList.putStringSet("Sri Lanka", SLprovinceListSet);
        SLprovinceList.commit();

        SharedPreferences.Editor KUprovinceList = settings.edit();
        Set<String> KUprovinceListSet = new HashSet<String>();
        KUprovinceListSet.add("Kuwait North");
        KUprovinceListSet.add("Kuwait Center");
        KUprovinceListSet.add("Kuwait South");
        KUprovinceList.putStringSet("Kuwait", KUprovinceListSet);
        KUprovinceList.commit();

        SharedPreferences.Editor SAprovinceList = settings.edit();
        Set<String> SAprovinceListSet = new HashSet<String>();
        SAprovinceListSet.add("Riyadh");
        SAprovinceListSet.add("Jeddah");
        SAprovinceListSet.add("Dammam");
        SAprovinceListSet.add("Al Hasa");
        SAprovinceListSet.add("Al Qassim");
        SAprovinceList.putStringSet("Saudi Arabia", SAprovinceListSet);
        SAprovinceList.commit();

        SharedPreferences.Editor UAEprovinceList = settings.edit();
        Set<String> UAEprovinceListSet = new HashSet<String>();
        UAEprovinceListSet.add("Dubai");
        UAEprovinceListSet.add("UAE North");
        UAEprovinceListSet.add("Abu Dhabi");
        UAEprovinceList.putStringSet("UAE", UAEprovinceListSet);
        UAEprovinceList.commit();

        SharedPreferences.Editor OMANprovinceList = settings.edit();
        Set<String> OMANprovinceListSet = new HashSet<String>();
        OMANprovinceListSet.add("Muscat");
        OMANprovinceList.putStringSet("Oman", OMANprovinceListSet);
        OMANprovinceList.commit();

        SharedPreferences.Editor BHprovinceList = settings.edit();
        Set<String> BHprovinceListSet = new HashSet<String>();
        BHprovinceListSet.add("Bahrain");
        BHprovinceList.putStringSet("Bahrain", BHprovinceListSet);
        BHprovinceList.commit();

        SharedPreferences.Editor QRprovinceList = settings.edit();
        Set<String> QRprovinceListSet = new HashSet<String>();
        QRprovinceListSet.add("Qatar");
        QRprovinceList.putStringSet("Qatar", QRprovinceListSet);
        QRprovinceList.commit();

        SharedPreferences.Editor MAprovinceList = settings.edit();
        Set<String> MAprovinceListSet = new HashSet<String>();
        MAprovinceListSet.add("Malaysia");
        MAprovinceList.putStringSet("Malaysia", MAprovinceListSet);
        MAprovinceList.commit();

//-----District list provincewise

        SharedPreferences.Editor TNdistLst = settings.edit();
        Set<String> TNdistLstSet = new HashSet<String>();
        TNdistLstSet.add("Chennai North");
        TNdistLstSet.add("Chennai South");
        TNdistLstSet.add("Cuddalore North");
        TNdistLstSet.add("Cuddalore South");
        TNdistLstSet.add("Dharmapuri");
        TNdistLstSet.add("Dindugal");
        TNdistLstSet.add("Erode");
        TNdistLstSet.add("Kanyakumari");
        TNdistLstSet.add("Kanchi West");
        TNdistLstSet.add("Kanchi East");
        TNdistLstSet.add("Karur");
        TNdistLstSet.add("Krishnagiri");
        TNdistLstSet.add("Kovai North");
        TNdistLstSet.add("Kovai South");
        TNdistLstSet.add("Madurai");
        TNdistLstSet.add("Madurai");
        TNdistLstSet.add("Nagai North");
        TNdistLstSet.add("Nagai South");
        TNdistLstSet.add("Namakkal");
        TNdistLstSet.add("Nellai West");
        TNdistLstSet.add("Nellai East");
        TNdistLstSet.add("Nilgiris");
        TNdistLstSet.add("Pudhukottai");
        TNdistLstSet.add("Perambalur");
        TNdistLstSet.add("Ramanathapuram North");
        TNdistLstSet.add("Ramanathapuram South");
        TNdistLstSet.add("Salem");
        TNdistLstSet.add("Sivagangai");
        TNdistLstSet.add("Tirupur");
        TNdistLstSet.add("Trichy");
        TNdistLstSet.add("Tuticorin");
        TNdistLstSet.add("Thanjavur North");
        TNdistLstSet.add("Thanjavur South");
        TNdistLstSet.add("Theni");
        TNdistLstSet.add("Thiruvallur West");
        TNdistLstSet.add("Thiruvallur East");
        TNdistLstSet.add("Thiruvannamalai");
        TNdistLstSet.add("Thiruvarur North");
        TNdistLstSet.add("Thiruvarur South");
        TNdistLstSet.add("Vellore West");
        TNdistLstSet.add("Vellore East");
        TNdistLstSet.add("Virudhunagar");
        TNdistLstSet.add("Villupuram West");
        TNdistLstSet.add("Villupuram East");
        TNdistLst.putStringSet("TamilNadu", TNdistLstSet);
        TNdistLst.commit();
//        getLocationList(TNdistLstSet);


        SharedPreferences.Editor PYdistLst = settings.edit();
        Set<String> PYdistLstSet = new HashSet<String>();
        PYdistLstSet.add("Karaikal");
        PYdistLstSet.add("Pondicherry");
        PYdistLst.putStringSet("Pondicherry", PYdistLstSet);
        PYdistLst.commit();
//        getLocationList(PYdistLstSet);

        SharedPreferences.Editor MHdistLst = settings.edit();
        Set<String> MHdistLstSet = new HashSet<String>();
        MHdistLstSet.add("Mumbai");
        MHdistLst.putStringSet("Maharashtra", MHdistLstSet);
        MHdistLst.commit();

        SharedPreferences.Editor KAdistLst = settings.edit();
        Set<String> KAdistLstSet = new HashSet<String>();
        KAdistLstSet.add("Bengaluru");
        KAdistLst.putStringSet("Karnataka", KAdistLstSet);
        KAdistLst.commit();

        SharedPreferences.Editor APdistLst = settings.edit();
        Set<String> APdistLstSet = new HashSet<String>();
        APdistLstSet.add("Andhra North");
        APdistLstSet.add("Andhra South");
        APdistLst.putStringSet("Andhra", APdistLstSet);
        APdistLst.commit();

        SharedPreferences.Editor KLdistLst = settings.edit();
        Set<String> KLdistLstSet = new HashSet<String>();
        KLdistLstSet.add("Kerala North");
        KLdistLstSet.add("Kerala South");
        KLdistLst.putStringSet("Kerala", KLdistLstSet);
        KLdistLst.commit();


    }

//    private static void getLocationList(Set<String> stringSet) {
//        locationList.clear();
//        locationList = new ArrayList<String>(Arrays.asList(stringSet.toArray(new String[stringSet.size()])));
//    }

    public Set<String> fetchDetails(String name) {
        return getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getStringSet(name, null);
    }

}
