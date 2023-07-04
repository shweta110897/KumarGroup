package com.example.kumarGroup.EmployeeTracker.ak;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.kumarGroup.R;
import com.example.kumarGroup.directionhelpers.FetchURL;
import com.example.kumarGroup.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class LineDrawMapActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    Button getDirection;
    private Polyline currentPolyline;
    LatLng latLng;
    String lat0,lat1,long0,long1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_draw_map);

        lat0 = getIntent().getStringExtra("lat0");
        lat1 = getIntent().getStringExtra("lat1");
        long0 = getIntent().getStringExtra("long0");
        long1 = getIntent().getStringExtra("long1");

        Log.d("TAG", "lat0 "+lat0+ " lat1 "+lat1+" long0 "+long0 +" long1 "+long1);

        latLng = new LatLng(Double.parseDouble(lat0),Double.parseDouble(long0));

        getDirection = findViewById(R.id.btnGetDirection);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), "run", Toast.LENGTH_SHORT).show();
                new FetchURL(LineDrawMapActivity.this)
                        .execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
               // Toast.makeText(getApplicationContext(), "run", Toast.LENGTH_SHORT).show();
            }
        });
        //27.658143,85.3199503
        //27.667491,85.3208583
        place1 = new MarkerOptions().position(new LatLng(Double.parseDouble(lat1),Double.parseDouble(long1))).title("Location 1");
        place2 = new MarkerOptions().position(new LatLng(Double.parseDouble(lat0),Double.parseDouble(long0))).title("Location 2");

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapNearBy);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        mMap.addMarker(place1);
        mMap.addMarker(place2);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        Log.d("TAG", "urlget: "+url);
        return url;
    }
}