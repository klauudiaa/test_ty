package pl.amistad.tychy.fragment;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.PathOverlay;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.amistad.framework.fragment.base.BaseProgressFragment;
import pl.amistad.framework.map.overlay.MapEventsOverlay;
import pl.amistad.framework.map.overlay.MapEventsReceiver;
import pl.amistad.framework.service.GpsService;
import pl.amistad.tychy.R;
import pl.amistad.tychy.api.model.NominatimObject;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class MapFragment extends BaseProgressFragment implements MapEventsReceiver {
    public static final String TAG = MapFragment.class.getSimpleName() ;

    MyItemizedOverlay myItemizedOverlay = null;
    protected PathOverlay mPathOverlay;

    @Bind(R.id.map_view)
    protected MapView mMapView;

    @Bind(R.id.map_navigate_button)
    protected ImageButton navigateButton;

    protected View mPopupView;

    protected ItemizedIconOverlay<OverlayItem> mItemizedIconOverlay;

    protected Drawable mMarker;

    protected Location mPointedLocation = null;
    protected MapController mMapController;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_map;
    }

    @Override
    public void initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       ButterKnife.bind(this, mContentView);

        mMarker = getMarker();

        mMapView.setMultiTouchControls(true);
        mMapView.setTilesScaledToDpi(true);
        mMapView.getOverlays().add(new MapEventsOverlay(getActivity(), this));

        mPathOverlay = new PathOverlay(getResources().getColor(getPathColorResource()), getActivity());
        mPathOverlay.getPaint().setStrokeWidth(getPathStrokeWidth());

        mMapView.getOverlays().add(mPathOverlay);

//        mItemizedIconOverlay = new ItemizedIconOverlay<OverlayItem>(new ArrayList<OverlayItem>(), mMarker, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
//            @Override
//            public boolean onItemSingleTapUp(int index, OverlayItem item) {
//                if (mPopupView != null && mMapView != null && mPopupView.isShown()) {
//                    mMapView.removeView(mPopupView);
//                }
//
//               // initPopup(mPopupView, mPlacesOnTrip.get(index));
//
//                return true;
//            }
//
//            @Override
//            public boolean onItemLongPress(int index, OverlayItem item) {
//                return false;
//            }
//        }, new DefaultResourceProxyImpl(getActivity()));

        //mMapView.getOverlays().add(mItemizedIconOverlay);
        myItemizedOverlay = new MyItemizedOverlay(mMarker, new DefaultResourceProxyImpl(getActivity().getApplicationContext()));
        mMapView.getOverlays().add(myItemizedOverlay);
        GeoPoint myPoint1 = new GeoPoint(50.127124*1000000, 18.938289*1000000);
        myItemizedOverlay.addItem(myPoint1, "Tychy", "Tychy");

        mMapController = (MapController) mMapView.getController();
        mMapController.setZoom(13);
        mMapController.animateTo(myPoint1);

//        Drawable marker=getResources().getDrawable(android.R.drawable.star_big_on);
//        int markerWidth = marker.getIntrinsicWidth();
//        int markerHeight = marker.getIntrinsicHeight();
//        marker.setBounds(0, markerHeight, markerWidth, 0);
//
//        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getActivity().getApplicationContext());
//
//        myItemizedOverlay = new MyItemizedOverlay(marker, resourceProxy);
//        mMapView.getOverlays().add(myItemizedOverlay);
//
//        GeoPoint myPoint1 = new GeoPoint(0*1000000, 0*1000000);
//        myItemizedOverlay.addItem(myPoint1, "myPoint1", "myPoint1");
//
//        GeoPoint myPoint2 = new GeoPoint(50*1000000, 19*1000000);
//        myItemizedOverlay.addItem(myPoint2, "myPoint2", "myPoint2");


    }



    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {
        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        return false;
    }

    public static MapFragment newInstance(){
        MapFragment mapFragment = new MapFragment();

        return mapFragment;
    }

    @OnClick(R.id.map_navigate_button)
    public void navigateButtonOnClick(){

        if( mPointedLocation != null)
            GpsService.getDirections(getActivity(), mPointedLocation);
    }

    public int getPathColorResource() {
        return R.color.colorAccent;
    }

    public float getPathStrokeWidth() {
        return 5;
    }

    private void drawRoute(){
//        for (int i = 0; i < trackpoints.length; i++) {
//            mPathOverlay.addPoint(new GeoPoint(trackpoints[i].getLatitude(), trackpoints[i].getLongitude()));
//        }
    }

    @Override
    public int doInBackground() {
        super.doInBackground();

        drawRoute();

        return 0;
    }

    public Drawable getMarker() {
        return getResources().getDrawable(android.R.drawable.star_big_on);
    }

    public void displayPlace(NominatimObject nominatimObject){
       setLocation(nominatimObject.getLat(), nominatimObject.getLon());

        //Log.d("TAG", "Displayed place in fragment: " + nominatimObject.toString());
        GeoPoint myPoint1 = new GeoPoint(nominatimObject.getLat()*1000000, nominatimObject.getLon()*1000000);
        myItemizedOverlay.clear();
        myItemizedOverlay.addItem(myPoint1, nominatimObject.getDisplayName(),"");
        mMapController.animateTo(myPoint1);
    }

    private void setLocation(double latitude, double longitude){
        if(mPointedLocation == null)
            mPointedLocation = new Location("map_location");

        mPointedLocation.setLatitude(latitude);
        mPointedLocation.setLongitude(longitude);

    }


}
