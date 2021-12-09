package com.example.startechnical.Common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.startechnical.Main_Classes.AddTasks;
import com.example.startechnical.Main_Classes.FireBase_Logins;
import com.example.startechnical.Main_Classes.Manage_Documents_D;
import com.example.startechnical.Main_Classes.ManagSpecialCustomer;
import com.example.startechnical.Main_Classes.Manage_Profile_D;
import com.example.startechnical.Main_Classes.Manage_Finance_D;
import com.example.startechnical.AssisterClasses.Finance;
import com.example.startechnical.AssisterClasses.Task;
import com.example.startechnical.AssisterClasses.TaskAdapter;
import com.example.startechnical.Main_Classes.Manage_Tasks_D;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.BuildConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dash_board_D extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menu_icon;
    ImageView Dashboard_AddTask;
    TextView Manage_Tasks;
    TextView Manage_Profile;
    TextView Manage_Finance;
    TextView Manage_Documents;
    TextView Special_customer;
    TextView nav_login;
    TextView nav_logout;
    TextView nav_Share;
    TextView nav_Rate;
    TextView Dashboard_food;
    TextView Dashboard_accessories;
    TextView totalAmount;
    TextView totalprofith;
    TextView Dashboard_petrol;
    RecyclerView recyclerView;
    DatabaseReference firebaseDatabase;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        // calling Init(initializing objects) function
        Init();
        Sumpetroll();
        SumFood();
        SumAccessories();
        SumToatalExp();
        SumProfit();
        if(ContextCompat.checkSelfPermission(Dash_board_D.this,Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Dash_board_D.this, new String[]{Manifest.permission.SEND_SMS}, 1);
        }
        //assigning the reference to the firebaseDatabase object of the child named task;
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Task");

        // calling the navigation function
        click_navigation();

        // Setting the recyclerView_Layout to Linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Fixing the recyclerView chucks size for the efficiency of the app
        recyclerView.setHasFixedSize(true);

        // creating an Firebase object(options) of type (Task.java) class
        // taking the references of the child "task" into (options)

        FirebaseRecyclerOptions<Task> options = new FirebaseRecyclerOptions.Builder<Task>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Task") , Task.class)
                        .build();
        // & passing the  reference of the (options) to the constructor of (Task_Adapter.java) class
        taskAdapter = new TaskAdapter(options,this);
        recyclerView.setAdapter(taskAdapter);


        //Staring the Activity named (AddTask.java)
        Dashboard_AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTask = new Intent(Dash_board_D.this, AddTasks.class);
                startActivity(addTask);
            }
        });

    }

   

    //The following two functions are used to enhance the efficiency of the app
    // OnStart() will load the data on the recyclerViews as the activity will start
    // OnStop() will stop loading the data on the recyclerViews as the activity will stop

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

    // We created our own search_menu layout of type (Menu) and inflating our view with the menu & initialized it into item
    // getting the text from search bar and saving into (searchView) obj
    // by using (setOnQueryTextLis) we are filtering our data to get the final result
    // by using the following Override_func


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


    // opening the drawerLayout by sliding or clicking the button

    public void click_navigation() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        menu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    // if the drawer is opened when back button pressed the drawer closed first and on again pressing the back button
    // the activity or app will close

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else super.onBackPressed();
    }
    private void Sumpetroll()
    {
     DatabaseReference   firebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("Finance");
        firebaseDatabase2
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer total = 0;
                for( DataSnapshot ds :dataSnapshot.getChildren()) {
                    Finance finance = ds.getValue(Finance.class);
                    Integer cost = Integer.valueOf(finance.getPetRollExpence());
                    total = total + cost;
                }
                Dashboard_petrol.setText(""+ total);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void SumFood()
    {
        DatabaseReference   firebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("Finance");
        firebaseDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer total = 0;

                for( DataSnapshot ds :dataSnapshot.getChildren()) {
                    Finance finance = ds.getValue(Finance.class);
                    Integer cost = Integer.valueOf(finance.getFoodExpence());
                    total = total + cost;
                }
                Dashboard_food.setText(""+ total);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void SumAccessories()
    {
        DatabaseReference   firebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("Finance");
        firebaseDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer total = 0;

                for( DataSnapshot ds :dataSnapshot.getChildren()) {
                    Finance finance = ds.getValue(Finance.class);
                    Integer cost = Integer.valueOf(finance.getAcessoriesExpence());
                    total = total + cost;
                }
                Dashboard_accessories.setText(""+ total);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    private void SumToatalExp()
    {
        DatabaseReference   firebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("Finance");
        firebaseDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Integer total = 0;
                for( DataSnapshot ds :dataSnapshot.getChildren()) {
                    Finance finance = ds.getValue(Finance.class);
                    Integer cost = Integer.valueOf(finance.getTotalAmount());
                    total = total + cost;
                }

                totalAmount.setText(""+ total);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    private void SumProfit()
    {
        DatabaseReference   firebaseDatabase2 = FirebaseDatabase.getInstance().getReference().child("Finance");
        firebaseDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer total = 0;

                for( DataSnapshot ds :dataSnapshot.getChildren()) {
                    Finance finance = ds.getValue(Finance.class);
                    Integer cost = Integer.valueOf(finance.getTaskProfit());
                    total = total + cost;

                }

                totalprofith.setText(""+ total);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
    public void Init() {
        drawerLayout = findViewById(R.id.drawr_layout);
        navigationView = findViewById(R.id.navigation_view);
        menu_icon = findViewById(R.id.menu_icon);
        Manage_Tasks = findViewById(R.id.Manage_Tasks);
        Manage_Profile = findViewById(R.id.Manage_prof);
        Dashboard_AddTask = findViewById(R.id.DAddTask);
        Manage_Finance = findViewById(R.id.Manag_finance);
        Manage_Documents = findViewById(R.id.Manag_Documents);
        Special_customer = findViewById(R.id.Special_customer);
        nav_login = findViewById(R.id.nav_login );
        nav_logout = findViewById(R.id.nav_logout);
        nav_Share = findViewById(R.id.nav_Share);
        nav_Rate = findViewById(R.id.nav_Rate);
        recyclerView = findViewById(R.id.dashrecycle);
        Dashboard_petrol = findViewById(R.id.Dpetroll);
        Dashboard_food = findViewById(R.id.Dfood);
        Dashboard_accessories = findViewById(R.id.Dacesories);
        totalAmount = findViewById(R.id.totalamount);
        totalprofith = findViewById(R.id.totalprofith);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Manage_Tasks:{
                Intent intent = new Intent(Dash_board_D.this, Manage_Tasks_D.class);
                startActivity(intent);
                break;
        }
            case R.id.Manage_prof:{
                Intent manage_pr = new Intent(Dash_board_D.this, Manage_Profile_D.class);
                startActivity(manage_pr);
            break;}
            case R.id.Manag_finance:{
                Intent manage_finance = new Intent(Dash_board_D.this, Manage_Finance_D.class);
                startActivity(manage_finance);
            break;}
            case R.id.Manag_Documents:{
                Intent manage_documents = new Intent(Dash_board_D.this, Manage_Documents_D.class);
                startActivity(manage_documents);
                break;}
            case R.id.Special_customer:{
                Intent manage_SpecCustomer = new Intent(Dash_board_D.this, ManagSpecialCustomer.class);
                startActivity(manage_SpecCustomer);
                break;}

            case R.id.nav_login:{
                Intent manage_login = new Intent(Dash_board_D.this, FireBase_Logins.class);
                startActivity(manage_login);
                break;
            }
            case R.id.nav_logout:{
                Intent manage_login = new Intent(Dash_board_D.this, SignupActivity.class);
                startActivity(manage_login);
                break;
            }
            case R.id.nav_Share:
                {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT,
                            "MY Application === https://drive.google.com/file/d/1VfNVPPeAF8pJ4AzG5MzJ9AHivAs8kg5T/view?usp=sharing " + BuildConfig.APPLICATION_ID);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);

                break;
            }
            case R.id.nav_Rate:

                {
                    try{
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + BuildConfig.APPLICATION_ID )));
                    }
                    catch (ActivityNotFoundException e){
                        Toast.makeText(this, "Error"+ e, Toast.LENGTH_SHORT).show();
                    }
                //    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" +BuildConfig.APPLICATION_ID )));
                    //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("MY Application ===" + BuildConfig.APPLICATION_ID)));
//                    try {
//                        Uri marketUri = Uri.parse("MY Application === https://drive.google.com/file/d/1VfNVPPeAF8pJ4AzG5MzJ9AHivAs8kg5T/view?usp=sharing " + getPackageName());
//                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
//                        startActivity(marketIntent);
//                    }catch(ActivityNotFoundException e) {
//                        Uri marketUri = Uri.parse("MY Application === https://drive.google.com/file/d/1VfNVPPeAF8pJ4AzG5MzJ9AHivAs8kg5T/view?usp=sharing " + getPackageName());
//                        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
//                        startActivity(marketIntent);
//                    }
                break;
           }




      }
       return true;
    }

}

