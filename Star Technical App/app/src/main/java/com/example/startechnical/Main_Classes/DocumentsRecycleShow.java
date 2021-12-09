package com.example.startechnical.Main_Classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.startechnical.AssisterClasses.DocumentsAdapter;
import com.example.startechnical.AssisterClasses.UploadsofDocuments;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DocumentsRecycleShow extends AppCompatActivity {


    RecyclerView recyclerView;
    DocumentsAdapter documentsAdapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents_recycle_show);

        recyclerView = findViewById(R.id.recyclerdocuments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<UploadsofDocuments> options =
                new FirebaseRecyclerOptions.Builder<UploadsofDocuments>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Uploads") , UploadsofDocuments.class)
                        .build();
        documentsAdapter = new DocumentsAdapter(options,this);
        recyclerView.setAdapter(documentsAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        documentsAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        documentsAdapter.stopListening();
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

        FirebaseRecyclerOptions<UploadsofDocuments> options =
                new FirebaseRecyclerOptions.Builder<UploadsofDocuments>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Uploads").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), UploadsofDocuments.class)
                        .build();
        documentsAdapter = new DocumentsAdapter(options,this);
        documentsAdapter.startListening();
        recyclerView.setAdapter(documentsAdapter);

    }

}