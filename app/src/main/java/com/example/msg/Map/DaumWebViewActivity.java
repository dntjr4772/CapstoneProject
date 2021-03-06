
package com.example.msg.Map;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

import com.example.msg.R;


public class DaumWebViewActivity extends AppCompatActivity {
    private WebView browser;
    private TextView daum_result;
    private Handler handler;
    private Button b2;
    private final Geocoder geocoder= new Geocoder(this);
    private TextView tv;

    public double mLat=0;
    public double mLng=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daum_web_view);

        b2=(Button)findViewById(R.id.button2);


        daum_result = (TextView) findViewById(R.id.daum_result);
        init_webView();

        handler = new Handler();
    }

    public void init_webView() {

        browser = (WebView) findViewById(R.id.webView);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new AndroidBridge(), "TestApp");
        browser.getSettings().setSupportMultipleWindows(true);
        browser.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {

                browser.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        //browser.loadUrl("file:///android_asset/daum.html");
        //browser.loadUrl("http://www.daddyface.com/public/daum.html");
        //browser.loadUrl("http://cdn.rawgit.com/jolly73-df/DaumPostcodeExample/master/DaumPostcodeExample/app/src/main/assets/daum.html");
        browser.loadUrl("http://capstone.dothome.co.kr/embed.php");
        // ??????! ??? ???????????? ???????????? ??????????????? ????????? ???????????????.
        // ??? ????????? ??? ????????? ?????? ????????? ???????????? ????????? ????????? ???????????????.
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    daum_result.setText(String.format("%s %s", arg1, arg2));
                    String a= daum_result.getText().toString();

                    b2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            List<Address> list = null;


                            String str = daum_result.getText().toString();
                            try {
                                list = geocoder.getFromLocationName(str,1);
                                double mLat=list.get(0).getLatitude();
                                double mLng=list.get(0).getLongitude();

                                Intent intent = new Intent();
                                intent.putExtra("comeback", str);

                                intent.putExtra("comebacks", mLat);
                                intent.putExtra("comebackss", mLng);
                                setResult(RESULT_OK, intent);
                                finish();
                                //????????? ????????? ?????????
//                                Intent intent = new Intent(DaumWebViewActivity.this, MapActivity.class);
//                                intent.putExtra("mLat",mLat);
//                                intent.putExtra("mLng", mLng);
//                                startActivity(intent);

//                finish();

                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("test","????????? ?????? - ???????????? ??????????????? ????????????");
                            }

                        }
                    });


                    init_webView();
                }
            });
        }
    }


}
