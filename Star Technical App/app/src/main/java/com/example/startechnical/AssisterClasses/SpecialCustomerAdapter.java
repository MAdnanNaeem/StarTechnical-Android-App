package com.example.startechnical.AssisterClasses;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.example.startechnical.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class SpecialCustomerAdapter extends FirebaseRecyclerAdapter<SpecialCustomer,SpecialCustomerAdapter.SpCustomViewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public SpecialCustomerAdapter(@NonNull FirebaseRecyclerOptions<SpecialCustomer> options,Context context) {
        super(options);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull SpCustomViewholder holder, int position, @NonNull SpecialCustomer model) {
        holder.Sc_name.setText("Customer Name       :  "+"    "+ model.getCustomerName());
        holder.Sc_cnic.setText("Customer CNIC        :  "+"    "+ model.getCustomerCnic());
        holder.ScNo.setText("Customer NO            :  "+"    "+  model.getCustomerNo());
        holder.ScPhno.setText("Customer PhNo        :  "+"    "+  model.getCustomerPhNo());
        holder.ScAdress.setText("Customer Address    :  "+"    "+  model.getCustomerAdress());
        holder.ScTaskcompleat.setText("Completed Tasks      : "+"    "+  model.getTaskCompleated());




        holder.imgscUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.specialcustomerupdatefile))
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        // .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                dialog.show();

                View view  = dialog.getHolderView();
                EditText Sup_SName,SUp_SCnice,Sup_Spno,Sup_SPhno,SpUpTaskCom, Sup_SAdress;
                Button Spbtn_Update;

                Sup_SName = view.findViewById(R.id.Sup_SName);
                SUp_SCnice = view.findViewById(R.id.SUp_SCnice);
                Sup_Spno = view.findViewById(R.id.Sup_Spno);
                Sup_SPhno = view.findViewById(R.id.Sup_SPhno);
                Sup_SAdress = view.findViewById(R.id.Sup_SAdress);
                SpUpTaskCom = view.findViewById(R.id.SpUpTaskCom);
                Spbtn_Update = view.findViewById(R.id.Spbtn_Update);

                Sup_SName.setText(model.getCustomerName());
                SUp_SCnice.setText(model.getCustomerCnic());
                Sup_Spno.setText(model.getCustomerNo());
                Sup_SPhno.setText(model.getCustomerPhNo());
                Sup_SAdress.setText(model.getCustomerAdress());
                SpUpTaskCom.setText(model.getTaskCompleated());

                Spbtn_Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String sup_SName = Sup_SName.getText().toString().trim();
                        String sUp_SCnice = SUp_SCnice.getText().toString().trim();
                        String sup_Spno = Sup_Spno.getText().toString().trim();
                        String spUpTaskCom = SpUpTaskCom.getText().toString().trim();
                        String sup_SPhno = Sup_SPhno.getText().toString().trim();
                        String sup_SAdress = Sup_SAdress.getText().toString().trim();



                        if (sup_SName.isEmpty()) {
                            Sup_SName.setError("Field can't be empty");
                        }
                        else if (sUp_SCnice.length() != 13)
                        {
                            SUp_SCnice.setError("Field can't be empty");
                        }
                        else if (sup_Spno.isEmpty())
                        {
                            Sup_Spno.setError("Field can't be empty");
                        }
                        else if (spUpTaskCom.isEmpty())
                        {
                            SpUpTaskCom.setError("Field can't be empty");
                        }
                        else if (sup_SPhno.length() != 11)
                        {
                            Sup_SPhno.setError("Field can't be empty");
                        }
                        else if (sup_SAdress.isEmpty())
                        {
                            Sup_SAdress.setError("Field can't be empty");
                        }
                        else
                        {

                        Map<String,Object> updatedData = new HashMap<>();
                        updatedData.put("CustomerName",sup_SName);
                        updatedData.put("CustomerCnic",sUp_SCnice);
                        updatedData.put("CustomerNo",sup_Spno);
                        updatedData.put("TaskCompleated",spUpTaskCom);
                        updatedData.put("CustomerPhNo",sup_SPhno);
                        updatedData.put("CustomerAdress",sup_SAdress);

                        FirebaseDatabase.getInstance().getReference()
                                .child("SpecialCustomer")
                                .child((getRef(position).getKey()))
                                .updateChildren(updatedData)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                                        Toast.makeText(context,"Updated Sucessfully",Toast.LENGTH_SHORT).show();
                                    }
                                });
                        dialog.dismiss();
                    }

                    }
                });
            }
        });





        holder.imgscCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +model.getCustomerPhNo()));
                context.startActivity(intent);
            }
        });


        holder.imgScAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" +model.getCustomerAdress()));
                context.startActivity(intent);
            }
        });

        holder.spimgedel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.spimgedel.getContext());
                builder.setTitle("Delete Item ");
                builder.setMessage("Delete...?");

                builder.setPositiveButton(" Yes ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseDatabase.getInstance().getReference()
                                .child("SpecialCustomer")
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
    public SpCustomViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.special_customerrecycle_file, parent, false);
        return new SpCustomViewholder(view);
    }

    public class   SpCustomViewholder extends RecyclerView.ViewHolder {
        TextView Sc_name;
        TextView Sc_cnic;
        TextView ScNo;
        TextView ScPhno;
        TextView ScTaskcompleat;
        TextView ScAdress;
        ImageView imgscUpdate;
        ImageView spimgedel;
        ImageView imgscCall;
        ImageView imgScAdress;




        public SpCustomViewholder(@NonNull View itemView) {
            super(itemView);

         Sc_name = itemView.findViewById(R.id.Sc_name);
            Sc_cnic = itemView.findViewById(R.id.Sc_cnic);
            ScNo = itemView.findViewById(R.id.ScNo);
            ScPhno = itemView.findViewById(R.id.ScPhno);
            ScTaskcompleat = itemView.findViewById(R.id.ScTaskcompleat);
            ScAdress = itemView.findViewById(R.id.ScAdress);
            imgscUpdate = itemView.findViewById(R.id.imgscUpdate);
            spimgedel = itemView.findViewById(R.id.spimgedel);
            imgscCall = itemView.findViewById(R.id.imgscCall);
            imgScAdress= itemView.findViewById(R.id.imgScAdress);





        }
    }
}
