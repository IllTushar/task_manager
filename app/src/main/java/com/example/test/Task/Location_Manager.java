package com.example.test.Task;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import com.example.test.assets.Assets;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location_Manager {
    Assets assets;
    Context context;

    public Location_Manager(Context context) {
        this.context = context;
        assets = new Assets(context);
    }

    public void getLocation(TextView location_text) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            getAddress(latitude, longitude,location_text);
        } else {
            // Handle the case where location is null
            assets.toast(this.context, "latlong is not found!!");
        }
    }

    private void getAddress(double latitude, double longitude, TextView location_text) {
        Geocoder geocoder = new Geocoder(this.context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String addressText = address.getAddressLine(0);
                // Use the address string for your purposes
                location_text.setText(addressText);
                assets.toast(this.context, addressText);
            } else {
                // Handle the case where no address was found
                assets.toast(this.context, "address is not found!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
            assets.toast(this.context, "unable to get address!!");
        }
    }
}
