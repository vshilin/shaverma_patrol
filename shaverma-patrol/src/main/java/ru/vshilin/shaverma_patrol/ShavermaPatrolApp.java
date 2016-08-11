package ru.vshilin.shaverma_patrol;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by helpdesk on 01.08.2016.
 */
public class ShavermaPatrolApp extends android.app.Application {

    public HashMap<String, Shaverma> AllShavas;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        AllShavas = new HashMap<String, Shaverma>();
    }
}
