package com.example.startechnical.Main_Classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.startechnical.AssisterClasses.Task;
import com.example.startechnical.AssisterClasses.TaskAdapter;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Manage_Tasks_D extends AppCompatActivity {

    ImageView btn_AddTask;
    RecyclerView recyclerView;
    TaskAdapter taskAdapter;
   DatabaseReference firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage__tasks);
          Init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<Task> options =
                new FirebaseRecyclerOptions.Builder<Task>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Task") , Task.class)
                        .build();
        taskAdapter = new TaskAdapter(options,this);
        recyclerView.setAdapter(taskAdapter);



        btn_AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(Manage_Tasks_D.this, AddTasks.class);
              startActivity(intent);

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        taskAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
       taskAdapter.stopListening();
    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == key) {
//            if (resultCode == RESULT_OK)
//            {
//                HashMap<String,String> Task = new HashMap<>();
//                Task.put("AssignTo",data.getStringExtra("Task Assi"));
//               Task.put("AssignDate",data.getStringExtra("Assign date"));
//               Task.put("AssignTime",data.getStringExtra("Assi Time"));
//               Task.put("TaskIshu",data.getStringExtra("Task Ishu"));
//               Task.put("FBargin",data.getStringExtra("F Bargin"));
//               Task.put("Feedback",data.getStringExtra("Feed back"));
//               Task.put("ClientName",data.getStringExtra("Client Name"));
//               Task.put("ClientNo",data.getStringExtra("Client No"));
//               Task.put("ClientAdress",data.getStringExtra("Client Adress"));
//               Task.put("ClientPhno",data.getStringExtra("Client_Phno"));
//               Task.put("ClientType",data.getStringExtra("Client Type"));
//
//                    firebaseDatabase.push().setValue(Task)
//            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(Manage_Tasks_D.this," Added Success ",Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(Manage_Tasks_D.this," Failed To Enter ",Toast.LENGTH_SHORT).show();
//                }
//            }).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
//                    Toast.makeText(Manage_Tasks_D.this,"Task  Added Success ",Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//            }
//
//        }
//        else if (resultCode == RESULT_CANCELED) {
//            Toast.makeText(Manage_Tasks_D.this, "User Pressed the Cancel button", Toast.LENGTH_SHORT).show();
//        }
//
//    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // The override(TextSubmit) func is filtering our data when the keyword entered
            @Override
            public boolean onQueryTextSubmit(String s) {
                process_search(s);
                return false;
            }

            // The override(TextChange) func is filtering our data while changing the keyword after entered
            @Override
            public boolean onQueryTextChange(String s)
            {
                process_search(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }



    // Creating a (options)obj of (Task.java) type and then getting the reference  on the bases of "ClientName"
    // start search for the entering keyword till the unicode and pass the (options) obj to the constructor of the (TaskAdapter.java)
    // To set data on the recyclerView against the keyword
    private void process_search(String s) {

        FirebaseRecyclerOptions<Task> options =
                new FirebaseRecyclerOptions.Builder<Task>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child("Task").orderByChild("AssignDate").startAt(s).endAt(s+"\uf8ff"),Task.class)
                        .build();
        taskAdapter = new TaskAdapter(options,this);
        taskAdapter.startListening();
        recyclerView.setAdapter(taskAdapter);

    }
    public  void Init()
    {

        btn_AddTask = findViewById(R.id.btn_AddTask);
        recyclerView = findViewById(R.id.Taskrecycle);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Task");
    }
}