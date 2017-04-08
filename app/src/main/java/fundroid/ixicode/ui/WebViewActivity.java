package fundroid.ixicode.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import fundroid.ixicode.R;
import fundroid.ixicode.utils.Slog;

public class WebViewActivity extends AppCompatActivity {

    String url;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        url = getIntent().getStringExtra("url");

        if (url.isEmpty()) {
            finish();
            return;
        }

        webview = (WebView) findViewById(R.id.wv_app);

        webview.getSettings().setJavaScriptEnabled(true);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Slog.d("Page Loading Started" + url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Slog.d("webview Url: -- : " + url);
            }
        });

        webview.loadUrl(url);
    }
}
