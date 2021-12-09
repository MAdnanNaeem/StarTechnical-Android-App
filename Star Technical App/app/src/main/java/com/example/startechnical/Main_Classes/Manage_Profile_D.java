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

import com.example.startechnical.AssisterClasses.Employee;
import com.example.startechnical.AssisterClasses.EmployeeAdapter;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Manage_Profile_D extends AppCompatActivity {


    ImageView btn_AddEmp;
    final int key = 3;
   RecyclerView recyclerView;
    EmployeeAdapter employeeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manag__profile);
          Init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<Employee> options =
                new FirebaseRecyclerOptions.Builder<Employee>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Employee") , Employee.class)
                        .build();

        employeeAdapter = new EmployeeAdapter(options,this);
        recyclerView.setAdapter(employeeAdapter);

        btn_AddEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Manage_Profile_D.this, Add_Employee.class),key);
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        employeeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        employeeAdapter.stopListening();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item = menu.findItem(R.id.menusearch);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s)
            {
                processearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void processearch(String s) {

        FirebaseRecyclerOptions<Employee> options =
                new FirebaseRecyclerOptions.Builder<Employee>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Employee").orderByChild("Employee_Name").startAt(s).endAt(s+"\uf8ff"), Employee.class)
                        .build();
        employeeAdapter = new EmployeeAdapter(options,this);
        employeeAdapter.startListening();
        recyclerView.setAdapter(employeeAdapter);

    }


    private void Init() {
        btn_AddEmp = findViewById(R.id.btn_AddScust);
       recyclerView = findViewById(R.id.mngspeccustRecycle);

    }
}