package com.example.startechnical.Main_Classes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class Add_Employee extends AppCompatActivity {

      EditText Emp_name;
      EditText Emp_cnic;
      EditText Emp_age;
      EditText Emp_Adress;
      EditText Emp_roll;
      EditText Emp_Gender;
      EditText Emp_phNo;
      EditText Emp_Salary;
      Button btn_submit;
      Button btn_Cancel;
     DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__employee);
         Init();
         btn_submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 EnterEmployeeinDb();
                 //Add_Employee.this.finish();

             }
         });
       btn_Cancel.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("ShowToast")
           @Override
           public void onClick(View v) {
               Toast.makeText(Add_Employee.this,"You Have Press Back Button ",Toast.LENGTH_SHORT).show();
               Add_Employee.this.finish();
           }
       });
    }

    private void EnterEmployeeinDb() {


        String empname = Emp_name.getText().toString().trim();
        String EmpCnic = Emp_cnic.getText().toString().trim();
        String EmpAge = Emp_age.getText().toString().trim();
        String EmpAdress = Emp_Adress.getText().toString().trim();
        String EmpGender = Emp_Gender.getText().toString().trim();
        String EmpRoll = Emp_roll.getText().toString().trim();
        String EmpSalar = Emp_Salary.getText().toString().trim();
        String EmpPhno = Emp_phNo.getText().toString().trim();


        if (empname.isEmpty() )
        {
            Emp_name.setError("Employee Name Cannot Be Empty ");
        }
        else if (EmpCnic.length() != 13)
        {
            Emp_cnic.setError(" Cannot Be Empty || = 13 ");
        }
        else if (EmpAge.isEmpty())
        {
            Emp_age.setError("Employee Age Cannot Be Empty ");
        }
        else if (EmpAdress.isEmpty())
        {
            Emp_Adress.setError("Emp Adress Cannot Be Empty ");
        }
        else if (EmpRoll.isEmpty())
        {
            Emp_roll.setError("Emp Roll Cannot Be Empty ");

        } else if (EmpSalar.isEmpty())
        {
            Emp_Salary.setError("Emp Salarry Cannot Be Empty ");
        }
        else if (EmpPhno.length() != 11)
        {
            Emp_phNo.setError("Cannot Be Empty lenght shouled be 11 ");
        }
        else if (EmpGender.isEmpty())
        {
            Emp_Gender.setError("Emp Gender Cannot Be Empty ");
        }
        else {
            HashMap<String, String> Employee = new HashMap<>();
            Employee.put("Employee_Name", empname);
            Employee.put("EmployeeCnic",EmpCnic );
            Employee.put("EmployeeAgge", EmpAge);
            Employee.put("EmployeeAdress", EmpAdress);
            Employee.put("EmployeeRoll", EmpRoll);
            Employee.put("EmployeeGender", EmpGender);
            Employee.put("EmployeeSallary",EmpSalar);
            Employee.put("EmployeePhNo", EmpPhno);
            btn_submit.setVisibility(View.GONE);
            firebaseDatabase.push().setValue(Employee)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Add_Employee.this, " Added Success ", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_Employee.this, " Failed To Enter ", Toast.LENGTH_SHORT).show();
                }
            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                    Toast.makeText(Add_Employee.this, "Task  Added Success ", Toast.LENGTH_SHORT).show();
                    Emp_name.setText("");
                    Emp_cnic.setText("");
                    Emp_age.setText("");
                    Emp_Adress.setText("");
                    Emp_roll.setText("");
                    Emp_Gender.setText("");
                    Emp_phNo.setText("");
                    Emp_Salary.setText("");
                    btn_submit.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public void  Init()
    {
        Emp_name = findViewById(R.id.Emp_name);
        Emp_cnic = findViewById(R.id.Emp_cnic);
        Emp_age = findViewById(R.id.Emp_age);
        Emp_Adress = findViewById(R.id.Emp_Adress);
        Emp_roll = findViewById(R.id.Emp_roll);
        Emp_Gender = findViewById(R.id.Emp_Gender);
        Emp_phNo = findViewById(R.id.Emp_phNo);
        Emp_Salary = findViewById(R.id.Emp_salary);
        btn_submit = findViewById(R.id.btn_submit);
        btn_Cancel = findViewById(R.id.btn_cancel);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Employee");
    }
}