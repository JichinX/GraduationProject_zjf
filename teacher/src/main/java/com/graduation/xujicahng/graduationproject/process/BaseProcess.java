package com.graduation.xujicahng.graduationproject.process;

import android.os.AsyncTask;

import com.graduation.xujicahng.graduationproject.interfaces.HttpConnCallBack;
import com.graduation.xujicahng.graduationproject.interfaces.ResponseListener;
import com.graduation.xujicahng.graduationproject.listeners.EmptyResponseListener;
import com.graduation.xujicahng.graduationproject.net.HttpConn;
import com.graduation.xujicahng.graduationproject.utils.Const;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 所有网络请求的基类
 */
abstract public class BaseProcess {
    /**
     * 服务器返回的状态
     */
    private int status;

    private HttpConnCallBack connCallBack = new CallBack();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //获取请求地址
    abstract String getRequestUrl();

    //请求参数
    abstract String getParameter();

    //返回结果
    abstract void onResult(JSONObject object);

    public void runPost() {
        runPost(new EmptyResponseListener());
    }

    public void runPost(ResponseListener listener) {
        runPost(null, listener);
    }

    public void runPost(String requestId, ResponseListener listener) {
        new AsyncComm(requestId, listener, false).execute();
    }

    public void runGet() {
        runGet(new EmptyResponseListener());
    }

    public void runGet(ResponseListener listener) {
        runGet(null, listener);
    }

    public void runGet(String requestId, ResponseListener listener) {
        new AsyncComm(requestId, listener, true).execute();
    }


    private class AsyncComm extends AsyncTask<Void, Void, Void> {
        private String requestId;
        private ResponseListener listener;
        private String parameter;
        private boolean isGet;

        public AsyncComm(String requestId, ResponseListener listener, boolean isGet) {
            this.requestId = requestId;
            this.listener = listener;
            this.isGet = isGet;
        }

        @Override
        protected Void doInBackground(Void... params) {
            //Url路径
            String url = new StringBuffer(Const.BASE_URL).append(getRequestUrl()).toString();
            //请求参数
            HttpConn conn = new HttpConn();
            if (isGet) {
                conn.get(url, connCallBack);
            } else {
                parameter = getParameter();
                conn.post(url, parameter, connCallBack);
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listener.onResponse(requestId);
        }
    }

    private class CallBack implements HttpConnCallBack {

        @Override
        public void onResponse(int status, String result) {
            if (status == Const.HTTP_SUCCESS) {
                try {
                    JSONObject object = new JSONObject(result);
                    onResult(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
