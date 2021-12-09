package com.example.startechnical.Main_Classes;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FireBase_Logins extends AppCompatActivity {

    EditText etid_Name;
    EditText etid_Password;
    EditText etid_PhNo;
    Button btnAdd_passWord;
    Button btn_SeeAdmin;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainag_logins);
        Init();
        btnAdd_passWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterEmployeeinDb();

            }
        });
        btn_SeeAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manage_login = new Intent(FireBase_Logins.this, AdminProfiles.class);
                startActivity(manage_login);
            }
        });


    }

    private void EnterEmployeeinDb()
    {
        String etid_name = etid_Name.getText().toString().trim();
        String etid_phno = etid_PhNo.getText().toString().trim();
        String etid_password = etid_Password.getText().toString().trim();

        if (etid_name.isEmpty()) {
            etid_Name.setError(" Cannot Be Empty ");
        } else if (etid_password.isEmpty()) {
            etid_Password.setError(" Cannot Be Empty ");
        }
        else if (etid_phno.length() != 11) {
            etid_PhNo.setError(" Cannot Be Empty ");
        }
        else
        {
            HashMap<String, String> Password = new HashMap<>();
            Password.put("Name", etid_name);
            Password.put("PhonNo", etid_phno);
            Password.put("Password", etid_password);
            firebaseDatabase.child(etid_name).setValue(Password)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(FireBase_Logins.this, " Added Success ", Toast.LENGTH_LONG).show();
                            etid_Name.setText("");
                            etid_PhNo.setText("");
                            etid_Password.setText("");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(FireBase_Logins.this, " Failed To Enter ", Toast.LENGTH_LONG).show();
                }
            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                    Toast.makeText(FireBase_Logins.this, "Finance  Added Success ", Toast.LENGTH_LONG).show();

                }
            });

        }
    }

    private void Init() {
        etid_Password = findViewById(R.id.etid_Password);
        etid_Name = findViewById(R.id.etid_Name);
        etid_PhNo = findViewById(R.id.etid_PhNo);
        btnAdd_passWord = findViewById(R.id.btnAdd_passWord);
        btn_SeeAdmin = findViewById(R.id.btn_SeeAdmin);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Logins");
    }

}