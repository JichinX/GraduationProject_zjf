package com.graduation.xujicahng.graduationproject.net;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.graduation.xujicahng.graduationproject.interfaces.HttpConnCallBack;
import com.graduation.xujicahng.graduationproject.utils.Const;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author xujichang
 * @version 2016/5/16.
 */
public class HttpConn {
    //是否是同步的请求
    private boolean sync = false;
    //是否要转码
    private boolean Encode = false;
    private String mUrl;
    private HttpConnCallBack mCallback;

    public HttpConn(boolean sync, boolean encode) {
        this.sync = sync;
        this.Encode = encode;
    }

    public HttpConn() {
        this(false, false);
    }

    public HttpConn(boolean sync) {
        this(sync, false);
    }


    /**
     * @param url
     * @param param
     * @param callback
     */
    public void post(String url, String param, HttpConnCallBack callback) {
        this.mUrl = url;
        this.mCallback = callback;
        HttpPost request = new HttpPost(url);

        if (!TextUtils.isEmpty(param)) {
            try {
                byte[] paramBytes = param.getBytes("UTF-8");
                BasicHttpEntity requestBody = new BasicHttpEntity();
                requestBody.setContent(new ByteArrayInputStream(paramBytes));
                requestBody.setContentLength(paramBytes.length);
                request.setEntity(requestBody);
                request.setHeader("Accept", "application/json");
                request.setHeader("Content-Type", "application/json;charset=utf-8");
            } catch (Exception e) {
                postEvent(null);
                return;
            }
        }

        if (sync) {
            new HttpAsyncTask().execute(request);
        } else {
            postEvent(work(request));
        }
    }

    public void get(String url, HttpConnCallBack callback) {
        this.mUrl = url;
        this.mCallback = callback;

        HttpGet request = new HttpGet(url);

        if (sync) {
            new HttpAsyncTask().execute(request);
        } else {
            postEvent(work(request));
        }
    }

    private void postEvent(String result) {
        if (this.mCallback != null) {
            this.mCallback.onResponse(result != null ? Const.HTTP_SUCCESS : Const.HTTP_FAIL, result);
        }
    }

    private String work(HttpUriRequest request) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private class HttpAsyncTask extends AsyncTask<HttpUriRequest, Void, String> {

        @Override
        protected String doInBackground(HttpUriRequest... requests) {
            return work(requests[0]);
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String result) {
            postEvent(result);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }
}
