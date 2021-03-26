package rishabh.example.jwcapp.ui.faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

public class FacultyFragment extends Fragment {

    private RecyclerView scienceDepartment, commerceDepartment, artsDepartment, othersDepartment;
    private LinearLayout scienceNoData, commerceNoData, artsNoData, othersNoData;
    private List<TeacherData> list1, list2, list3, list4;
    private TeacherAdapter adapter;
    private ProgressBar pbsc, pbco, pbar, pbot;

    private DatabaseReference reference, dbRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_faculty, container, false);


        scienceDepartment = view.findViewById(R.id.scienceDepartment);
        commerceDepartment = view.findViewById(R.id.commerceDepartment);
        artsDepartment = view.findViewById(R.id.artsDepartment);
        othersDepartment = view.findViewById(R.id.othersDepartment);

        pbsc = view.findViewById(R.id.pbsc);
        pbco = view.findViewById(R.id.pbco);
        pbar = view.findViewById(R.id.pbar);
        pbot = view.findViewById(R.id.pbot);

        scienceNoData = view.findViewById(R.id.scienceNoData);
        commerceNoData = view.findViewById(R.id.commerceNoData);
        artsNoData = view.findViewById(R.id.artsNoData);
        othersNoData = view.findViewById(R.id.othersNoData);

        reference = FirebaseDatabase.getInstance().getReference().child("Teachers");

        scienceDepartment();
        commerceDepartment();
        artsDepartment();
        othersDepartment();

        return  view;
    }

    private void scienceDepartment() {
        dbRef = reference.child("Science");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    scienceNoData.setVisibility(View.VISIBLE);
                    scienceDepartment.setVisibility(View.GONE);
                }
                else {
                    scienceNoData.setVisibility(View.GONE);
                    scienceDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list1.add(data);
                    }
                    scienceDepartment.setHasFixedSize(true);
                    scienceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1, getContext());
                    scienceDepartment.setAdapter(adapter);
                }
                pbsc.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbsc.setVisibility(View.GONE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void commerceDepartment() {
        dbRef = reference.child("Commerce");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list2 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    commerceNoData.setVisibility(View.VISIBLE);
                    commerceDepartment.setVisibility(View.GONE);
                }
                else {
                    commerceNoData.setVisibility(View.GONE);
                    commerceDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list2.add(data);
                    }
                    commerceDepartment.setHasFixedSize(true);
                    commerceDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2, getContext());
                    commerceDepartment.setAdapter(adapter);
                }
                pbco.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbco.setVisibility(View.GONE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void artsDepartment() {
        dbRef = reference.child("Arts");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list3 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    artsNoData.setVisibility(View.VISIBLE);
                    artsDepartment.setVisibility(View.GONE);
                }
                else {
                    artsNoData.setVisibility(View.GONE);
                    artsDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list3.add(data);
                    }
                    artsDepartment.setHasFixedSize(true);
                    artsDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3, getContext());
                    artsDepartment.setAdapter(adapter);
                }
                pbar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbar.setVisibility(View.GONE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void othersDepartment() {
        dbRef = reference.child("Others");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list4 = new ArrayList<>();
                if (!dataSnapshot.exists()){
                    othersNoData.setVisibility(View.VISIBLE);
                    othersDepartment.setVisibility(View.GONE);
                }
                else {
                    othersNoData.setVisibility(View.GONE);
                    othersDepartment.setVisibility(View.VISIBLE);

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        TeacherData data = snapshot.getValue(TeacherData.class);
                        list4.add(data);
                    }
                    othersDepartment.setHasFixedSize(true);
                    othersDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4, getContext());
                    othersDepartment.setAdapter(adapter);
                }
                pbot.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                pbot.setVisibility(View.GONE);
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}