package pl.amistad.tychy.api;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class NominatimRequest extends StringRequest {
    public static final String TAG = NominatimRequest.class.getName();
    public static final String URL = "http://nominatim.openstreetmap.org/search";
    public static final Map<String , String> params = new HashMap<String, String>();
    public static final String QUERY = "q";


    static{
        params.put("format", "json");
        params.put("polygon", "1");
        params.put("addressdetails", "1");
        params.put("limit", "1");
        params.put(QUERY, "");
    }

    public NominatimRequest(String searchedQuery,  Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, prepareUrl(searchedQuery), listener, errorListener);

        Log.d("TAG", "End of constructor");
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Log.d("TAG", "Get params");
        return params;
    }

    private static String prepareUrl(String searchedQuery){

        params.put(QUERY, ParamUtils.prepareQueryString(searchedQuery));

        Log.d("TAG", "Prepared url: " + ParamUtils.prepareUrl(URL, params));
        return ParamUtils.prepareUrl(URL, params);

    }


}

