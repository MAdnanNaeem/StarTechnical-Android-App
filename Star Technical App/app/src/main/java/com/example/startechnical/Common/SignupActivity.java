
package com.example.startechnical.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.startechnical.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {
   EditText etID_Name;
   EditText etId_Password;
   Button btn_ConfirmPassword;
    DatabaseReference firebaseDatabase;
    DatabaseReference firebaseDatabase2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Init();


        btn_ConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               sinuphere();

            }
        });

    }
        public  void  sinuphere()
        {
            String cmail = etID_Name.getText().toString().trim();
            String cpassword = etId_Password.getText().toString().trim();
            if (cmail.isEmpty()) {
                etID_Name.setError(" Cannot Be Empty ");
            } else if (cpassword.length() != 7)
            {
                etId_Password.setError("Pasword Must 7 character... ");
            }
            else
            {
            mAuth = FirebaseAuth.getInstance();

          mAuth.createUserWithEmailAndPassword(cmail,cpassword)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {
                           Toast.makeText(SignupActivity.this, "Registered Successfully ", Toast.LENGTH_SHORT).show();
                           Intent manage_login = new Intent(SignupActivity.this, Dash_board_D.class);
                           startActivity(manage_login);
                           etID_Name.setText("");
                           etId_Password.setText("");
                       }
                       else
                       {
                           Toast.makeText(SignupActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                       }
                      }
                  });
        }
        }
    private void Init() {
        etID_Name = findViewById(R.id.etID_Name);
        etId_Password = findViewById(R.id.etId_Password);
        btn_ConfirmPassword = findViewById(R.id.btn_ConfirmPassword);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Logins");
    }
}