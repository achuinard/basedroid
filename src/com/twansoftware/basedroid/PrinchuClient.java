package com.twansoftware.basedroid;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import roboguice.util.Ln;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class PrinchuClient {
    private static HttpClient httpClient = HttpConnectionManager.getInstance();

    private SharedPreferences sharedPreferences;

    @Inject
    public PrinchuClient(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        Log.d("TAG", "Spinning up PrinchuClient");
        Log.d("TAG", String.valueOf(sharedPreferences.getBoolean("test", false)));
    }

    /**
     * this is an example of an API call that returns a 'long'.  say my activity wants to find this long value.
     * client method returns exactly what we need.  keeps things nice and decoupled.
     *
     * @return the userId associated with the search-for username
     */
    public long exampleJsonSearch() {
        String url = "http://www.speakbin.com/api/json/interaction/searchidbyusername.sb?username=tony";
        Map<String, String> requestParams = new HashMap<String, String>();

        requestParams.put("username", "achuinard");

        try {
            JSONObject jo = fetchJsonObject(url, requestParams, RequestType.GET);
            return jo.getLong("userId");
        } catch (Exception e) {
            Ln.w(e);
        }
        return 0;
    }

    private enum RequestType {
        GET, POST
    }

    private JSONObject fetchJsonObject(String url, Map<String, String> requestParams, RequestType requestType) throws Exception {
        if (requestType == RequestType.GET) {
            StringBuilder realUrl = new StringBuilder();
            realUrl.append(url);
            realUrl.append("?");
            for (String key : requestParams.keySet()) {
                realUrl.append(key);
                realUrl.append("=");
                realUrl.append(URLEncoder.encode(requestParams.get(key), "UTF-8"));
            }

            Ln.d("Making http get request to %s.", realUrl.toString());
            return new JSONObject(httpClient.execute(new HttpGet(realUrl.toString()), new BasicResponseHandler()));
        } else if (requestType == RequestType.POST) {
            HttpPost httpPost = new HttpPost(url);
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (String key : requestParams.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, requestParams.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            Ln.d("Making http post request to %s.", url);
            return new JSONObject(httpClient.execute(httpPost, new BasicResponseHandler()));
        }

        // should never come to this
        return null;
    }
}
