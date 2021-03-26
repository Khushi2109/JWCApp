package rishabh.example.jwcapp.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import rishabh.example.jwcapp.MainActivity;
import rishabh.example.jwcapp.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText regName, father, mother, mobile, course, examRoll, regNo, dateOfBirth, regEmail, regPassword;
    private Button registerBtn;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    private DatabaseReference dbRef;
    private TextView alreadyHaveAnAccount;

    private String name, fathersName, mothersName, mobileNo, courseName, examRollNo, registrationNo, DOB, email, password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        regName = findViewById(R.id.regName);
        father = findViewById(R.id.regFatherName);
        mother = findViewById(R.id.regMotherName);
        mobile = findViewById(R.id.regPhone);
        course = findViewById(R.id.regCourseName);
        examRoll = findViewById(R.id.regExamRollNo);
        regNo = findViewById(R.id.regNo);
        dateOfBirth = findViewById(R.id.regDOB);
        regEmail = findViewById(R.id.regEmail);
        regPassword = findViewById(R.id.regPassword);
        registerBtn = findViewById(R.id.registerBtn);
        progressBar = findViewById(R.id.progress);
        alreadyHaveAnAccount = findViewById(R.id.alreadyHaveAnAccount);

        alreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void openLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void validateData() {
        name = regName.getText().toString();
        fathersName = father.getText().toString();
        mothersName = mother.getText().toString();
        mobileNo = mobile.getText().toString();
        courseName = course.getText().toString();
        examRollNo = examRoll.getText().toString();
        registrationNo = regNo.getText().toString();
        DOB = dateOfBirth.getText().toString();
        email = regEmail.getText().toString();
        password = regPassword.getText().toString();

        if (name.isEmpty()){
            regName.setError("Required");
            regName.requestFocus();
        }
        else if(fathersName.isEmpty()){
            father.setError("Required");
            father.requestFocus();
        }
        else if(mothersName.isEmpty()){
            mother.setError("Required");
            mother.requestFocus();
        }
        else if(mobileNo.isEmpty()){
            mobile.setError("Required");
            mobile.requestFocus();
        }
        else if(mobileNo.length() != 10){
            mobile.setError("Required 10 digit");
            mobile.requestFocus();
        }
        else if(courseName.isEmpty()){
            course.setError("Required");
            course.requestFocus();
        }
        else if(examRollNo.isEmpty()){
            examRoll.setError("Required");
            examRoll.requestFocus();
        }
        else if(registrationNo.isEmpty()){
            regNo.setError("Required");
            regNo.requestFocus();
        }
        else if(DOB.isEmpty()){
            dateOfBirth.setError("Required");
            dateOfBirth.requestFocus();
        }
        else if(email.isEmpty()){
            regEmail.setError("Required");
            regEmail.requestFocus();
        }
        else if(password.isEmpty()){
            regPassword.setError("Required");
            regPassword.requestFocus();
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            createUser();
        }
    }

    private void createUser() {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    uploadData();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    String error = task.getException().getMessage();
                    Toast.makeText(RegisterActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void uploadData() {
        dbRef = reference.child("Users");
        String key = dbRef.push().getKey();

        HashMap<String, String> user = new HashMap<>();
        user.put("key", key);
        user.put("Name", name);
        user.put("FathersName", fathersName);
        user.put("MothersName", mothersName);
        user.put("MobileNo", mobileNo);
        user.put("CourseName", courseName);
        user.put("ExamRollNo", examRollNo);
        user.put("RegistrationNo", registrationNo);
        user.put("DateOfBirth", DOB);
        user.put("Email", email);
        user.put("Verified", "No");

        dbRef.child(key).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "Welcome "+name, Toast.LENGTH_SHORT).show();
                    openMain();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    String error = task.getException().getMessage();
                    Toast.makeText(RegisterActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}