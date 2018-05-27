package adisense.adisense;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Localizacion extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loca);


        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }




    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.4530100,-3.6882900))
                .title("Santiago Bernabéu ")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .snippet("Santiago Bernabéu "));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.2333,-3.4333))
                .title("Adidas HackAton")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .snippet("Adidas HackAton"));

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(38.53173101357827,-1.6473432999999886))
                .title("Adidas")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                .snippet("Tienda adidas"));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.53173101357827,-3.6473432999999886), 10));

    }
}
