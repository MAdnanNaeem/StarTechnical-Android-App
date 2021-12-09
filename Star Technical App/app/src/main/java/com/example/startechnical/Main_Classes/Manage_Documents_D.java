package com.example.startechnical.Main_Classes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.startechnical.AssisterClasses.UploadsofDocuments;
import com.example.startechnical.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

public class Manage_Documents_D extends AppCompatActivity {
   private final static int Imag_PicResult_Code = 1;
    Button ChoosePic;
    Button upload;
    Button Rview;
    ImageView imgAdd;
    EditText PicName;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    Uri filepath;

    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manag_documents);
        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");
        Init();
   ChoosePic.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v)

       {
           PickImaggalarry();
       }
   });

   upload.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v)
       {
           Uploads();
       }
   });
        Rview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manage_Documents_D.this,DocumentsRecycleShow.class);
                startActivity(intent);
            }
        });
    }


    private void PickImaggalarry() {
        Dexter.withActivity(Manage_Documents_D.this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        Intent intent = new Intent(Intent.ACTION_PICK );
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"Please Select Image "),1);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(Manage_Documents_D.this, "No"+response, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1 && resultCode == RESULT_OK
          &&data != null  && data.getData() != null)
        {
            filepath = data.getData();
            try
            {
                Picasso.with(this).load(filepath).into(imgAdd);
//                InputStream inputStream = getContentResolver().openInputStream(filepath);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                imgAdd.setImageBitmap(bitmap);
            }
            catch (Exception exception)
            {
                Toast.makeText(this, "Error"+exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getExtention(Uri uri) {
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cR.getType(uri));

    }
    private void Uploads()
    {

        String pictext = PicName.getText().toString().trim();
        if(pictext.isEmpty())
        {
            PicName.setError(" Cannot be Empty ");
        }
        else   if(imgAdd != null)
        {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle(" Uploading... ");
        dialog.show();
       // FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storageReference.child(System.currentTimeMillis()
                +"."+getExtention(filepath));

            uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                        }
                    },500);
                    Toast.makeText(Manage_Documents_D.this, "Upload successful!", Toast.LENGTH_LONG).show();
                    upload.setVisibility(View.VISIBLE);

                    uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            dialog.dismiss();
                            String url = uri.toString();
                            UploadsofDocuments upload = new UploadsofDocuments(pictext, url);
                            String uploadId = databaseReference.push().getKey();
                           databaseReference.child(uploadId).setValue(upload);
                            PicName.setText("");

                        }
                    });

                }
            })
             .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot)
            {
                upload.setVisibility(View.GONE);
                float percent  = (100 * snapshot.getBytesTransferred())/snapshot.getTotalByteCount();

                dialog.setMessage("Uploaded : " + (int)percent+" %");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Manage_Documents_D.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });}
        else 
        {
            Toast.makeText(this, "No File selected ", Toast.LENGTH_SHORT).show();
        }
    }

    private void Init()
    {
        ChoosePic = findViewById(R.id.ChoosePic);
        upload = findViewById(R.id.upload);
        Rview = findViewById(R.id.Rview);
        imgAdd = findViewById(R.id.imgAdd);
        PicName = findViewById(R.id.PicName);

    }
}