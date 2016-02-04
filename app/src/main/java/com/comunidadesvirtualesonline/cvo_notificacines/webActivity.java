package com.comunidadesvirtualesonline.cvo_notificacines;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Guardian on 01/02/2016.
 */
public class webActivity extends FragmentActivity {

    // establecer las variables WebView
    private WebView mWebview ;
    private static final String urlTag = "url";

    //Launch webview as intent
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getting intent data
        Intent intent = getIntent();

        // Atrapar la url de la cardView
        String postUrl = intent.getStringExtra(urlTag);

        Log.i("URL","=="+postUrl);

        mWebview  = new WebView(this);
        mWebview.getSettings().setJavaScriptEnabled(true); // habilita Javascript

        final Activity activity = this;

        // función mantiene el navegador dentro de la aplicación para que el usuario no
        // tiene que cambiar entre aplicaciones
        mWebview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });

        //cargar la url seleccionada
        mWebview.loadUrl(postUrl);
        setContentView(mWebview);

    }
}


