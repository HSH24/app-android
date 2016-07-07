package com.js.hsh24;

import android.net.http.SslError;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


    public class MainActivity extends AppCompatActivity {
        private WebView webView;
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        }
        protected void  onStart(){
            super.onStart();

            String url="https://dms.hsh24.com";
            // String url="http://wx.wideka.com/dms";
            this.webView=(WebView) findViewById(R.id.webView);
            WebSettings webSettings = webView.getSettings();
          //  this.webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE,null );//Genymotion模拟器不支持硬件加速,关闭
            webSettings.setJavaScriptEnabled(true);
            this.webView.loadUrl(url);
            this.webView.setWebViewClient(new WebViewClientHsh());
        }

        private class WebViewClientHsh extends WebViewClient {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view ,String url){
                view .loadUrl(url);
                return true;
            }
            @Override
            public void onReceivedSslError(WebView view , SslErrorHandler handler,SslError error){
                handler.proceed();
            }
            @Override
            public void onFormResubmission(WebView view, Message dontReset,Message resend){
                resend.sendToTarget();
            }

        }

        public boolean onKeyDown(int keyCode , KeyEvent event){
            if(keyCode == event.KEYCODE_BACK&&webView.canGoBack()){
                webView.goBack();
                return true ;
            }
            return super.onKeyDown(keyCode,event);
        }
}
