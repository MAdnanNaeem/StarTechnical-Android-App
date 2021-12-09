package com.example.startechnical.AssisterClasses;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class AdminAdapter extends FirebaseRecyclerAdapter<Admin,AdminAdapter.AdminViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public AdminAdapter(@NonNull FirebaseRecyclerOptions<Admin> options, Context context)
    {
        super(options);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull AdminViewHolder holder, int position, @NonNull Admin model) {
        holder.tv_AdminName.setText(" Name  :   "+model.getName());
        holder.tv_AdminPassword.setText(" Pasword  :   "+model.getPassword());
        holder.tv_AdminPhonNo.setText(" PhonNO  :   "+model.getPhonNo());


       holder.imgEditAdmin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DialogPlus dialog = DialogPlus.newDialog(context)
                       .setContentHolder(new ViewHolder(R.layout.adminupdate))
                       .setGravity(Gravity.CENTER)
                       .setMargin(50,0,50,0)
                       // .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                       .create();
               dialog.show();

               View view  = dialog.getHolderView();
               EditText update_Adminname,update_Adminpassword,update_AdminPhno;
               Button buttonAdminUpdate;
               update_Adminname = view.findViewById(R.id.update_Adminname);
               update_Adminpassword = view.findViewById(R.id.update_Adminpassword);
               update_AdminPhno = view.findViewById(R.id.update_AdminPhno);
               buttonAdminUpdate = view.findViewById(R.id.buttonAdminUpdate);

               update_Adminname.setText(model.getName());
               update_Adminpassword.setText(model.getPassword());
               update_AdminPhno.setText(model.getPhonNo());


               buttonAdminUpdate.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String Update_Adminname = update_Adminname.getText().toString().trim();
                       String UPdate_Adminpassword = update_Adminpassword.getText().toString().trim();
                       String UPdate_AdminPhno = update_AdminPhno.getText().toString().trim();


                       if (Update_Adminname.isEmpty()) {
                           update_Adminname.setError("Field can't be empty");
                       }
                       else if (UPdate_Adminpassword.isEmpty())
                       {
                           update_Adminpassword.setError("Field can't be empty");
                       }
                       else if (UPdate_AdminPhno.isEmpty())
                       {
                           update_AdminPhno.setError("Field can't be empty");
                       }
                       else
                       {
                           Map<String,Object> updatedData = new HashMap<>();
                           updatedData.put("Name",Update_Adminname);
                           updatedData.put("Password",UPdate_Adminpassword);
                           updatedData.put("PhonNo",UPdate_AdminPhno);

                           FirebaseDatabase.getInstance().getReference()
                                   .child("Logins")
                                   .child((getRef(position).getKey()))
                                   .updateChildren(updatedData)
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           Toast.makeText(context,"Updated Sucessfully",Toast.LENGTH_SHORT).show();
                                       }
                                   });
                           dialog.dismiss();
                       }

                   }
               });


           }
       });


        holder.imgDelAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgDelAdmin.getContext());
                builder.setTitle("Delete Item ");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Logins")
                                .child((getRef(position).getKey()))
                                .removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(context,"Deleted Sucessfully",Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adminrecyclerresourcefile, parent, false);

        return new AdminViewHolder(view);
    }

    public  class  AdminViewHolder extends RecyclerView.ViewHolder {

        TextView tv_AdminName;
        TextView tv_AdminPassword;
        TextView tv_AdminPhonNo;
        ImageView imgEditAdmin;
        ImageView imgDelAdmin;
        public AdminViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_AdminName = itemView.findViewById(R.id.tv_AdminName);
            tv_AdminPassword = itemView.findViewById(R.id.tv_AdminPassword);
            tv_AdminPhonNo = itemView.findViewById(R.id.tv_AdminPhonNo);
            imgDelAdmin = itemView.findViewById(R.id.imgDelAdmin);
            imgEditAdmin = itemView.findViewById(R.id.imgEditAdmin);


        }
    }
}
