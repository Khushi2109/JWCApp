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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import rishabh.example.jwcapp.MainActivity;
import rishabh.example.jwcapp.R;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText resetEmail;
    private Button resetPasswordBtn;
    private ProgressBar progressBar;
    private TextView goBack;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        auth = FirebaseAuth.getInstance();

        resetEmail = findViewById(R.id.resetEmail);
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        progressBar = findViewById(R.id.progressReset);
        goBack = findViewById(R.id.forgot_password_go_back);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        email = resetEmail.getText().toString();

        if (email.isEmpty()){
            resetEmail.setError("Required");
            resetEmail.requestFocus();
        }
        else{
            progressBar.setVisibility(View.VISIBLE);
            ResetPassword();
        }
    }

    private void ResetPassword() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ResetPasswordActivity.this, "Password Reset Link has been sent to your Email", Toast.LENGTH_LONG).show();
                    OpenLoginActivity();
                }
                else{
                    String error = task.getException().getMessage();
                    Toast.makeText(ResetPasswordActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void OpenLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}