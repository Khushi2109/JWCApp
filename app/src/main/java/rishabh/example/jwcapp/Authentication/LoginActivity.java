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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import rishabh.example.jwcapp.MainActivity;
import rishabh.example.jwcapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private TextView createNewAccount, resetPassword;
    private Button loginBtn;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        createNewAccount = findViewById(R.id.createNewAccount);
        loginBtn = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressLogin);
        resetPassword = findViewById(R.id.openForgetPassword);

        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
            }
        });
    }

    private void validateData() {
        email = loginEmail.getText().toString();
        password = loginPassword.getText().toString();

        if (email.isEmpty()) {
            loginEmail.setError("Required");
            loginEmail.requestFocus();
        } else if (password.isEmpty()) {
            loginPassword.setError("Required");
            loginPassword.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            LoginUser();
        }
    }

    private void LoginUser() {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    openMain();
                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(LoginActivity.this, "Error: " + error, Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() != null) {
            openMain();
        }
    }

    private void openRegisterActivity() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void openMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}