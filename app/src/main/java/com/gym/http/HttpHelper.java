package com.gym.http;

import android.content.Entity;
import android.text.TextUtils;
import android.util.Base64;

import com.gym.utils.IOUtils;
import com.gym.utils.LogUtils;
import com.gym.utils.StringUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by mwqi on 2014/6/7.
 */
public class HttpHelper {
    private static final int mTimeoutConnection = 10 * 1000;// 设置连接超时时间
    private static final int mTimeoutSocket = 15 * 1000;

    /**
     * get请求，获取返回字符串内容
     */
    public static HttpResult get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(url, httpGet);
    }

    /**
     * post请求，获取返回字符串内容
     */
    public static HttpResult post(String url, String value) {
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(value, "utf-8");
            stringEntity.setContentEncoding("utf-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

        }

        return execute(url, httpPost);
    }
    /*public static HttpResult post(String url, String value) {
        HttpPost httpPost = new HttpPost(url);
        try {
            StringEntity stringEntity=new StringEntity(value,"utf-8");
            stringEntity.setContentEncoding("utf-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);

            HttpParams httpParameters = new BasicHttpParams();
			*//*HttpConnectionParams.setConnectionTimeout(httpParameters,
                    timeoutConnection);
			HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);*//*
            HttpProtocolParams.setUserAgent(httpParameters, "HBPlatformClient");
            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            httpClient.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT, mTimeoutSocket);
            httpClient.getParams().setIntParameter(HttpConnectionParams.CONNECTION_TIMEOUT, mTimeoutConnection);

            setRequestHeader(httpPost, url);
            return  new HttpResult(httpClient.execute(httpPost), httpClient, httpPost);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
      //  return execute(url, httpPost);
    }*/

    /**
     * 下载
     */
    public static HttpResult download(String url) {
        HttpGet httpGet = new HttpGet(url);
        return execute(url, httpGet);
    }

    /**
     * 执行网络访问
     */
    private static HttpResult execute(String url, HttpRequestBase requestBase) {
        boolean isHttps = url.startsWith("https://");//判断是否需要采用https
        AbstractHttpClient httpClient = HttpClientFactory.create(isHttps);
        HttpContext httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        HttpRequestRetryHandler retryHandler = httpClient.getHttpRequestRetryHandler();//获取重试机制
        int retryCount = 0;
        boolean retry = true;
        while (retry) {
            try {
                HttpResponse response = httpClient.execute(requestBase, httpContext);//访问网络
                if (response != null) {
                    return new HttpResult(response, httpClient, requestBase);
                }
            } catch (Exception e) {
                IOException ioException = new IOException(e.getMessage());
                retry = retryHandler.retryRequest(ioException, ++retryCount, httpContext);//把错误异常交给重试机制，以判断是否需要采取从事
                LogUtils.e(e);
            }
        }
        return null;
    }

    /**
     * http的返回结果的封装，可以直接从中获取返回的字符串或者流
     */
    public static class HttpResult {
        private HttpResponse mResponse;
        private InputStream mIn;
        private String mStr;
        private HttpClient mHttpClient;
        private HttpRequestBase mRequestBase;

        public HttpResult(HttpResponse response, HttpClient httpClient, HttpRequestBase requestBase) {
            mResponse = response;
            mHttpClient = httpClient;
            mRequestBase = requestBase;
        }

        public int getCode() {
            StatusLine status = mResponse.getStatusLine();
            return status.getStatusCode();
        }

        /**
         * 从结果中获取字符串，一旦获取，会自动关流，并且把字符串保存，方便下次获取
         */
        public String getString() {
            if (!StringUtils.isEmpty(mStr)) {
                return mStr;
            }

            InputStream inputStream = getInputStream();
            ByteArrayOutputStream out = null;
            if (inputStream != null) {
                try {
                    out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024 * 4];
                    int len = -1;
                    while ((len = inputStream.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    byte[] data = out.toByteArray();
                    mStr = new String(data, "utf-8");
                } catch (Exception e) {
                    LogUtils.e(e);
                } finally {
                    IOUtils.close(out);
                    close();
                }
            }


            return mStr;
        }

        /**
         * 获取流，需要使用完毕后调用close方法关闭网络连接
         */
        public InputStream getInputStream() {
            if (mIn == null && getCode() < 300) {
                HttpEntity entity = mResponse.getEntity();

                try {
                    mIn = entity.getContent();
                } catch (Exception e) {
                    LogUtils.e(e);
                }
            }
            return mIn;
        }

        /**
         * 关闭网络连接
         */
        public void close() {
            if (mRequestBase != null) {
                mRequestBase.abort();
            }
            IOUtils.close(mIn);
            if (mHttpClient != null) {
                mHttpClient.getConnectionManager().closeExpiredConnections();
            }
        }

    }

}
