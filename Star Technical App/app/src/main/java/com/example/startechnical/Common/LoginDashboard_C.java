package com.example.startechnical.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.startechnical.R;
import com.google.firebase.auth.FirebaseAuth;

public class LoginDashboard_C extends AppCompatActivity {
    EditText etID_Email;
    EditText etId_Passwordreg;
    Button btn_Confirm;
    Button btn_Sign_Up;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dashboard);
        Init();
        mAuth = FirebaseAuth.getInstance();



          btn_Confirm.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String name = etID_Email.getText().toString().trim();
                  String password = etId_Passwordreg.getText().toString().trim();

                  String Cname = "asdf";
                  String Cpassword = "1234";
                  if ( ! name.equals(Cname) )
                  {
                      etID_Email.setError(" Wrong name ");
                  }
                  else if(! password.equals(Cpassword))
                  {
                      etId_Passwordreg.setError(" Wrong Password ");

                  }
                  else if(name.equals(Cname) &&  password.equals(Cpassword))
                  {
                      Intent manage_login = new Intent(LoginDashboard_C.this, Dash_board_D.class);
                      startActivity(manage_login);
                      etID_Email.setText("");
                      etId_Passwordreg.setText("");
                  }
                  else
                  {
                      Toast.makeText(LoginDashboard_C.this, " Error ", Toast.LENGTH_SHORT).show();
                  }

              }
          });


//        btn_Confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = etID_Email.getText().toString().trim();
//                String password = etId_Passwordreg.getText().toString().trim();
//                if (name.isEmpty()) {
//                    etID_Email.setError(" Cannot Be Empty ");
//                } else
//                if (password.length() != 7 ) {
//                    etId_Passwordreg.setError(" Password Must 7 character...");
//                }
//                 else {
//                    mAuth.signInWithEmailAndPassword(name, password)
//                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    if (task.isSuccessful()) {
//                                        Intent intent = new Intent(LoginDashboard_C.this, Dash_board_D.class);
//                                        startActivity(intent);
//                                        etID_Email.setText("");
//                                        etId_Passwordreg.setText("");
//                                    }
//                                    else
//                                        {
//                                            Toast.makeText(LoginDashboard_C.this, " Please Enter correct email or password  ", Toast.LENGTH_SHORT).show();
//                                        etID_Email.setText("");
//                                        etId_Passwordreg.setText("");
//                                         }
//                                }
//                            });
//                }
//            }
//        });
        btn_Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginDashboard_C.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Init() {
        etID_Email = findViewById(R.id.etID_email);
        etId_Passwordreg = findViewById(R.id.etId_Passwordreg);
        btn_Confirm = findViewById(R.id.btn_Confirm);
        btn_Sign_Up = findViewById(R.id.btn_Sign_Up);
    }
}