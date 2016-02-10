package pl.amistad.tychy.api;

import java.util.Map;

/**
 * Created by Klaudia on 2016-02-09.
 */
public class ParamUtils {
    public static String prepareQueryString(String queryString){
        return queryString.replace( " ", "%20");
    }

    public static String prepareUrl(String url, Map<String, String> params){
        int counter = 1;
        StringBuffer buffer = new StringBuffer(url);
        buffer.append("?");

        for(String key : params.keySet()){
            buffer.append(key).append("=").append(params.get(key));

            if(counter < params.size())
                buffer.append("&");

            counter++;
        }

        return buffer.toString();
    }

}
