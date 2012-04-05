package com.twansoftware.basedroid.singleton;

import android.content.SharedPreferences;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONArray;
import org.json.JSONObject;
import roboguice.util.Ln;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class BasedroidHttpClient {
    private static HttpClient httpClient = HttpConnectionManager.getInstance();
    private SharedPreferences sharedPreferences;

    @Inject
    public BasedroidHttpClient(final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        Ln.d("Constructing BasedroidHttpClient...");
    }

    /**
     * this is an example of an API call that returns a 'long'.  say my activity wants to find this long value.
     * client method returns exactly what we need.
     *
     * @return the userId associated with the search-for username
     */
    public long exampleJsonSearch() {
        final String url = "http://www.speakbin.com/api/json/interaction/searchidbyusername.sb?username=tony";
        final Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("username", "achuinard");
        try {
            final JSONObject jo = fetchJsonObject(url, requestParams, RequestType.GET);
            return jo.getLong("userId");
        } catch (Exception e) {
            Ln.w(e);
        }
        return 0;
    }

    private enum RequestType {
        GET, POST
    }

    private JSONObject fetchJsonObject(final String url,
                                       final Map<String, String> requestParams,
                                       final RequestType requestType) throws Exception {
        return new JSONObject(fetchStringData(url, requestParams, requestType));
    }

    private JSONArray fetchJsonArray(final String url,
                                     final Map<String, String> requestParams,
                                     final RequestType requestType) throws Exception {
        return new JSONArray(fetchStringData(url, requestParams, requestType));
    }

    private String fetchStringData(final String url,
                                   final Map<String, String> requestParams,
                                   final RequestType requestType) throws IOException {
        Ln.d("Making %s request to %s.", requestType, url);
        if (requestType == RequestType.GET) {
            final StringBuilder realUrl = new StringBuilder();
            realUrl.append(url);
            if (!requestParams.isEmpty()) {
                realUrl.append("?");
                for (String key : requestParams.keySet()) {
                    realUrl.append(key);
                    realUrl.append("=");
                    realUrl.append(URLEncoder.encode(requestParams.get(key), "UTF-8"));
                }
            }
            return httpClient.execute(new HttpGet(realUrl.toString()), new BasicResponseHandler());
        } else if (requestType == RequestType.POST) {
            final HttpPost httpPost = new HttpPost(url);
            final List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            for (String key : requestParams.keySet()) {
                nameValuePairs.add(new BasicNameValuePair(key, requestParams.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            return httpClient.execute(httpPost, new BasicResponseHandler());
        } else {
            throw new IllegalStateException("Invalid HTTP request type.");
        }
    }

    public static class HttpConnectionManager {
        private static volatile HttpClient INSTANCE;

        /**
         * Static method to initialize HTTP client.  Notice the double instance == null check.
         * By checking in and outside of the synchronization, this method is threadsafe.
         * It ensures the singleton is only instantiated once.
         *
         * @return
         */
        public static HttpClient getInstance() {
            if (INSTANCE == null) {
                synchronized (HttpConnectionManager.class) {
                    if (INSTANCE == null) {
                        final HttpParams params = new BasicHttpParams();
                        final SchemeRegistry registry = new SchemeRegistry();
                        final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();

                        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                        HttpProtocolParams.setContentCharset(params, "utf-8");
                        HttpProtocolParams.setUserAgent(params, "Basedroid");

                        sslSocketFactory.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

                        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                        registry.register(new Scheme("https", sslSocketFactory, 443));

                        final ClientConnectionManager manager = new ThreadSafeClientConnManager(params, registry);
                        INSTANCE = new DefaultHttpClient(manager, params);
                    }
                }
            }
            return INSTANCE;
        }
    }
}
