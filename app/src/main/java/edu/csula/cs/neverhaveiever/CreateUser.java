package edu.csula.cs.neverhaveiever;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.csula.cs.neverhaveiever.models.User;

public class CreateUser extends AppCompatActivity {

    DatabaseReference db;
    TextView name;
    TextView email;

    final String MY_PREFS_NAME = "USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("user_key", null);
        if (restoredText != null) {
            Intent completed_Profile = new Intent(CreateUser.this, MainActivity.class);
            Log.d("USER_KEY", restoredText);
            startActivity(completed_Profile);
        }


        setContentView(R.layout.sign_in);
        name  = findViewById(R.id.name);
        email = findViewById(R.id.image);

        db = FirebaseDatabase.getInstance().getReference();

    }

    public void onSignUp(View view) {
        Intent completed_Profile = new Intent(CreateUser.this, MainActivity.class);

        String key = db.push().getKey();
        User user = new User(key, name.getText().toString(), email.getText().toString());
        db.child("users").child(key).setValue(user);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("user_key", key);

        editor.apply();


        startActivity(completed_Profile);
    }
}
