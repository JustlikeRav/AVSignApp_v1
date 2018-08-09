package com.avenview.avsignapp_v1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.avenview.avsignapp_v1.attributes.Schedule;
import com.avenview.avsignapp_v1.attributes.SessionAttributes;
import com.avenview.avsignapp_v1.attributes.Presentation;
import com.avenview.avsignapp_v1.attributes.Zone;
import com.avenview.avsignapp_v1.fullscreen_utilities.Fullscreen;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RequestQueue requestQueue;
    private SessionAttributes mSessionAttributes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        mSessionAttributes = new SessionAttributes();

        Fullscreen mFullscreen = new Fullscreen(this);
        mFullscreen.setFullscreen();
        mFullscreen.registerSystemUiVisibility();

        setAttributes();
    }

    public void setAttributes() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "https://www.avenview.com/test/AVSignCloudAPI.php", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mSessionAttributes.setWidth(response.getInt("width"));
                    mSessionAttributes.setHeight(response.getInt("height"));
                    mSessionAttributes.setBackground(response.getString("background"));
                    mSessionAttributes.setHexcode(response.getString("hexcode"));
                    mSessionAttributes.setStatus(response.getString("status"));
                    mSessionAttributes.setScheduleArrayList(response.getJSONArray("schedule"));

                    testSignage();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) { }
        });
        requestQueue.add(request);
    }

    private void testSignage() {
        Schedule schedule = mSessionAttributes.getScheduleArrayList().get(0);
        Presentation presentation = schedule.getPresentationsArrayList().get(0);
        ArrayList<Zone> ZoneArray = presentation.getZoneArrayList();

        RelativeLayout rl = findViewById(R.id.rl);
        rl.setBackgroundColor(Color.parseColor(mSessionAttributes.getBackground()));

        for(int i = 0; i < ZoneArray.size(); i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(ZoneArray.get(i).getUrl()).into(imageView);
            RelativeLayout.LayoutParams lp_tv = new RelativeLayout.LayoutParams(
                    ZoneArray.get(i).getWeight(),
                    ZoneArray.get(i).getHeight()
            );

            lp_tv.setMargins(ZoneArray.get(i).getLeft(), ZoneArray.get(i).getTop(), 0, 0);

            imageView.setLayoutParams(lp_tv);

            rl.addView(imageView);
        }
    }
}
