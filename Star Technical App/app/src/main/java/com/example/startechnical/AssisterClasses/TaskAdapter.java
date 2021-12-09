package com.example.startechnical.AssisterClasses;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
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

// Extends the adapter class with the FirebaseRecyclerAdapter
// ( module class(Task.java), TaskAdapter's ViewHolder)

public class TaskAdapter  extends  FirebaseRecyclerAdapter<Task, TaskAdapter.TaskView_holder>{


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    Context context;
    public TaskAdapter(@NonNull FirebaseRecyclerOptions<Task> options,Context context)
    {
        super(options);
        this.context = context;
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull TaskView_holder holder, int position, @NonNull Task model) {
        // Retivering the data from the database and setting it on the textboxes of Xml file.

        holder.task_id.setText(model.getTask_Id());
        holder.assign_to.setText(model.getAssignTo());
        holder.assign_date.setText(model.getAssignDate());

           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   DialogPlus dialog = DialogPlus.newDialog(context)
                           .setContentHolder(new ViewHolder(R.layout.taskitemclikeddialoge))
                         //  .setGravity(Gravity.FILL)
                          // .setMargin(50,0,50,0)
                         //  .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                           .create();
                   dialog.show();
                   View view  = dialog.getHolderView();
                   TextView show_task_id,show_AssTo,show_date,show_time,show_bargain,show_Issue,show_Cno,show_ClientName,show_CAdress,show_CphNo,show_type, show_feedback;
                   Button btn_dismiss;
                   show_task_id = view.findViewById(R.id.showTask_id);
                   show_AssTo = view.findViewById(R.id.showAss_To);
                   show_date = view.findViewById(R.id.show_Assign_date);
                   show_time = view.findViewById(R.id.show_Assi_Time);
                   show_bargain = view.findViewById(R.id.show_Barging);
                   show_Issue = view.findViewById(R.id.showTask_ishu);
                   show_Cno = view.findViewById(R.id.showclient_no);
                   show_ClientName = view.findViewById(R.id.showClient_name);
                   show_CAdress = view.findViewById(R.id.show_Adress);
                   show_CphNo = view.findViewById(R.id.show_Ph_No);
                   show_type = view.findViewById(R.id.showC_Type);
                   show_feedback = view.findViewById(R.id.showFeed_Back);
                   btn_dismiss = view.findViewById(R.id.btn_cancel);

                   show_task_id.setText("Task ID\n\n"+model.getTask_Id());
                   show_AssTo.setText("Assign To\n\n"+model.getAssignTo());
                   show_date.setText("Date\n\n"+model.getAssignDate());
                   show_time.setText("Time\n\n"+model.getAssignTime());
                   show_bargain.setText("Bargain\n\n"+model.getBargain_Amount());
                   show_Issue.setText("Issue\n\n"+model.getTask_Issue());
                   show_Cno.setText("Client ID\n\n"+model.getClientId());
                   show_ClientName.setText("Client Name\n\n"+model.getClientName());
                   show_CAdress.setText("Client Address\n\n"+model.getClientAddress());
                   show_CphNo.setText("Client PH#\n\n"+model.getClient_cellno());
                   show_type.setText("Client Type\n\n"+model.getClientType());
                   show_feedback.setText("FeedBack\n\n"+model.getFeedback());

                   btn_dismiss.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.dismiss();
                       }
                   });




               }
           });
        holder.img_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPlus dialog = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.taskupdateimg))
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        // .setExpanded(true)  // This will enable the expand feature, (similar to android L share dialog)
                        .create();
                dialog.show();

                View view  = dialog.getHolderView();
                EditText TUp_task_id,TUp_AssTo,TUp_Ishu,TUp_Fbargin,Tup_Cno,Tup_ClientName,Tup_CAdress,Tup_CphNo;
                Button btn_Update;

                TUp_task_id = view.findViewById(R.id.TUp_TaskId);
                TUp_AssTo = view.findViewById(R.id.TUp_AssTo);
                TUp_Ishu = view.findViewById(R.id.TUp_Ishu);
                TUp_Fbargin = view.findViewById(R.id.TUp_Fbargin);
                Tup_Cno = view.findViewById(R.id.Tup_Cno);
                Tup_ClientName = view.findViewById(R.id.Tup_ClientName);
                Tup_CAdress = view.findViewById(R.id.Tup_CAdress);
                Tup_CphNo = view.findViewById(R.id.Tup_CphNo);
                btn_Update = view.findViewById(R.id.btn_Update);

                TUp_task_id.setText(model.getTask_Id());
                TUp_AssTo.setText(model.getAssignTo());
                TUp_Ishu.setText(model.getTask_Issue());
                TUp_Fbargin.setText(model.getBargain_Amount());
                Tup_Cno.setText(model.getClientId());
                Tup_ClientName.setText(model.getClientName());
                Tup_CAdress.setText(model.getClientAddress());
                Tup_CphNo.setText(model.getClient_cellno());


                btn_Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tsk_id = TUp_task_id.getText().toString().trim();
                        String tUp_AssTo = TUp_AssTo.getText().toString().trim();
                        String tUp_Ishu = TUp_Ishu.getText().toString().trim();
                        String tUp_Fbargin = TUp_Fbargin.getText().toString().trim();
                        String tup_Cno = Tup_Cno.getText().toString().trim();
                        String tup_ClientName = Tup_ClientName.getText().toString().trim();
                        String tup_CphNo = Tup_CphNo.getText().toString().trim();
                        String tup_CAdress = Tup_CAdress.getText().toString().trim();
                        if (tsk_id.isEmpty()) {
                            TUp_task_id.setError("Field can't be empty");
                        } else if (tUp_AssTo.isEmpty()) {
                            TUp_AssTo.setError("Field can't be empty");
                        } else if (tup_ClientName.isEmpty()) {
                            Tup_ClientName.setError("Field can't be empty");
                        } else if (tUp_Ishu.isEmpty()) {
                            TUp_Ishu.setError("Field can't be empty");
                        } else if (tup_CAdress.isEmpty()) {
                            Tup_CAdress.setError("Field can't be empty");
                        } else if (tUp_Fbargin.isEmpty()) {
                            TUp_Fbargin.setError("Field can't be empty");
                        } else if (tup_Cno.isEmpty()) {
                            Tup_Cno.setError("Field can't be empty");
                        } else if (tup_CphNo.length() != 11) {
                            Tup_CphNo.setError("Field can't be empty");
                        } else {

                            Map<String, Object> updatedData = new HashMap<>();

                            updatedData.put("TaskId", tsk_id);
                            updatedData.put("AssignTo", tUp_AssTo);
                            updatedData.put("Task_Issue", tUp_Ishu);
                            updatedData.put("Bargain_Amount", tUp_Fbargin);
                            updatedData.put("ClientId", tup_Cno);
                            updatedData.put("ClientName", tup_ClientName);
                            updatedData.put("Client_cellno", tup_CphNo);
                            updatedData.put("ClientAddress", tup_CAdress);

                            FirebaseDatabase.getInstance().getReference()
                                    .child("Task")
                                    .child((getRef(position).getKey()))
                                    .updateChildren(updatedData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                                            Toast.makeText(context, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                            dialog.dismiss();
                        }
                    }
                });


            }
        });
        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating an AlertDialogue box for delete

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img_del.getContext());
                builder.setTitle(" Delete Item ");

                // if the user press "yes" then
                builder.setPositiveButton(" yes " , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // get the reference of the node "Task" and delete the child
                        // on which position we pressed the delete button to perform deletion
                        FirebaseDatabase.getInstance().getReference()
                                .child("Task")
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
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
            holder.img_call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +model.getClient_cellno()));
                            context.startActivity(intent);

                }
            });
            holder.img_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" +model.getClientAddress()));
                    context.startActivity(intent);
                }
            });
      //  Animation side_anim, bottom_anim;
            holder.img_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    YoYo.with(Techniques.SlideInLeft).duration(700).repeat(0).playOn(holder.img_update);
                    YoYo.with(Techniques.SlideInLeft).duration(700).repeat(0).playOn(holder.img_del);
                    YoYo.with(Techniques.SlideInLeft).duration(700).repeat(0).playOn(holder.img_call);
                    YoYo.with(Techniques.SlideInLeft).duration(700).repeat(0).playOn(holder.img_address);
                    YoYo.with(Techniques.SlideInLeft).duration(700).repeat(0).playOn(holder.ImgSC_message);
                    holder.img_update.setVisibility(View.VISIBLE);
                    holder.img_del.setVisibility(View.VISIBLE);
                    holder.img_call.setVisibility(View.VISIBLE);
                    holder.img_address.setVisibility(View.VISIBLE);
                    holder.ImgSC_message.setVisibility(View.VISIBLE);
                    holder.img_plusagain.setVisibility(View.VISIBLE);
                    holder.img_plus.setVisibility(View.INVISIBLE);
                }

            });

        holder.img_plusagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.img_update.setVisibility(View.GONE);
                holder.img_del.setVisibility(View.GONE);
                holder.img_call.setVisibility(View.GONE);
                holder.img_address.setVisibility(View.GONE);
                holder.ImgSC_message.setVisibility(View.GONE);
                holder.img_plusagain.setVisibility(View.GONE);
                holder.img_plus.setVisibility(View.VISIBLE);
            }
        });
            holder.ImgSC_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.img_del.getContext());
                    builder.setTitle(" Do You want to send meseege  ");

                    builder.setPositiveButton("yes " , new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // get the reference of the node "Task" and delete the child
                            // on which position we pressed the delete button to perform deletion
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(model.getClient_cellno(), null, "HI their is Star Technical "+ model.getClientName() +"\n Thank you for using our services "+"\n For any complain please contact :  03008826610 ", null, null);
                            holder.ImgSC_message.setVisibility(View.GONE);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            });
    }
    // assigning and then returning the view to the "OnBind" method after inflating the recyclerView xml class

    @NonNull
    @Override
    public TaskView_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_viewonrecyclerview, parent, false);

        return new TaskView_holder(view);
    }

    //Todo: Step 1 (Create customize ViewHolder)

    // ManageTask.xlm + View for a sinlge Card, we create viewonrecyclerview.xml

    public  class TaskView_holder extends RecyclerView.ViewHolder
    {
        TextView task_id;
        TextView assign_to;
        TextView assign_date;
        ImageView img_update;
        ImageView img_del;
        ImageView img_call;
        ImageView img_address;
        ImageView img_plus;
        ImageView img_plusagain;
        ImageView ImgSC_message;

        public TaskView_holder(@NonNull View itemView) {
            super(itemView);
            task_id = itemView.findViewById(R.id.Taskid);
            assign_to = itemView.findViewById(R.id.Ass_To);
            assign_date = itemView.findViewById(R.id.AssignDate);
            img_update = itemView.findViewById(R.id.ImgTedit);
            img_del = itemView.findViewById(R.id.ImgT_Del);
            img_call = itemView.findViewById(R.id.ImgSC_Call);
            img_address = itemView.findViewById(R.id.ImgSC_Adress);
            img_plus = itemView.findViewById(R.id.img_plus);
            img_plusagain = itemView.findViewById(R.id.img_plusagain);
            ImgSC_message = itemView.findViewById(R.id.ImgSC_message);

            img_update.setVisibility(View.GONE);
            img_del.setVisibility(View.GONE);
            img_call.setVisibility(View.GONE);
            img_address.setVisibility(View.GONE);
            ImgSC_message.setVisibility(View.GONE);
            img_plusagain.setVisibility(View.GONE);

        }
    }



}
