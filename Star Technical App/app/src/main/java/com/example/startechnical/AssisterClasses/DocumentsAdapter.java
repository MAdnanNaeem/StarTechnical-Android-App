package com.example.startechnical.AssisterClasses;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.example.startechnical.Main_Classes.DocumentseeOncliked;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class DocumentsAdapter extends FirebaseRecyclerAdapter<UploadsofDocuments,DocumentsAdapter.DocumentsViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public DocumentsAdapter(@NonNull FirebaseRecyclerOptions<UploadsofDocuments> options,Context context) {
        super(options);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull DocumentsViewHolder holder, int position, @NonNull UploadsofDocuments model)
    {
        holder.TvName.setText("  Name       "+model.getName());
        Picasso.with(holder.ImDocuments.getContext())
                .load(model.getImagUri())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.ImDocuments);
//        Glide.with(holder.ImDocuments.getContext()).load(model.getImagUri())
//                .into(holder.ImDocuments);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imguri = model.getImagUri();
                Intent intent = new Intent(context, DocumentseeOncliked.class);
                intent.putExtra("imguri",imguri);
                context.startActivity(intent);

            }
        });



  holder.D_edit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          DialogPlus dialog = DialogPlus.newDialog(context)
                  .setContentHolder(new ViewHolder(R.layout.documentupdatesname))
                  .setGravity(Gravity.CENTER)
                  .setMargin(50,0,50,0)
                  // .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                  .create();
          dialog.show();
          View view  = dialog.getHolderView();
          EditText D_UPName;
          Button Dbtn_Update;

          D_UPName= view.findViewById(R.id.D_UPName);
          Dbtn_Update= view.findViewById(R.id.Dbtn_Update);

          D_UPName.setText(model.getName());

          Dbtn_Update.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {


                  String  t_UPName = D_UPName.getText().toString().trim();
                  if (t_UPName.isEmpty()) {
                      D_UPName.setError("Field can't be empty");
                  }
                  else
                  {
                  Map<String,Object> updatedData = new HashMap<>();
                  updatedData.put("name",t_UPName);

                  FirebaseDatabase.getInstance().getReference()
                          .child("Uploads")
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

        holder.D_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.ImDocuments.getContext());
                builder.setTitle("Delete Item ");
                builder.setMessage("Delete...?");

                builder.setPositiveButton(" Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Uploads")
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
    public DocumentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.documentsrecycleresoursefile, parent, false);

        return new DocumentsViewHolder(view);
    }

    public class DocumentsViewHolder extends RecyclerView.ViewHolder {
        TextView TvName;
        ImageView ImDocuments;
        ImageView D_edit;
        ImageView D_Del;
        public DocumentsViewHolder(@NonNull View itemView) {
            super(itemView);

             TvName = itemView.findViewById(R.id.D_Fname);
             ImDocuments =  itemView.findViewById(R.id.D_Rimgshow);
            D_edit =  itemView.findViewById(R.id.D_edit);
            D_Del =  itemView.findViewById(R.id.D_Del);


        }
    }


}
