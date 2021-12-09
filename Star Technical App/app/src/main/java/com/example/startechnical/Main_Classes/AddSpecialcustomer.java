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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AddSpecialcustomer extends AppCompatActivity {
    EditText Spcust_Name;
    EditText  Spcust_Cnic;
    EditText  Spcust_No;
    EditText   Spcust_PhNo;
    EditText   Spcust_Adress;
    Button btn_submit;
    Button btn_cancel;
    DatabaseReference firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_specialcustomer);
        Init();
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddInFirBase();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddSpecialcustomer.this,"You Have Press Cancel Button ",Toast.LENGTH_SHORT).show();
                AddSpecialcustomer.this.finish();

            }
        });


   }




    private void AddInFirBase() {
        String  SpcustName = Spcust_Name.getText().toString().trim();
        String  SpcustCnic = Spcust_Cnic.getText().toString().trim();
        String  SpcustNo = Spcust_No.getText().toString().trim();
        String  SpcustPhno = Spcust_PhNo.getText().toString().trim();
        String SpcustAdress = Spcust_Adress.getText().toString().trim();

        if (SpcustName.isEmpty())
        {
            Spcust_Name.setError("  Cannot Be Empty ");
        }
        else if (SpcustCnic.length() != 13)
        {
            Spcust_Cnic.setError(" Cannot Be Empty length = 13 ");
        }
        else if (SpcustNo .isEmpty())
        {
            Spcust_No.setError(" Cannot Be Empty  ");
        }
        else if (SpcustPhno.length() !=11 )
        {
            Spcust_PhNo.setError(" Cannot Be Empty length = 11 ");
        }
        else if (SpcustAdress.isEmpty())
        {
            Spcust_Adress.setError(" Cannot Be Empty ");
        }
else {

        int t = 1;
        String TaskCompleated = String.valueOf(t);
        HashMap<String, String> SpecialCustomer = new HashMap<>();
        SpecialCustomer.put("CustomerName",SpcustName);
        SpecialCustomer.put("CustomerCnic", SpcustCnic);
        SpecialCustomer.put("CustomerNo", SpcustNo);
        SpecialCustomer.put("CustomerPhNo", SpcustPhno);
        SpecialCustomer.put("TaskCompleated", TaskCompleated);
        SpecialCustomer.put("CustomerAdress", SpcustAdress);
       btn_submit.setVisibility(View.GONE);





        firebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.child(SpcustCnic).exists()){
//                    Toast.makeText(AddSpecialcustomer.this, "S_Customer Already Exist ", Toast.LENGTH_LONG).show();
//                }
//                else
//                {
                    firebaseDatabase.child(SpcustCnic).setValue(SpecialCustomer)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddSpecialcustomer.this, " Added Success ", Toast.LENGTH_LONG).show();
                                    Spcust_Name.setText("");
                                    Spcust_Cnic.setText("");
                                    Spcust_No.setText("");
                                    Spcust_PhNo.setText("");
                                    Spcust_Adress.setText("");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddSpecialcustomer.this, " Failed To Enter ", Toast.LENGTH_LONG).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                            Toast.makeText(AddSpecialcustomer.this, "S_Customer  Added Success ", Toast.LENGTH_LONG).show();
                             btn_submit.setVisibility(View.VISIBLE);
                        }
                    });
                }
          //  }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddSpecialcustomer.this, "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
        }
    }


    private void Init() {
        Spcust_Name = findViewById(R.id.Spcust_Name);
        Spcust_Cnic = findViewById(R.id.Spcust_Cnic);
        Spcust_No = findViewById(R.id.Spcust_No);
        Spcust_PhNo = findViewById(R.id.Spcust_PhNo);
        Spcust_Adress = findViewById(R.id.Spcust_Adress);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("SpecialCustomer");
    }
}