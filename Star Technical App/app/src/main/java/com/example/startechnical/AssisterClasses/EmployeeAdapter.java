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

public class EmployeeAdapter extends FirebaseRecyclerAdapter<Employee, EmployeeAdapter.EmployeViewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public EmployeeAdapter(@NonNull FirebaseRecyclerOptions<Employee> options,Context context)
    {
        super(options);
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull EmployeViewholder holder, int position, @NonNull Employee model) {
           holder.Emp_name.setText(" Employee Name        : "+"  "+model.getEmployee_Name());
           holder.Emp_cnic.setText(" Employee CNIC         : "+"  "+model.getEmployeeCnic());
           holder.Emp_age.setText (" Employee Age           : "+"  "+model.getEmployeeAgge());
           holder.Emp_Gender.setText(" Employee Gender     : "+"  "+model.getEmployeeGender());
           holder.Emp_Adress.setText(" Employee Address   : "+"  "+model.getEmployeeAdress());
           holder.Emp_roll.setText(" Employee Role          : "+"  "+model.getEmployeeRoll());
           holder.Emp_phNo.setText(" Employee PhNo        : "+"  "+model.getEmployeePhNo());
           holder.Emp_Salary.setText(" Employee Salary       : "+"  "+model.getEmployeeSallary());


          
           holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   DialogPlus dialog = DialogPlus.newDialog(context)
                           .setContentHolder(new ViewHolder(R.layout.employeeupdateimg))
                           .setGravity(Gravity.CENTER)
                           .setMargin(50,0,50,0)
                           // .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                           .create();
                   dialog.show();

                   View view  = dialog.getHolderView();
                   EditText EUp_EmpName,EUp_EmpAge,EUp_EmpRoll,EUp_EmpSalary,EUp_EmpPhNo,EUp_EmpAdress;
                   Button btn_Update;
                   EUp_EmpName = view.findViewById(R.id.EUp_EmpName);
                   EUp_EmpAge = view.findViewById(R.id.EUp_EmpAge);
                   EUp_EmpRoll = view.findViewById(R.id.EUp_EmpRoll);
                   EUp_EmpSalary = view.findViewById(R.id.EUp_EmpSalary);
                   EUp_EmpPhNo= view.findViewById(R.id.EUp_EmpPhNo);
                   EUp_EmpAdress = view.findViewById(R.id.EUp_EmpAdress);
                   btn_Update = view.findViewById(R.id.btn_Update);

                   EUp_EmpName.setText(model.getEmployee_Name());
                   EUp_EmpAge.setText(model.getEmployeeAgge());
                   EUp_EmpRoll.setText(model.getEmployeeRoll());
                   EUp_EmpSalary.setText(model.getEmployeeSallary());
                   EUp_EmpPhNo.setText(model.getEmployeePhNo());
                   EUp_EmpAdress.setText(model.getEmployeeAdress());

                   btn_Update.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           String eUp_EmpName = EUp_EmpName.getText().toString().trim();
                           String eUp_EmpAge = EUp_EmpAge.getText().toString().trim();
                           String eUp_EmpRoll = EUp_EmpRoll.getText().toString().trim();
                           String eUp_EmpSalary = EUp_EmpSalary.getText().toString().trim();
                           String eUp_EmpPhNo = EUp_EmpPhNo.getText().toString().trim();
                           String eUp_EmpAdress = EUp_EmpAdress.getText().toString().trim();

                           if (eUp_EmpName.isEmpty()) {
                               EUp_EmpName.setError("Field can't be empty");
                           }
                           else if (eUp_EmpAge.isEmpty())
                           {
                               EUp_EmpAge.setError("Field can't be empty");
                           }
                           else if (eUp_EmpRoll.isEmpty())
                           {
                               EUp_EmpRoll.setError("Field can't be empty");
                           }
                           else if (eUp_EmpSalary.isEmpty())
                           {
                               EUp_EmpSalary.setError("Field can't be empty");
                           }
                           else if (eUp_EmpPhNo.length() != 11)
                           {
                               EUp_EmpPhNo.setError("Field can't be empty");
                           }
                           else if (eUp_EmpAdress .isEmpty())
                           {
                               EUp_EmpAdress.setError("Field can't be empty");
                           }

                          else
                           {
                           Map<String,Object> updatedData = new HashMap<>();
                           updatedData.put("Employee_Name",eUp_EmpName);
                           updatedData.put("EmployeeAgge",eUp_EmpAge);
                           updatedData.put("EmployeeRoll",eUp_EmpRoll);
                           updatedData.put("EmployeeSallary",eUp_EmpSalary);
                           updatedData.put("EmployeePhNo",eUp_EmpPhNo);
                           updatedData.put("EmployeeAdress",eUp_EmpAdress);

                           FirebaseDatabase.getInstance().getReference()
                                   .child("Employee")
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
           holder.imgDel.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   AlertDialog.Builder builder = new AlertDialog.Builder(holder.imgDel.getContext());
                   builder.setTitle("Delete Item ");
                   builder.setMessage("Delete...?");

                   builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           FirebaseDatabase.getInstance().getReference()
                                   .child("Employee")
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
        holder.imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +model.getEmployeePhNo()));
                context.startActivity(intent);

            }
        });
        holder.imgAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" +model.getEmployeeAdress()));
                context.startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public EmployeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.employerecyclresourcefile, parent, false);

        return new EmployeViewholder(view);
    }


    public class EmployeViewholder extends RecyclerView.ViewHolder {

        TextView Emp_name;
        TextView Emp_cnic;
        TextView Emp_age;
        TextView Emp_Adress;
        TextView Emp_roll;
        TextView Emp_Gender;
        TextView Emp_phNo;
        TextView Emp_Salary;
        ImageView imgUpdate;
        ImageView imgDel;
        ImageView imgAdress;
        ImageView imgCall;
        public EmployeViewholder(@NonNull View itemView) {
            super(itemView);
            Emp_name = itemView.findViewById(R.id.Emp_name);
            Emp_cnic = itemView.findViewById(R.id.Emp_cnic);
            Emp_age = itemView.findViewById(R.id.Emp_age);
            Emp_Adress = itemView.findViewById(R.id.Emp_Adress);
            Emp_roll = itemView.findViewById(R.id.Emp_roll);
            Emp_Gender = itemView.findViewById(R.id.Emp_Gender);
            Emp_phNo = itemView.findViewById(R.id.empPhon);
            Emp_Salary = itemView.findViewById(R.id.empsalary);
            imgUpdate = itemView.findViewById(R.id.imgUpdate);
            imgDel = itemView.findViewById(R.id.imgDel);
            imgCall = itemView.findViewById(R.id.imgCall);
            imgAdress = itemView.findViewById(R.id.imgAdress);


        }
    }

}
