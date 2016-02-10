package pl.amistad.tychy.api;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;

import pl.amistad.tychy.api.model.NominatimObject;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class NominatimApi {

    private static NominatimApi api;
    private Volleysingleton volleysingleton;


    private NominatimApi(Context context) {
        this.volleysingleton = Volleysingleton.getInstance(context);
    }

    public static NominatimApi getInstance(Context context){
        if(api == null){
            api = new NominatimApi(context);
        }
        return api;
    }


    public <T> void addToRequestQueue(Request<T> request) {
        this.volleysingleton.getRequestQueue().add(request);
    }

    public void searchPlace(String queryString, final NominationApiResponseListener listener, Context context){
       final NominatimRequest request = new NominatimRequest(
               queryString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", response);
                        try {
                            Gson gson = new Gson();
                            NominatimObject[] nominatimObject =  gson.fromJson(response, NominatimObject[].class);

                            if(listener != null)
                                listener.onResponseDelivered(nominatimObject);

                        }catch (RuntimeException exc){
                            Log.d("TAG", exc.getMessage());
                            Log.d("TAG", "Unsupported exception");
                        }
                    }
                },
                new VolleyErrorListener(context)
        );

        addToRequestQueue(request);

    }

    public interface NominationApiResponseListener{
        public void onResponseDelivered(NominatimObject[] nominatimObject);
    }


}
