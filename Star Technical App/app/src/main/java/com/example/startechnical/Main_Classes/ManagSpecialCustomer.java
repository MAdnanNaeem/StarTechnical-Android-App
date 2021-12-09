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
import android.widget.TextView;

import com.example.startechnical.AssisterClasses.SpecialCustomer;
import com.example.startechnical.AssisterClasses.SpecialCustomerAdapter;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ManagSpecialCustomer extends AppCompatActivity {
    ImageView btn_AddScust;
    RecyclerView recyclerView;
    TextView tvWellcome;
    SpecialCustomerAdapter specialCustomerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manag_special_customer);
        Init();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<SpecialCustomer> options =
                new FirebaseRecyclerOptions.Builder<SpecialCustomer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SpecialCustomer") , SpecialCustomer.class)
                        .build();
       specialCustomerAdapter = new SpecialCustomerAdapter(options,this);
        recyclerView.setAdapter(specialCustomerAdapter);


        btn_AddScust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManagSpecialCustomer.this,AddSpecialcustomer.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        specialCustomerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
     specialCustomerAdapter.stopListening();
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

        FirebaseRecyclerOptions<SpecialCustomer> options =
                new FirebaseRecyclerOptions.Builder<SpecialCustomer>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("SpecialCustomer").orderByChild("CustomerCnic").startAt(s).endAt(s+"\uf8ff"), SpecialCustomer.class)
                        .build();
        specialCustomerAdapter = new SpecialCustomerAdapter(options,this);
        specialCustomerAdapter.startListening();
        recyclerView.setAdapter(specialCustomerAdapter);

    }

    private void Init() {
        btn_AddScust = findViewById(R.id.btn_AddScust);
        recyclerView = findViewById(R.id.mngspeccustRecycle);
        tvWellcome = findViewById(R.id.tvwellcome);
        FirebaseDatabase.getInstance().getReference().child("SpecialCutomer");
    }


}