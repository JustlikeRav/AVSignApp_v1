package com.avenview.avsignapp_v1.attributes;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SessionAttributes {
    private Integer width, height;
    private String background, hexcode, status;
    private ArrayList<Schedule> scheduleArrayList;

    public SessionAttributes() {
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getHexcode() {
        return hexcode;
    }

    public void setHexcode(String hexcode) {
        this.hexcode = hexcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Schedule> getScheduleArrayList() {
        return scheduleArrayList;
    }

    public void setScheduleArrayList(JSONArray scheduleArray) {
        this.scheduleArrayList = new ArrayList<>();

        for (int i = 0; i < scheduleArray.length(); i++){
            Schedule tempSchedule = new Schedule();
            try {
                JSONObject jsonObject = scheduleArray.getJSONObject(i);
                tempSchedule.setName(jsonObject.getString("name"));
                tempSchedule.setScheduletype(jsonObject.getInt("scheduletype"));
                tempSchedule.setType(jsonObject.getInt("type"));
                tempSchedule.setDays(jsonObject.getJSONArray("days"));
                tempSchedule.setAllday(jsonObject.getBoolean("allday"));
                tempSchedule.setPresentationsArrayList(jsonObject.getJSONArray("presentation"));

                scheduleArrayList.add(tempSchedule);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "SessionAttributes{" +
                "width=" + width +
                ", height=" + height +
                ", background='" + background + '\'' +
                ", hexcode='" + hexcode + '\'' +
                ", status='" + status + '\'' +
                ", scheduleArrayList=" + scheduleArrayList +
                '}';
    }
}
