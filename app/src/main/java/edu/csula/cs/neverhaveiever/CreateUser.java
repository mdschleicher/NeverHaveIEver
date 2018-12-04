package edu.csula.cs.neverhaveiever;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import edu.csula.cs.neverhaveiever.models.User;

public class CreateUser extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    DatabaseReference db;
    private EditText name;
    private Bitmap bitmap;
    private ImageView imageView;
    private Uri downloadUri;
    final String MY_PREFS_NAME = "USER";

    //creating reference to firebase storage
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // code for shared preferences.
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("user_key", null);
        if (restoredText != null) {
            Intent completed_Profile = new Intent(CreateUser.this, MainActivity.class);
            Log.d("USER_KEY", restoredText);
            startActivity(completed_Profile);
            finish();
        }



        setContentView(R.layout.sign_in);


        name  = findViewById(R.id.friendName);
        imageView = findViewById(R.id.mImageView);

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String UserName = bundle.getString("User Name");
            bitmap = intent.getParcelableExtra("BitmapImage");
            imageView = findViewById(R.id.mImageView);
            name = findViewById(R.id.friendName);
            setValues(UserName, bitmap);
        }

        db = FirebaseDatabase.getInstance().getReference();

    }

    public void setValues(String name, Bitmap picture){
        imageView.setImageBitmap(picture);
        this.name.setText(name);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        }
    }

    public void dispatchTakePicture(View view){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void allDone(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you ready to continue?")
                .setIcon(R.drawable.ic_warning_black_24dp)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] data = baos.toByteArray();

                        String path = "icons/" + UUID.randomUUID() + ".png";
                        final StorageReference iconRef = storageRef.child(path);
                        UploadTask uploadTask = iconRef.putBytes(data);

                        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                // Continue with the task to get the download URL
                                return iconRef.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Intent completed_Profile = new Intent(CreateUser.this, MainActivity.class);
                                    downloadUri = task.getResult();

                                    String key = db.push().getKey();
                                    User user = new User(key, name.getText().toString(), downloadUri.toString());
                                    db.child("users").child(key).setValue(user);

                                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                    editor.putString("user_key", key);
                                    editor.putString("user_image_url", downloadUri.toString());
                                    editor.putString("user_name", name.getText().toString());
                                    editor.apply();

                                    startActivity(completed_Profile);
                                    finish();
                                } else {

                                    // Handle failures
                                    // ...
                                }
                            }
                        });
                    }
                }).setNegativeButton(android.R.string.no, null).show();

    }


}
