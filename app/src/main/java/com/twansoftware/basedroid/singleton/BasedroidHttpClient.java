package com.twansoftware.basedroid.singleton;

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
    private HttpClient httpClient;

    public BasedroidHttpClient() {
        Ln.d("Constructing BasedroidHttpClient...");
        httpClient = new HttpConnectionManager().getInstance();
    }

    /**
     * This is an example API call.  Say I have an app - when a user registers you may want to check
     * if the username exists before sending the registration call through (because the registration
     * call will fail).  This fetches and parses the server-defined JSON object into a boolean value.
     * @param username The username you want to check exists.
     * @return true if it exists, false if it doesn't, null on server / deserialization error
     */
    public Boolean speakbinUserExists(final String username) {
        final String url = "http://www.speakbin.com/api/json/interaction/checkuser.sb";
        final Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("username", username);
        try {
            final JSONObject jo = fetchJsonObject(url, requestParams, RequestType.GET);
            return Boolean.parseBoolean(jo.getString("bool"));
        } catch (final Exception e) {
            Ln.w(e);
        }
        return null;
    }

    public HttpClient getHttpClient() {
        return httpClient;
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

    public class HttpConnectionManager {
        private volatile HttpClient INSTANCE;

        /**
         * Static method to initialize HTTP client.  Notice the double instance == null check.
         * By checking in and outside of the synchronization, this method is threadsafe.
         * It ensures the singleton is only instantiated once.
         *
         * @return
         */
        public HttpClient getInstance() {
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
