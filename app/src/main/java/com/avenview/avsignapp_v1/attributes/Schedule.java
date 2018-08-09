package com.avenview.avsignapp_v1.attributes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Schedule {
    private String name;
    private Integer scheduletype, type;
    private ArrayList<Integer> days;
    private Boolean allday;
    private ArrayList<Presentation> presentationsArrayList;

    public Schedule() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScheduletype() {
        return scheduletype;
    }

    public void setScheduletype(Integer scheduletype) {
        this.scheduletype = scheduletype;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ArrayList<Integer> getDays() {
        return days;
    }

    public void setDays(JSONArray daysArray) throws JSONException {
        this.days = new ArrayList<>();
        for (int j = 0; j < daysArray.length(); j++)
            this.days.add(daysArray.getInt(j));
    }

    public Boolean getAllday() {
        return allday;
    }

    public void setAllday(Boolean allday) {
        this.allday = allday;
    }

    public ArrayList<Presentation> getPresentationsArrayList() {
        return presentationsArrayList;
    }

    public void setPresentationsArrayList(JSONArray presentationsArray) {
        this.presentationsArrayList = new ArrayList<>();

        for (int i = 0; i < presentationsArray.length(); i++){
            Presentation temppresentation = new Presentation();
            try {
                JSONObject jsonObject = presentationsArray.getJSONObject(i);
                temppresentation.setName(jsonObject.getString("name"));
                temppresentation.setDuration(jsonObject.getInt("duration"));
                temppresentation.setVolume(jsonObject.getInt("volume"));
                temppresentation.setZoneArrayList(jsonObject.getJSONArray("zone"));

                presentationsArrayList.add(temppresentation);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
