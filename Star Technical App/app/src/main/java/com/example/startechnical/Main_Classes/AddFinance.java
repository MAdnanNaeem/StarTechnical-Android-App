package com.example.startechnical.Main_Classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.startechnical.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddFinance extends AppCompatActivity {
    EditText  Food_Expence;
    EditText  Petroll_Expence;
    EditText  Acessories_Expence;
    EditText  Total_Amount;
    EditText   Task_No;
    Button btnAdd_Indb;
    private FirebaseAuth mAuth;
    Button btn_cancel;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_finance);
        Init();
        btnAdd_Indb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnterEmployeeinDb();

            }
        });
     btn_cancel.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Toast.makeText(AddFinance.this,"You Have Press Cancel Button ",Toast.LENGTH_SHORT).show();
             AddFinance.this.finish();

         }
     });
    }



    private void EnterEmployeeinDb() {


        String F_Expence = Food_Expence.getText().toString().trim();
        String Pet_Expence = Petroll_Expence.getText().toString().trim();
        String Acess_Expence = Acessories_Expence.getText().toString().trim();
        String Toatal_amount = Total_Amount.getText().toString().trim();
        String Task_no = Task_No.getText().toString().trim();

        if (F_Expence.isEmpty()) {
                Food_Expence.setError("Food Expence  Cannot Be Empty ");
        } else
            if (Pet_Expence.isEmpty()) {
                 Petroll_Expence.setError("Petroll Expence  Cannot Be Empty ");
        } else if (Acess_Expence.isEmpty()) {
                  Acessories_Expence.setError("Acessories Expence  Cannot Be Empty ");
        } else if (Toatal_amount.isEmpty()) {
                 Total_Amount.setError("Task Total Amount Cannot Be Empty ");
        } else if (Task_no.isEmpty()) {
                  Task_No.setError("Task No Cannot Be Empty ");
        } else {


            

         //   btnAdd_Indb.setVisibility(View.GONE);



            firebaseDatabase
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.child(F_Expence).exists())
                            {

                                Food_Expence.setText("");

                            }
                            else
                            {
                                String result;
                                try{
                                    int t_amount = Integer.parseInt(Toatal_amount);
                                    int value = Integer.parseInt(F_Expence)+Integer.parseInt(Pet_Expence)+Integer.parseInt(Acess_Expence);
                                    int  tvalue  = t_amount - value;
                                    result = String.valueOf(tvalue);
                                }
                                catch(NumberFormatException ex){
                                    //either a or b is not a number
                                    result = "Invalid input";
                                }
                                HashMap<String, String> Finance = new HashMap<>();
                                Finance.put("FoodExpence", F_Expence);
                                Finance.put("PetRollExpence", Pet_Expence);
                                Finance.put("AcessoriesExpence", Acess_Expence);
                                Finance.put("TotalAmount", Toatal_amount);
                                Finance.put("TaskProfit", result);
                                Finance.put("TaskNo", Task_no);

                            //    mAuth.getCurrentUser();
                                firebaseDatabase.child(F_Expence).setValue(Finance)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(AddFinance.this, " Added Success ", Toast.LENGTH_LONG).show();
                                                Food_Expence.setText("");
                                                Petroll_Expence.setText("");
                                                Acessories_Expence.setText("");
                                                Total_Amount.setText("");
                                                Task_No.setText("");
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AddFinance.this, " Failed To Enter ", Toast.LENGTH_LONG).show();
                                    }
                                }).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                                        Toast.makeText(AddFinance.this, "Finance  Added Success ", Toast.LENGTH_LONG).show();
                                        btnAdd_Indb.setVisibility(View.VISIBLE);
                                    }
                                });




                                //      Toast.makeText(AddFinance.this, "bhai nhi huwa  ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });








//            firebaseDatabase.child(F_Expence).setValue(Finance)
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(AddFinance.this, " Added Success ", Toast.LENGTH_LONG).show();
//                            Food_Expence.setText("");
//                            Petroll_Expence.setText("");
//                            Acessories_Expence.setText("");
//                            Total_Amount.setText("");
//                            Task_No.setText("");
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(AddFinance.this, " Failed To Enter ", Toast.LENGTH_LONG).show();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
//                    Toast.makeText(AddFinance.this, "Finance  Added Success ", Toast.LENGTH_LONG).show();
//                      btnAdd_Indb.setVisibility(View.GONE);
//                }
//            });
        }
    }

            private void Init() {
                Food_Expence = findViewById(R.id.Food_Expence);
                Petroll_Expence = findViewById(R.id.Petroll_Expence);
                Acessories_Expence = findViewById(R.id.Acessories_Expence);
                Total_Amount = findViewById(R.id.Total_Amount);
                Task_No = findViewById(R.id.Task_No);
                btnAdd_Indb = findViewById(R.id.btnAdd_Indb);
                btn_cancel = findViewById(R.id.btnAdd_Cancel);
                firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Finance");

            }
        }


