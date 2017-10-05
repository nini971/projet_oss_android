package com.example.nico.ossproject.bean.beanUtils;

import android.location.Location;
import android.support.annotation.Nullable;

import com.example.nico.ossproject.bean.beanServer.Spot;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;

import java.util.ArrayList;
import java.util.List;

public class MapUtils {
    public static void polygonZoomIn(Polygon polygon, GoogleMap googleMap){
        LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();
        List<LatLng> points = polygon.getPoints();
        for (LatLng latLng : points){
            latLngBounds.include(latLng);
        }
        int padding = 50; // offset from edges of the map in pixels
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), padding));
    }

    public static ArrayList<Marker> spotsZoomIn(ArrayList<Spot> spotArrayList, GoogleMap googleMap, @Nullable Location myLocation){
        ArrayList<Marker> markerArrayList = new ArrayList<>();
        LatLngBounds.Builder latLngBounds = new LatLngBounds.Builder();
        for (Spot spot : spotArrayList){
            LatLng latLng = new LatLng(spot.getLattitude(), spot.getLongitude());
            latLngBounds.include(latLng);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(spot.getName()));
            marker.setTag(spot);
            markerArrayList.add(marker);

        }
        if (myLocation != null){
            latLngBounds.include(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
        }
        int padding = 300; // offset from edges of the map in pixels
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), padding));
        return markerArrayList;
    }
}
