package b3nac.injuredandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class FlagTwelveProtectedActivity extends AppCompatActivity {
    int click = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView flagWebView = new WebView(this);
        setContentView(flagWebView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SharedPreferences settings = getSharedPreferences("b3nac.injuredandroid", Context.MODE_PRIVATE);

        Uri uri = null;
        String intentToUri = getIntent().getStringExtra("totally_secure");
        uri = Uri.parse(intentToUri);

        flagWebView.getSettings().setJavaScriptEnabled(true);
        flagWebView.setWebChromeClient(new WebChromeClient());


        if (getIntent() == null || !getIntent().hasExtra("totally_secure")) {
            finish();
            return;
        }

        boolean onlyAcceptThisSchema = "https".equals(uri.getScheme());

        if (onlyAcceptThisSchema) {

            flagWebView.loadUrl(getIntent().getStringExtra("totally_secure"));
            FlagsOverview.flagTwelveButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagTwelveButtonColor", true).commit();
            correctFlag();

        } else {

            flagWebView.loadData(getIntent().getStringExtra("totally_secure"), "text/html", "UTF-8");
            FlagsOverview.flagTwelveButtonColor = true;
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("flagTwelveButtonColor", true).commit();
            correctFlag();
        }
    }

    private void correctFlag() {
        Intent intent = new Intent(this, FlagOneSuccess.class);
        startActivity(intent);
    }

}
