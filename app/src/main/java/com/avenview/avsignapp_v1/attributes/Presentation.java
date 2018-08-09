package com.avenview.avsignapp_v1.attributes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Presentation {
    private String name;
    private Integer duration, volume;
    private ArrayList<Zone> zoneArrayList;

    Presentation() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public ArrayList<Zone> getZoneArrayList() {
        return zoneArrayList;
    }

    public void setZoneArrayList(JSONArray zoneArray) {
        this.zoneArrayList = new ArrayList<>();

        for (int i = 0; i < zoneArray.length(); i++){
            Zone tempzone = new Zone();
            try {
                JSONObject jsonObject = zoneArray.getJSONObject(i);
                tempzone.setName(jsonObject.getString("name"));
                tempzone.setDuration(jsonObject.getInt("duration"));
                tempzone.setType(jsonObject.getInt("type"));
                tempzone.setUrl(jsonObject.getString("url"));
                tempzone.setHeight(jsonObject.getInt("height"));
                tempzone.setWeight(jsonObject.getInt("weight"));
                tempzone.setLeft(jsonObject.getInt("left"));
                tempzone.setTop(jsonObject.getInt("top"));

                zoneArrayList.add(tempzone);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
