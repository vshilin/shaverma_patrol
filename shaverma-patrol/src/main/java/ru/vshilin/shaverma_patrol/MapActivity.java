package ru.vshilin.shaverma_patrol;

import android.app.Activity;
import android.app.Application;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import ru.yandex.yandexmapkit.*;
import ru.yandex.yandexmapkit.overlay.Overlay;
import ru.yandex.yandexmapkit.overlay.OverlayItem;
import ru.yandex.yandexmapkit.utils.GeoPoint;

import java.util.HashMap;
import java.util.List;

/**
 * BalloonOverlay.java
 *
 * This file is a part of the Yandex Map Kit.
 *
 * Version for Android © 2012 YANDEX
 *
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://legal.yandex.ru/mapkit/
 *
 */
public class MapActivity extends Activity  {
    /** Called when the activity is first created. */
    MapController mMapController;
    OverlayManager mOverlayManager;
    ShavermaPatrolApp myApp;
    Drawable markerPic;
    Overlay shavaOverlay;
    ImageView filter;

    Firebase mRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApp = (ShavermaPatrolApp) getApplicationContext();

        setContentView(R.layout.sample);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-3940256099942544~3347511713");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        final MapView mapView = (MapView) findViewById(R.id.map);
        //mapView.showBuiltInScreenButtons(true);
        mapView.showFindMeButton(true);

        filter = (ImageView) findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          final FilterDialog fDialog = FilterDialog.newInstance();
                                          fDialog.setOnDoneListener(new FilterDialog.OnDoneListener() {

                                              @Override
                                              public void onDone() {
                                                  filterObjects();
                                                  filter.setBackgroundResource(
                                                          FilterState.INSTANCE.isDisabled() ? R.drawable.filter_off : R.drawable.filter_on
                                                  );

                                              }

                                          });
                                          fDialog.show(getFragmentManager(), "CancelRequestDialog");
                                      }
                                  });

        mMapController = mapView.getMapController();
        mMapController.setPositionAnimationTo(new GeoPoint(59.939095, 30.315868));

        mOverlayManager = mMapController.getOverlayManager();

        markerPic = getResources().getDrawable(R.drawable.shop1);
        Bitmap b = ((BitmapDrawable) markerPic).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 35, 35, false);
        markerPic = new BitmapDrawable(getResources(), bitmapResized);
    }

    @Override
    protected void onStart(){
        super.onStart();

        mRef = new Firebase("https://shaverma-patrol.firebaseio.com/shava");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot shava : snapshot.getChildren()) {
                    myApp.AllShavas.put(shava.getKey(), new Shaverma(shava));
                }

                showObjects();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    public void showObjects(){
        if (shavaOverlay != null) {
            mOverlayManager.removeOverlay(shavaOverlay);
        }

        // Create a layer of objects for the map
        shavaOverlay = new Overlay(mMapController);

        for (HashMap.Entry<String, Shaverma> entry : myApp.AllShavas.entrySet())
        {
            Shaverma shava = entry.getValue();

            OverlayItem shavaItem = new OverlayItem(new GeoPoint(shava.getGeoPointA(), shava.getGeoPointB()), markerPic);
            // Create a balloon model for the object
            ImageBalloonItem balloonShava = new ImageBalloonItem(this, shavaItem.getGeoPoint(), shava);
            balloonShava.setOnViewClickListener();
            shavaItem.setBalloonItem(balloonShava);

            // Add the object to the layer
            shavaOverlay.addOverlayItem(shavaItem);
        }

        // Add the layer to the map
        mOverlayManager.addOverlay(shavaOverlay);
        mMapController.notifyRepaint();
    }

    public void filterObjects() {
        if (shavaOverlay == null) {return;}

        for (OverlayItem shavaItem : (List<OverlayItem>) shavaOverlay.getOverlayItems()) {
            Shaverma shava = ((ImageBalloonItem) shavaItem.getBalloonItem()).getShaverma();

            shavaItem.setVisible(
                    shava.getScore() <= FilterState.INSTANCE.maxScore &&
                    shava.getScore() >= FilterState.INSTANCE.minScore &&
                    shava.getTaste() <= FilterState.INSTANCE.maxTaste &&
                    shava.getTaste() >= FilterState.INSTANCE.minTaste &&
                    shava.getFill() <= FilterState.INSTANCE.maxFill &&
                    shava.getFill() >= FilterState.INSTANCE.minFill &&
                    shava.getStruct() <= FilterState.INSTANCE.maxStruct &&
                    shava.getStruct() >= FilterState.INSTANCE.minStruct &&
                    shava.getOrig() <= FilterState.INSTANCE.maxOrig &&
                    shava.getOrig() >= FilterState.INSTANCE.minOrig &&
                    shava.getIntPers() <= FilterState.INSTANCE.maxIntpers &&
                    shava.getIntPers() >= FilterState.INSTANCE.minIntpers);
        }

        mMapController.notifyRepaint();
    }
}