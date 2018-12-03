package edu.csula.cs.neverhaveiever;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.csula.cs.neverhaveiever.models.Game;

public class ResponseActivity extends AppCompatActivity {

    TextView response_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response_activity);

        Intent intent = getIntent();
        response_question = findViewById(R.id.response_question);
        response_question.setText("Never Have I Ever " + intent.getStringExtra("question"));

    }


}
