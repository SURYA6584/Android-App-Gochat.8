package com.example.fedupmini;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fedupmini.databinding.ActivityMapsBinding;
import com.example.fedupmini.databinding.ActivityOtpSendBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class maps extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap myMap;
    String country, state, city;
    double longititue, latitude;
    private final static int REQUEST_CODE = 100;

      ActivityMapsBinding binding;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLastLocation();


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        myMap = googleMap;

        LatLng Bangalore = new LatLng(13, 77);
        myMap.moveCamera(CameraUpdateFactory.newLatLng(Bangalore));
    }

    public void getLastLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            if (location != null) {
                                Geocoder geocoder = new Geocoder(maps.this, Locale.getDefault());
                                List<Address> address = null;
                                try {
                                    address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                    latitude = address.get(0).getLatitude();
                                    longititue = address.get(0).getLongitude();
                                    country = address.get(0).getCountryName();
                                    city = address.get(0).getLocality();
                                    state = address.get(0).getAddressLine(0);
                                    LatLng place = new LatLng(latitude, longititue);
                                    myMap.addMarker(new MarkerOptions().position(place).title("Bangalore"));
                                    myMap.moveCamera(CameraUpdateFactory.newLatLng(place));
                                   binding.TG165.setText(state);

                                    final Handler handler = new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            // Start the intent to MainActivity after 3 seconds
                                            Intent intent = new Intent(maps.this, MainActivity.class);
                                            intent.putExtra("CUR_Address", state);
                                            startActivity(intent);
                                        }
                                    }, 3000);


                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                            }
                        }
                    });


        } else {


            askPermission();
        }

    }


    private void askPermission() {

        ActivityCompat.requestPermissions(maps.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode==REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getLastLocation();
            }else{

                Toast.makeText(this ,"Permission Required", Toast.LENGTH_SHORT ).show();
            }
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }




}


