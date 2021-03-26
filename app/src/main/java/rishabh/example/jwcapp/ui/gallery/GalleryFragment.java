package rishabh.example.jwcapp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rishabh.example.jwcapp.R;

public class GalleryFragment extends Fragment {

    RecyclerView convoRecycler, independenceRecycler, otherRecycler;
    GalleryAdapter adapter;
    private ProgressBar convoProgress, visitProgress, otherProgress;

    DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        convoRecycler = view.findViewById(R.id.convoRecycler);
        independenceRecycler = view.findViewById(R.id.independenceRecycler);
        otherRecycler = view.findViewById(R.id.otherRecycler);

        convoProgress = view.findViewById(R.id.convoProgress);
        visitProgress = view.findViewById(R.id.visitProgress);
        otherProgress = view.findViewById(R.id.otherProgress);

        reference = FirebaseDatabase.getInstance().getReference().child("Gallery");

        getConvoImage();

        getIndependenceImage();

        getOthersImage();

        return view;
    }

    private void getConvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    convoRecycler.setVisibility(View.GONE);
                }
                else {
                    convoRecycler.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String data = (String) snapshot.getValue();
                        imageList.add(data);
                    }

                    adapter = new GalleryAdapter(getContext(), imageList);

                    convoRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    convoRecycler.setAdapter(adapter);
                }
                convoProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                convoProgress.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getIndependenceImage() {
        reference.child("Visit to Cheshire Home").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    independenceRecycler.setVisibility(View.GONE);
                }
                else {
                    independenceRecycler.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String data = (String) snapshot.getValue();
                        imageList.add(data);
                    }

                    adapter = new GalleryAdapter(getContext(), imageList);

                    independenceRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    independenceRecycler.setAdapter(adapter);
                }
                visitProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                visitProgress.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOthersImage() {
        reference.child("Other Events").addValueEventListener(new ValueEventListener() {

            List<String> imageList = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()){
                    otherRecycler.setVisibility(View.GONE);
                }
                else {
                    otherRecycler.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String data = (String) snapshot.getValue();
                        imageList.add(data);
                    }

                    adapter = new GalleryAdapter(getContext(), imageList);

                    otherRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    otherRecycler.setAdapter(adapter);
                }
                otherProgress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                otherProgress.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}