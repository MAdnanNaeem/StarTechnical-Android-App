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

public class FinanceAdapter  extends FirebaseRecyclerAdapter<Finance, FinanceAdapter.FinanceViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public FinanceAdapter(@NonNull FirebaseRecyclerOptions<Finance> options,Context context) {
        super(options);
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull FinanceViewHolder holder, int position, @NonNull Finance model) {

        holder.tvtask.setText(" Task No"+"                     "+model.getTaskNo());
        holder.tvfoodexp.setText(" Food Exp"+"                   "+model.getFoodExpence());
       holder.tvpetrollexp.setText(" Petrol Exp"+"                  "+model.getPetRollExpence());
       holder.tvacessories.setText(" Accessories Exp"+"      "+model.getAcessoriesExpence());
       holder.tvtotalamount.setText(" Total Exp"+"                   "+model.getTotalAmount());
       holder.tvtotalprofit.setText(" Task Profit"+"               "+model.getTaskProfit());

        holder.ImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.financeupdate))
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                       // .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                dialog.show();
                View view  = dialog.getHolderView();
                EditText FUp_Food,Fup_Pet,Fup_Acessor,Fup_Total,Fup_TaskNo;
                Button btn_Update;
                FUp_Food = view.findViewById(R.id.FUp_Food);
                Fup_Pet = view.findViewById(R.id.Fup_Pet);
                Fup_Acessor = view.findViewById(R.id.Fup_Acessor);
                Fup_Total = view.findViewById(R.id.Fup_Total);
                Fup_TaskNo = view.findViewById(R.id.Fup_TaskNo);
                btn_Update = view.findViewById(R.id.btn_Update);

                FUp_Food.setText(model.getFoodExpence());
                Fup_Pet.setText(model.getPetRollExpence());
                Fup_Acessor.setText(model.getAcessoriesExpence());
                Fup_Total.setText(model.getTotalAmount());
                Fup_TaskNo.setText(model.getTaskNo());



                btn_Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String fooup = FUp_Food.getText().toString().trim();
                        String petrolup = Fup_Pet.getText().toString().trim();
                        String acesoriesup = Fup_Acessor.getText().toString().trim();
                        String totalamountup = Fup_Total.getText().toString().trim();
                        String tasknop = Fup_TaskNo.getText().toString().trim();


                        if (fooup.isEmpty()) {
                            FUp_Food.setError("Field can't be empty");
                        }
                        else if (petrolup.isEmpty())
                        {
                            Fup_Pet.setError("Field can't be empty");
                        }
                        else if (acesoriesup.isEmpty())
                        {
                            Fup_Acessor.setError("Field can't be empty");
                        }
                        else if (totalamountup.isEmpty())
                        {
                            Fup_Total.setError("Field can't be empty");
                        }
                        else if (tasknop.isEmpty())
                        {
                            Fup_TaskNo.setError("Field can't be empty");
                        }

                        else
                        {
                        String  result;
                        try{
                            int t_amount = Integer.parseInt(totalamountup);
                            int value = Integer.parseInt(fooup)+Integer.parseInt(petrolup)+Integer.parseInt(acesoriesup);
                            int  tvalue  = t_amount - value;
                            result = String.valueOf(tvalue);
                        }catch(NumberFormatException ex){
                            //either a or b is not a number
                            result = "Invalid input";
                        }
                        Map<String,Object> updatedData = new HashMap<>();
                        updatedData.put("FoodExpence",fooup);
                        updatedData.put("PetRollExpence",petrolup);
                        updatedData.put("AcessoriesExpence",acesoriesup);
                        updatedData.put("TotalAmount",totalamountup);
                        updatedData.put("TaskProfit",result);
                        updatedData.put("TaskNo",tasknop);

                        FirebaseDatabase.getInstance().getReference()
                                .child("Finance")
                                .child((getRef(position).getKey()))
                                .updateChildren(updatedData)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(context,"Updated Successfully",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                dialog.dismiss();
                    }

                    }
                });

            }
        });
        holder.ImgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.ImgDel.getContext());
                builder.setTitle("Delete Item ");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference()
                                .child("Finance")
                                .child((getRef(position).getKey()))
                                .removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(context,"Deleted Successfully",Toast.LENGTH_SHORT).show();
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
    public FinanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.financerecyclecustonview, parent, false);

        return new FinanceViewHolder(view);
    }

    public  class  FinanceViewHolder extends RecyclerView.ViewHolder {
        TextView tvfoodexp;
        TextView tvpetrollexp;
        TextView tvacessories;
        TextView tvtotalamount;
        TextView tvtotalprofit;
        TextView tvtask;
        ImageView ImgEdit;
        ImageView ImgDel;
        public FinanceViewHolder(@NonNull View itemView)
        {
            super(itemView);
           tvfoodexp = itemView.findViewById(R.id.TvF_Food);
           tvpetrollexp = itemView.findViewById(R.id.TvF_petroll);
           tvacessories = itemView.findViewById(R.id.TvF_Acessories);
           tvtotalamount = itemView.findViewById(R.id.TvF_TotalAmount);
           tvtotalprofit = itemView.findViewById(R.id.TvF_TotalProfit);
           tvtask = itemView.findViewById(R.id.TvF_TaskNo);
          ImgEdit = itemView.findViewById(R.id.ImgSCedit);
          ImgDel = itemView.findViewById(R.id.ImgSC_Del);
        }

    }

}
