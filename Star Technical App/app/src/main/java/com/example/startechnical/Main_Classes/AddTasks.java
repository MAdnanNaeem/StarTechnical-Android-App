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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class AddTasks extends AppCompatActivity {


    EditText task_id;
    EditText Assign_To;
    EditText Assign_Date;
    EditText Assign_Time;
    EditText Task_Issue;
    EditText Bargain_Amount;
    EditText FeedBack;
    EditText Client_Name;
    EditText Client_Id;
    EditText Client_Address;
    EditText Client_cellno;
    EditText Client_Type;
    Button btn_submit;
    Button btn_Cancel;
    DatabaseReference firebaseDatabase;
     // FirebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        Init();
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Task");


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddData_In_Database();

            }
        });
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(AddTasks.this, "Back Button Pressed", Toast.LENGTH_SHORT).show();
                AddTasks.this.finish();
            }
        });

    }
   private void AddData_In_Database()
            {

                String taskId =    task_id.getText().toString().trim();
                String assign_to = Assign_To.getText().toString().trim();
                String assign_date = Assign_Date.getText().toString().trim();
                String assign_time = Assign_Time.getText().toString().trim();
                String task_issue = Task_Issue.getText().toString().trim();
                String bargain_amount = Bargain_Amount.getText().toString().trim();
                String feedback = FeedBack.getText().toString().trim();
                String client_name = Client_Name.getText().toString().trim();
                String client_id = Client_Id.getText().toString().trim();
                String client_ph = Client_cellno.getText().toString().trim();
                String client_address = Client_Address.getText().toString().trim();
                String client_type = Client_Type.getText().toString().trim();

                if(taskId.isEmpty())
                {
                    task_id.setError("Field can't be empty");
                }
                else if(assign_to.isEmpty())
                {
                    Assign_To.setError("Field can't be empty");
                }
                else if(assign_date.isEmpty())
                {
                    Assign_Date.setError("Field can't be empty");
                }
                else if(assign_time.isEmpty())
                {
                    Assign_Time.setError("Field can't be empty");
                }
                else if(task_issue.isEmpty())
                {
                    Task_Issue.setError("Field can't be empty");
                }
                else if(feedback.isEmpty())
                {
                    FeedBack.setError("Field can't be empty");
                }
                else if(client_name.isEmpty())
                {
                    Client_Name.setError("Field can't be empty");
                }else if(client_id.isEmpty())
                {
                    Client_Id.setError("Field can't be empty");
                }else if(client_ph.length() != 11)
                {
                    Client_cellno.setError("Field can't be empty");
                }else if(client_address.isEmpty())
                {
                    Client_Address.setError("Field can't be empty");
                }
                else
                {
                    // HashMap allow us to copy multiple data into a single String_type obj named (task)
                    HashMap<String,String> Task = new HashMap<>();
                    Task.put("Task_Id",taskId);
                    Task.put("AssignTo",assign_to);
                    Task.put("AssignDate",assign_date);
                    Task.put("AssignTime",assign_time);
                    Task.put("Task_Issue",task_issue);
                    Task.put("Bargain_Amount",bargain_amount);
                    Task.put("Feedback",feedback);
                    Task.put("ClientName",client_name);
                    Task.put("ClientId",client_id);
                    Task.put("ClientAddress",client_address);
                    Task.put("Client_cellno",client_ph);
                    Task.put("ClientType",client_type);
                    btn_submit.setVisibility(View.GONE);



                  //   sending object Task and map data in the DB on a node named (Task)
                  //  firebaseDatabase.child()

                    firebaseDatabase.push().setValue(Task)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(AddTasks.this," Added Success ",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddTasks.this," Failed To Create Task",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                            Toast.makeText(AddTasks.this,"Task Created",Toast.LENGTH_SHORT).show();

                            task_id.setText("");
                            Assign_To.setText("");
                            Assign_Date.setText("");
                            Assign_Time.setText("");
                            Task_Issue.setText("");
                            Bargain_Amount.setText("");
                            FeedBack.setText("");
                            Client_Name.setText("");
                            Client_Id.setText("");
                            Client_cellno.setText("");
                            Client_Address.setText("");
                            Client_Type.setText("");
                            btn_submit.setVisibility(View.VISIBLE);
                        }
                    });
//                    Intent intent = new Intent();
//                    intent.putExtra("Task Assi",assign_to);
//                    intent.putExtra("Assign date",assign_date);
//                    intent.putExtra("Assi Time",assign_time);
//                    intent.putExtra("Task Ishu",task_issue);
//                    intent.putExtra("F Bargin",bargain_amount);
//                    intent.putExtra("Feed back",feedback);
//                    intent.putExtra("Client Name",client_name);
//                    intent.putExtra("Client No",client_id);
//                    intent.putExtra("Client Adress",client_address);
//                    intent.putExtra("Client_Phno",client_ph);
//                    intent.putExtra("Client Type",client_type);
//                    setResult(RESULT_OK,intent);
//                    AddTasks.this.finish();
                }

            }

    public  void Init()
    {
        task_id = findViewById(R.id.addTask_id);
        Assign_To = findViewById(R.id.Ass_To);
        Assign_Date = findViewById(R.id.T_Assign_date);
        Assign_Time = findViewById(R.id.t_Assi_Time);
        Task_Issue = findViewById(R.id.addTask_ishu);
        Bargain_Amount = findViewById(R.id.F_Barging);
        FeedBack = findViewById(R.id. Feed_Back);
        Client_Name = findViewById(R.id.Client_name);
        Client_Id = findViewById(R.id.client_no);
        Client_Address = findViewById(R.id.C_Adress);
        Client_cellno = findViewById(R.id.C_Ph_No);
        Client_Type = findViewById(R.id.C_Type);
        btn_submit = findViewById(R.id.btn_submit);
        btn_Cancel = findViewById(R.id.btn_cancel);

    }


}