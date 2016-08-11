package ru.vshilin.shaverma_patrol;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;

import java.util.HashMap;

/**
 * Created by helpdesk on 01.08.2016.
 */
public class Shaverma {
    HashMap<String, String> attrMap;

    public Shaverma(DataSnapshot snapshot){
        attrMap = new HashMap<String, String>();

        for (DataSnapshot ch : snapshot.getChildren()) {
            attrMap.put(ch.getKey(), ch.getValue(String.class));
        }
    }

    public String getAddress() {
        return attrMap.get("address");
    }

    public Double getGeoPointA() {
        return Double.parseDouble(attrMap.get("geoPointA").replace(',', '.'));
    }

    public Double getGeoPointB() {
        return Double.parseDouble(attrMap.get("geoPointB").replace(',', '.'));
    }

    public String getYoutubeLink() {
        return attrMap.get("youtubeLink");
    }

    public Float getTaste() {
        return (Float.parseFloat(attrMap.get("taste_x").replace(',', '.')) +
                Float.parseFloat(attrMap.get("taste_k").replace(',', '.')) +
                Float.parseFloat(attrMap.get("taste_m").replace(',', '.'))) / 3;
    }

    public Float getFill() {
        return (Float.parseFloat(attrMap.get("fill_x").replace(',', '.')) +
                Float.parseFloat(attrMap.get("fill_k").replace(',', '.')) +
                Float.parseFloat(attrMap.get("fill_m").replace(',', '.'))) / 3;
    }

    public Float getStruct() {
        return (Float.parseFloat(attrMap.get("struct_x").replace(',', '.')) +
                Float.parseFloat(attrMap.get("struct_k").replace(',', '.')) +
                Float.parseFloat(attrMap.get("struct_m").replace(',', '.'))) / 3;
    }

    public Float getOrig() {
        return (Float.parseFloat(attrMap.get("orig_x").replace(',', '.')) +
                Float.parseFloat(attrMap.get("orig_k").replace(',', '.')) +
                Float.parseFloat(attrMap.get("orig_m").replace(',', '.'))) / 3;
    }

    public Float getIntPers() {
        return (Float.parseFloat(attrMap.get("int_x").replace(',', '.')) +
                Float.parseFloat(attrMap.get("int_k").replace(',', '.')) +
                Float.parseFloat(attrMap.get("int_m").replace(',', '.'))) / 3;
    }


    public Float getScore() {
        return
                (
                        Float.parseFloat(attrMap.get("taste_x").replace(',', '.')) + Float.parseFloat(attrMap.get("taste_k").replace(',', '.')) + Float.parseFloat(attrMap.get("taste_m").replace(',', '.')) +
                        Float.parseFloat(attrMap.get("fill_x").replace(',', '.')) + Float.parseFloat(attrMap.get("fill_k").replace(',', '.')) + Float.parseFloat(attrMap.get("fill_m").replace(',', '.')) +
                        Float.parseFloat(attrMap.get("struct_x").replace(',', '.')) + Float.parseFloat(attrMap.get("struct_k").replace(',', '.')) + Float.parseFloat(attrMap.get("struct_m").replace(',', '.')) +
                        Float.parseFloat(attrMap.get("orig_x").replace(',', '.')) + Float.parseFloat(attrMap.get("orig_k").replace(',', '.')) + Float.parseFloat(attrMap.get("orig_m").replace(',', '.')) +
                        Float.parseFloat(attrMap.get("int_x").replace(',', '.')) + Float.parseFloat(attrMap.get("int_k").replace(',', '.')) + Float.parseFloat(attrMap.get("int_m").replace(',', '.')))/15;
    }
}
