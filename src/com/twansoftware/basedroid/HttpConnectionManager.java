package com.twansoftware.basedroid;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

/**
 * achuinard
 */
public class HttpConnectionManager {
    private static HttpClient INSTANCE;

    public static HttpClient getInstance() {
        if (INSTANCE == null) {
            final HttpParams params = new BasicHttpParams();
            final SchemeRegistry registry = new SchemeRegistry();
            final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();

            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, "utf-8");
            HttpProtocolParams.setUserAgent(params, "App Kingdoms Android");

            sslSocketFactory.setHostnameVerifier(SSLSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);

            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sslSocketFactory, 443));

            ClientConnectionManager manager = new ThreadSafeClientConnManager(params, registry);
            INSTANCE = new DefaultHttpClient(manager, params);
        }

        return INSTANCE;
    }
}
