package com.ruzaki.icestore;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button signup_button;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //already logged in
            finish();

            //startActivity(new Intent(getApplicationContext(), ProfileActiviy.class));
        }

        username = findViewById(R.id.username_text);
        password = findViewById(R.id.password_text);

        signup_button = findViewById(R.id.signup_button);

        signup_button.setOnClickListener(this);
    }

    private void registerUser() {

        String email_id_s = username.getText().toString().trim();
        String password_s = password.getText().toString().trim();

        if(TextUtils.isEmpty(email_id_s)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password_s)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }


        firebaseAuth.createUserWithEmailAndPassword(email_id_s, password_s)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            //startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void onLoginClicked(View view) {

    }

    //public void onSignUpClicked(View view){
      //  if (view == signup_button){
        //    registerUser();
        //}
    //}

    public void onForgotPasswordClicked(View view) {

    }

    @Override
    public void onClick(View v) {
        if (v == signup_button){
            registerUser();
        }
    }
}
