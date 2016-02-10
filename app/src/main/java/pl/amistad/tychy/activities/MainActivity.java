package pl.amistad.tychy.activities;

import pl.amistad.framework.activity.AbstractActivity;
import pl.amistad.tychy.adapter.ShowPlaceSuggestionAdapter;
import pl.amistad.tychy.api.NominatimApi;
import pl.amistad.tychy.api.model.NominatimObject;
import pl.amistad.tychy.fragment.FMapFragment;
import pl.amistad.tychy.fragment.MapFragment;
import pl.amistad.tychy.R;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AbstractActivity {

    private MapFragment mMapFragment;
    private ShowPlaceSuggestionAdapter mSuggestionsAdapter = new ShowPlaceSuggestionAdapter(this,new NominatimObject[0]);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mMapFragment = (MapFragment) getSupportFragmentManager().findFragmentByTag(MapFragment.TAG);
        if(mMapFragment == null) {
            mMapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, mMapFragment, MapFragment.TAG)
                    .commit();
        }
    }


    public boolean isSlidingMenuEnabled() {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (isSearchEnabled()) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_search, menu);

            MenuItem searchMenuItem = menu.findItem(R.id.search);
            mSearchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            if (!TextUtils.isEmpty(mQuery)) {
                mSearchView.setIconified(false);
                mSearchView.clearFocus();
                mSearchView.setQuery(mQuery, false);



            }
            mSearchView.setOnQueryTextListener(this);
            mSearchView.setSuggestionsAdapter(mSuggestionsAdapter);
            mSearchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
                @Override
                public boolean onSuggestionSelect(int position) {
                    NominatimObject selectedObject = mSuggestionsAdapter.getItem(position);
                    mMapFragment.displayPlace(selectedObject);
                    return false;
                }

                @Override
                public boolean onSuggestionClick(int position) {
                    return false;
                }
            });
        }


        return super.onCreateOptionsMenu(menu);
    }

    public boolean isSearchEnabled() {
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.d("TAG", "onQueryTextSubmit " + query);

        if( !TextUtils.isEmpty(query) ) {
            NominatimApi.getInstance(this).searchPlace(query, new NominatimApi.NominationApiResponseListener() {
                @Override
                public void onResponseDelivered(NominatimObject[] nominatimObject) {

                    Log.d("TAG", "Response delivered to activity: " + nominatimObject);
                    if (nominatimObject.length == 1)
                        mMapFragment.displayPlace(nominatimObject[0]);
                    else{
                        setSuggestions(nominatimObject);
                    }

                }
            }, this);

            return true;
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
       // Log.d("TAG", "onQueryTextChange " + newText);
        return false;
    }

    public void setSuggestions(NominatimObject[] nominatimObjects){
        mSuggestionsAdapter.setData(nominatimObjects);
        mSuggestionsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapFragment = null;
    }
}
