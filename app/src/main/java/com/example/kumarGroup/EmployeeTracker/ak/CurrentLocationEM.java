package com.example.kumarGroup.EmployeeTracker.ak;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.example.kumarGroup.R;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kumarGroup.WebService;
import com.example.kumarGroup.emp_tracking_mode;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CurrentLocationEM extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    TextView lattimeupdate;
    CardView card_google_map;
    String lat,longg;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location_em);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);

        //initilize fused location
        client = LocationServices.getFusedLocationProviderClient(this);
        lattimeupdate = findViewById(R.id.lattimeupdate);
        card_google_map = findViewById(R.id.card_google_map);


        String state = getIntent().getStringExtra("state");
        String stateId = getIntent().getStringExtra("stateId");

        //Toast.makeText(CurrentLocationEM.this, ""+state+" id "+stateId, Toast.LENGTH_LONG).show();

        WebService.getClient().get_log_lang_emp_tracking(stateId).enqueue(new Callback<emp_tracking_mode>() {
            @Override
            public void onResponse(Call<emp_tracking_mode> call, Response<emp_tracking_mode> response) {

                if (0 != response.body().getData().size()) {
                    lat = response.body().getData().get(0).getLatitude();
                    longg = response.body().getData().get(0).getLongitude();

                    lattimeupdate.setText("Last active " + response.body().getData().get(0).getTime());

                    //check permission
                    if (ActivityCompat.checkSelfPermission(CurrentLocationEM.this, Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        getCurrentLocation();
                    } else {
                        ActivityCompat.requestPermissions(CurrentLocationEM.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

                    }

                }
                else {
//                    Utils.showErrorToast(CurrentLocationEM.this,"No Data Found");
                    Toast.makeText(CurrentLocationEM.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CurrentLocationEM.this,Select_employee.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<emp_tracking_mode> call, Throwable t) {
                Toast.makeText(CurrentLocationEM.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        card_google_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CurrentLocationEM.this,MapDetailsEMActivity.class)
                .putExtra("id",stateId));
            }
        });
    }
    private void getCurrentLocation() {
        //initialize task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //sync map
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            //initialize lat lng
                            LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(longg));
                            Log.d("TAG", "onMapReady: "+latLng.toString());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("i am there");
                            //zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                            //add marker on map
                            googleMap.addMarker(options);
                        }
                    });
                }
                else
                {
                    //location is null
                    LocationRequest locationRequest = new LocationRequest()
                            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                            .setInterval(10000)
                            .setFastestInterval(10000)
                            .setNumUpdates(1);

                    LocationCallback locationCallback = new LocationCallback() {
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            //  super.onLocationResult(locationResult);

                            Location location1 = locationResult.getLastLocation();

                            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(GoogleMap googleMap) {
                                    //initialize lat lng
                                    LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(longg));
                                    MarkerOptions options = new MarkerOptions().position(latLng).title("i am there");
                                    //zoom map
                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                                    //add marker on map
                                    googleMap.addMarker(options);
                                }
                            });
                        }
                    };

                    if (ActivityCompat.checkSelfPermission(CurrentLocationEM.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CurrentLocationEM.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    client.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44 && grantResults.length > 0 && grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            //when permission granted
            //call method
            getCurrentLocation();
        }
    }
}