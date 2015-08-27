package com.gym.ui.activity;

import android.net.http.SslError;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gym.R;
import com.gym.utils.UIUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class WebViewActivity extends BaseActivity {

    @InjectView(R.id.title_tb)
    TextView title_tb;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.back_tb)
    TextView backTb;
    @InjectView(R.id.register_wv)
    WebView registWebView;
    private ActionBar mActionbar;
    private String url;

    @Override
    public void init() {
        setContentView(R.layout.activity_register_new);
        ButterKnife.inject(this);
        url = getIntent().getStringExtra("url");
    }

    @Override
    public void initData() {
        super.initData();
        addWebViewEvent(url);

    }

    @Override
    public void initActionbar() {
        setSupportActionBar(toolbar);
        mActionbar = getSupportActionBar();
        mActionbar.setDefaultDisplayHomeAsUpEnabled(true);
        mActionbar.setHomeButtonEnabled(true);
        backTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void addWebViewEvent(String registUri) {
        // TODO Auto-generated method stub
        WebSettings webSettings = registWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//设置缓存
        // 设置可以访问文件
        webSettings.setAllowFileAccess(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置默认缩放方式尺寸是far
        webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        // 设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDefaultFontSize(20);
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//设置缓存
        registWebView.loadUrl(registUri);
        registWebView.setWebChromeClient(new WebChromeClient() {

            /*
             * (non-Javadoc)
             *
             * @see
             * android.webkit.WebChromeClient#onProgressChanged(android.webkit
             * .WebView, int)
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                super.onProgressChanged(view, newProgress);
            }

        });

        registWebView.setWebViewClient(new WebViewClient() {

            /*
             * (non-Javadoc)
             *
             * @see
             * android.webkit.WebViewClient#shouldOverrideUrlLoading(android
             * .webkit.WebView, java.lang.String)
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }

            /*
             * (non-Javadoc)
             *
             * @see
             * android.webkit.WebViewClient#onReceivedSslError(android.webkit
             * .WebView, android.webkit.SslErrorHandler,
             * android.net.http.SslError)
             */
            @Override
            public void onReceivedSslError(WebView view,
                                           SslErrorHandler handler, SslError error) {
                // TODO Auto-generated method stub
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });
    }

}
