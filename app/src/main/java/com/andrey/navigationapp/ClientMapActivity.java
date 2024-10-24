package com.andrey.navigationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.andrey.navigationapp.providers.AuthProvider;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
public class ClientMapActivity extends AppCompatActivity {

    Button mLogoutButton;
    AuthProvider mAuthProvider;
    private MapView mMapView;

    String key = BuildConfig.MAPTILER_API_KEY;

    String mapId = "basic-v2";

    String styleUrl = "https://api.maptiler.com/maps/" + mapId + "/style.json?key=" + key;

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_map);

        Mapbox.getInstance(this);

        mMapView = findViewById(R.id.mapView);
        mMapView.getMapAsync( map -> {
            map.setStyle(styleUrl);
            map.setCameraPosition(new CameraPosition.Builder().target(new LatLng(4.66775,-74.09085)).zoom(1.0).build());
                }
        );

        mLogoutButton = findViewById(R.id.logoutButton);

        mLogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuthProvider.logout();
                Intent intent = new Intent(ClientMapActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}