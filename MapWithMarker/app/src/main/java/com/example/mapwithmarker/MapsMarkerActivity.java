package com.example.mapwithmarker;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * An activity that displays a Google map with a marker (pin) to indicate a particular location.
 */
public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private LatLng markerPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map when it's available.
     * The API invokes this callback when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user receives a prompt to install
     * Play services inside the SupportMapFragment. The API invokes this method after the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        PackageManager pm = getBaseContext().getPackageManager();
        int hasPerm = pm.checkPermission(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                getBaseContext().getPackageName());
        if (hasPerm != PackageManager.PERMISSION_GRANTED) {
            // do stuff
        } else {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            LatLng curPos = new LatLng(lat, lng);
            googleMap.setMaxZoomPreference(20);
            googleMap.setMinZoomPreference(15);
            googleMap.setMyLocationEnabled(true);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(curPos, 17));
            final Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(curPos)
                    .draggable(true));

            markerPos = marker.getPosition();

            final Button button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Map Fragment: " ,markerPos.toString());
                    startActivity(new Intent(MapsMarkerActivity.this, ConfirmationPage.class));
                }
            });

            googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {
                    button.setEnabled(false);
                }

                @Override
                public void onMarkerDrag(Marker marker) {

                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    button.setEnabled(true);
                    markerPos = marker.getPosition();
                }
            });
        }
    }
}
