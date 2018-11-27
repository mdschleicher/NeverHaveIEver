package edu.csula.cs.neverhaveiever;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    String TAG = "db";
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // sets the value of a user block with the id of 1 and the username to john.
        //mDatabase.child("users").child("1").child("username").setValue("john");

        System.out.print(mDatabase.child("users"));

    }

    // posting a user to db.
    // uses userId as a unique id.
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        // child is making a child from users.
        // also counts as ID.
        mDatabase.child("users").child(userId).setValue(user);
    }

}

@IgnoreExtraProperties
class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}
