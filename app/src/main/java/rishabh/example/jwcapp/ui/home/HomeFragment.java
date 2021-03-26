package rishabh.example.jwcapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;

import rishabh.example.jwcapp.R;

public class HomeFragment extends Fragment {

    private SliderLayout sliderLayout;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        sliderLayout = view.findViewById(R.id.slider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.DROP);
        sliderLayout.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2);

        map = view.findViewById(R.id.map);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        setSliderViews();

        return view;

    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Jamshedpur Women's College");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

    private void setSliderViews() {
        for (int i = 0; i < 5; i++){
            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i){
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/jwc-college-app.appspot.com/o/Slide2.jpg?alt=media&token=8b481ded-c3c4-4e1d-b248-09032abad581");
                    break;

                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/jwc-college-app.appspot.com/o/Slide5.jpg?alt=media&token=878b4706-2643-4883-aa91-bfad6d25cc9e");
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER);
                    break;

                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/jwc-college-app.appspot.com/o/Slider3.jpg?alt=media&token=95635fa2-f89d-41cd-8cff-49fb30feb813");
                    break;

                case 3:
                    sliderView.setImageScaleType(ImageView.ScaleType.CENTER);
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/jwc-college-app.appspot.com/o/Slide4.jpg?alt=media&token=ec3fdd92-df50-444f-a1db-53026ce05e14");
                    break;

                case 4:
                    sliderView.setDescription("International Yoga Day 2020");
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/jwc-college-app.appspot.com/o/Slider5.jpg?alt=media&token=4657a71b-7a1d-4d36-87bb-415683a78ca3");
                    break;
            }
            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);

            sliderLayout.addSliderView(sliderView);
        }
    }
}