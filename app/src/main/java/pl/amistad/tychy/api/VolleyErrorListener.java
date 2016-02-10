package pl.amistad.tychy.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import pl.amistad.tychy.R;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class VolleyErrorListener implements Response.ErrorListener {
    private Context mContext;

    public VolleyErrorListener(Context context) {
        this.mContext = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            Log.d("TAG", "Error while loading " + error.getMessage() +
                    "\n" +"Error code: " + error.networkResponse.statusCode);
        }catch(NullPointerException exc){
            Log.d("TAG", error.getMessage());
        }
    }
}
