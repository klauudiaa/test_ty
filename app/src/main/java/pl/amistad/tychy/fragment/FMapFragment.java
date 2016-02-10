package pl.amistad.tychy.fragment;

import android.graphics.drawable.Drawable;
import android.view.View;

import org.osmdroid.util.GeoPoint;

import pl.amistad.framework.fragment.AbsMapFragment;
import pl.amistad.framework.fragment.BaseTripMapFragment;
import pl.amistad.framework.model.treespot.Item;
import pl.amistad.tychy.R;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class FMapFragment extends BaseTripMapFragment{

    @Override
    protected int getMapId() {
        return R.id.map_view;
    }

    public int getPathColorResource() {
        return R.color.colorAccent;
    }

    @Override
    protected int getPathStrokeWidth() {
        return 5;
    }


    @Override
    protected int getLocalizeId() {
        return 0;
    }

    @Override
    protected Drawable getMarker() {
        return getResources().getDrawable(android.R.drawable.star_big_off);
    }

    @Override
    protected Drawable getSelectedMarker() {
        return getResources().getDrawable(android.R.drawable.star_big_on);
    }

    @Override
    public void initPopup(View popupView, Item item) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_map;
    }

    public static FMapFragment newInstance(){
        return new FMapFragment();
    }
}
