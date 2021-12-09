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

import com.example.startechnical.AssisterClasses.Finance;
import com.example.startechnical.AssisterClasses.FinanceAdapter;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Manage_Finance_D extends AppCompatActivity {
    ImageView Btn_Addfinance;
    RecyclerView recyclerView;
    FinanceAdapter financeAdapter;
    final int key = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_finance);
        Init();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FirebaseRecyclerOptions<Finance> options =
                new FirebaseRecyclerOptions.Builder<Finance>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Finance") , Finance.class)
                        .build();
       financeAdapter = new FinanceAdapter(options,this);
       recyclerView.setAdapter(financeAdapter);

        Btn_Addfinance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Manage_Finance_D.this,AddFinance.class),key);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        financeAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        financeAdapter.stopListening();
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

        FirebaseRecyclerOptions<Finance> options =
                new FirebaseRecyclerOptions.Builder<Finance>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Finance").orderByChild("TaskNo").startAt(s).endAt(s+"\uf8ff"), Finance.class)
                        .build();
        financeAdapter = new FinanceAdapter(options,this);
        financeAdapter.startListening();
        recyclerView.setAdapter(financeAdapter);

    }















    private void Init() {
        Btn_Addfinance = findViewById(R.id.btnAdd_Finance);
        recyclerView = findViewById(R.id.financeRecycler);
//       recyclerView.setLayoutManager(new LinearLayoutManager(this));
//       recyclerView.setHasFixedSize(true);
    }
}