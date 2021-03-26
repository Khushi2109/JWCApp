package rishabh.example.jwcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import rishabh.example.jwcapp.ebook.pdfViewerActivity;

public class DeveloperActivity extends AppCompatActivity {

    ImageView instagram, facebook, twitter;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        loadAds();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Developer");

        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mInterstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        super.onAdClosed();
                        String url = "https://www.instagram.com/khushi_.21_";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    return;
                }

                String url = "https://www.instagram.com/khushi_.21_";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mInterstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        super.onAdClosed();
                        String url = "https://www.facebook.com/Khushi.2109";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    return;
                }

                String url = "https://www.facebook.com/Khushi.2109";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mInterstitialAd.setAdListener(new AdListener(){
                    @Override
                    public void onAdClosed() {
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        super.onAdClosed();
                        String url = "https://twitter.com/Khushi_2109";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });

                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    return;
                }

                String url = "https://twitter.com/Khushi_2109";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private void loadAds(){
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAd_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}