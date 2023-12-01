package com.example.signin;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class uploadProfile extends AppCompatActivity {

    ImageView pImg;
    Button upload;
    ProgressBar progressBar;
    FirebaseStorage storage;
    StorageReference storageReference;
    DatabaseReference ref;
    FirebaseAuth mAuth;
    private Uri imguri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile);

//        mAuth = FirebaseAuth.getInstance();
//        ref = FirebaseDatabase.getInstance().getReference("pic");
//        storageprofile = FirebaseStorage.getInstance().getReference().child(" Profilepic");
//
//        pImg = findViewById(R.id.imageView17);
//        upload = findViewById(R.id.updateBtn);
//        progressBar = findViewById(R.id.progressBar);
////        progressBar.setVisibility(View.INVISIBLE);
//
//
//        //Firebase init
//        storage = FirebaseStorage.getInstance();
//        storageReference = storage.getReference();
//
////        upload.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                chooseImage();
////            }
////        });
////
////        pImg.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                uploadImage();
////            }
////        });
////    }
////
////    private void uploadImage() {
////        if( imguri!= null){
////            final ProgressDialog progress = new ProgressDialog(this);
////            progress.setTitle("Uploading....");
////            progress.show();
////
////            StorageReference ref= storageReference.child("images/"+ UUID.randomUUID().toString());
////            ref.putFile(imguri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
////                @Override
////                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
////                    progress.dismiss();
////                    Toast.makeText(uploadProfile.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
////                    String imageUrl = taskSnapshot.getDownloadUrl().toString();
////                    Picasso.with(getBaseContext()).load(imageUrl).into(pImg);
////                }
////            }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NonNull Exception e) {
////                    progress.dismiss();
////                    Toast.makeText(uploadProfile.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
////                }
////            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
////                @Override
////                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
////                    double progres_time = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
////                    progress.setMessage("Uploaded "+(int)progres_time+" %");
////                }
////            });
////        }
////    }
////
////    private void chooseImage() {
////        Intent intent = new Intent();
////        intent.setType("image/*");
////        intent.setAction(Intent.ACTION_GET_CONTENT);
////        startActivityForResult(intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);
////    }
////
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK &&
////                data != null && data.getData() != null)
////        {
////            imguri = data.getData();
////            try
////            {
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imguri);
////                pImg.setImageBitmap(bitmap);
////            } catch (IOException e)
////            {
////                e.printStackTrace();
////            }
////        }
////    }
//
//        pImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent galleryIntent = new Intent();
//                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, 2);
//            }
//        });
//
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (imguri != null) {
//                    uploadtofirebase(imguri);
//                } else {
//                    Toast.makeText(uploadProfile.this, "please upload a image", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
//
//            imguri = data.getData();
//            pImg.setImageURI(imguri);
//        }
//    }
//
//    private void uploadtofirebase(Uri uri) {
//        StorageReference fileRef = storageReference.child(System.currentTimeMillis() +"."+ getFileExtention(uri));
//
//        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                    @Override
//                    public void onSuccess(Uri uri) {
//
//                       Model model=new Model(uri.toString());
//                        String modelId = ref.push().getKey();
//                        ref.child(modelId).setValue(model);
//                        Toast.makeText(uploadProfile.this,"upload Succefuly",Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(uploadProfile.this,"uploading.. failed",Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//    private  String getFileExtention(Uri mUri){
//        ContentResolver cr = getContentResolver();
//
//        MimeTypeMap mime =MimeTypeMap.getSingleton();
//        return mime.getExtensionFromMimeType(cr.getType(mUri));
//    }
}}
