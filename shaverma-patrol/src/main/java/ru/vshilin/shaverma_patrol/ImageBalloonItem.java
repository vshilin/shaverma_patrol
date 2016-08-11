package ru.vshilin.shaverma_patrol;



import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import ru.yandex.yandexmapkit.overlay.balloon.BalloonItem;
import ru.yandex.yandexmapkit.overlay.balloon.OnBalloonListener;
import ru.yandex.yandexmapkit.utils.GeoPoint;

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
public class ImageBalloonItem extends BalloonItem implements OnBalloonListener{
    Context mContext;
    Shaverma shava;

    public ImageBalloonItem(Context context, GeoPoint geoPoint, Shaverma shava) {
        super(context, geoPoint);
        mContext = context;
        this.shava = shava;

        ((TextView) model.findViewById(R.id.score)).setText(String.format("%.2f", shava.getScore()) + " " + mContext.getResources().getString(R.string.rating_suffix));
        ((TextView) model.findViewById(R.id.address)).setText(shava.getAddress());

        ((RatingBar) model.findViewById(R.id.TasteRating)).setRating(shava.getTaste());
        ((RatingBar) model.findViewById(R.id.FillRating)).setRating(shava.getFill());
        ((RatingBar) model.findViewById(R.id.StructRating)).setRating(shava.getStruct());
        ((RatingBar) model.findViewById(R.id.OrigRating)).setRating(shava.getOrig());
        ((RatingBar) model.findViewById(R.id.IntpersRating)).setRating(shava.getIntPers());
    }

    @Override
    public void inflateView(Context context){

        LayoutInflater inflater = LayoutInflater.from( context );
        model = (ViewGroup)inflater.inflate(R.layout.balloon_images_layout, null);
    }

    
    public void setOnViewClickListener(){
        setOnBalloonViewClickListener(R.id.youtube_link, this);
    }

    @Override
    public void onBalloonViewClick(BalloonItem item, View view) {
        if (view.getTag().equals("YOUTUBE")) {
            mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(shava.getYoutubeLink())));
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            dialog.setTitle((String)view.getTag());
            dialog.show();
        }
    }

    @Override
    public void onBalloonShow(BalloonItem balloonItem) {
    }

    @Override
    public void onBalloonHide(BalloonItem balloonItem) {

    }

    @Override
    public void onBalloonAnimationStart(BalloonItem balloonItem) {

    }

    @Override
    public void onBalloonAnimationEnd(BalloonItem balloonItem) {
    }

    public Shaverma getShaverma() {
        return shava;
    }
}