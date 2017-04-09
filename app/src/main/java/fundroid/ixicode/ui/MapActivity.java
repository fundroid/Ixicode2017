//package fundroid.ixicode.ui;
//
//import android.os.Bundle;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import fundroid.ixicode.R;
//import fundroid.ixicode.base.BaseActivity;
//import fundroid.ixicode.model.Place;
//import fundroid.ixicode.model.RecomPlaces;
//
//public class MapActivity extends BaseActivity implements OnMapReadyCallback {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_map);
//
//        recom_places = (RecomPlaces) getIntent().getSerializableExtra("recom");
//
//    }
//
//    @Override
//    public void onMapReady(GoogleMap gmap) {
//        for (int i = 0; i < recom_places.getFlight().size(); i++) {
//            addPointToMap(gmap, recom_places.getFlight().get(i));
//        }
//    }
//
//    protected Marker addPointToMap(GoogleMap gmap, Place place) {
//        LatLng loc = new LatLng(place.getLat(), place.getLng());
//
//        Marker marker = gmap.addMarker(new MarkerOptions()
//                .position(loc)
//                .title(place.getName())
//                .snippet(place.getCountryName())
//                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_round)));
//
////        CameraPosition cameraPosition = new CameraPosition.Builder()
////                .target(loc)      // Sets the center of the map to Mountain View
////                .zoom(googleMap.getCameraPosition().zoom)                   // Sets the zoom
////                .zoom(1)                   // Sets the zoom
////                .bearing(90)                // Sets the orientation of the camera to east
////                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
////                .build();                   // Creates a CameraPosition from the builder
////        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//
//        return marker;
//    }
//}
