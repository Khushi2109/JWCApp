package rishabh.example.jwcapp.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Adler32;

import rishabh.example.jwcapp.R;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_comp, "B.SC. IT", "A milestone was achieved when Jamshedpur Women’s College started the B.Sc. (IT) Course in 2008. We train the prospective IT Professionals to shoulder the responsibility of the future generation firmly yet with sensitivity and to value discipline as the bedrock of success. They are provided with every opportunity to explore their potentials and to grow into strong, empowered individuals capable of leading changes in society."));
        list.add(new BranchModel(R.drawable.ic_scence, "B.SC.", "science is the study of the natural world through observations and experiments. There are five main branches of science: Physics is the study of laws and properties that govern the universe, such as energy, waves, force, mechanics, motion, electricity, and nuclear reactions."));
        list.add(new BranchModel(R.drawable.ic_commerce, "B.COM.", "Commerce refers to activities connected with transferring goods from manufacturers to consumers. Branches of commerce include trade, transport, banking, insurance warehousing, advertising and communication. ... Commerce ensures the smooth flow of goods from the producer to the user with the help of aids to trade."));

        adapter = new BranchAdapter(getContext(), list);

        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.college_image);
        ImageView map = view.findViewById(R.id.map_about);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/jwc-college-app.appspot.com/o/JWC_About_Image.jpg?alt=media&token=d953d12f-f080-4675-a180-83d81ed6bd5d").into(imageView);

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Jamshedpur Women's College");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}