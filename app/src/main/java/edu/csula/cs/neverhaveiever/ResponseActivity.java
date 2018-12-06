package edu.csula.cs.neverhaveiever;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.csula.cs.neverhaveiever.models.Response;

public class ResponseActivity extends AppCompatActivity {

    TextView response_question;
    Button button_have;
    Button button_never;
    TextView stats;
    final String MY_PREFS_NAME = "USER";
    String user_key ;
    String user_pic;
    String question_id;
    String access_code;
    DatabaseReference db;
    RecyclerView recyclerView;
    List<Response> responseList;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.response_activity);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);


        Intent intent = getIntent();
        stats = findViewById(R.id.stats);
        response_question = findViewById(R.id.response_question);
        button_have = findViewById(R.id.button_have);
        button_never = findViewById(R.id.button_never);

        String str = intent.getStringExtra("question");
        String[] strArray = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap).append(" ");
        }

        response_question.setText("Never Have I Ever " + builder.toString());

        String author_id = intent.getStringExtra("author_id");
        question_id = intent.getStringExtra("question_id");
        access_code = intent.getStringExtra("access_code");
        user_key = prefs.getString("user_key", null);
        user_pic = prefs.getString("user_image_url", null);
        if (user_key != null) {
            if (user_key.equals(author_id)) {
                button_never.setVisibility(View.GONE);
                button_have.setVisibility(View.GONE);
            }
        }

        db = FirebaseDatabase.getInstance().getReference();

        responseList = new ArrayList<>();

        final ResponseAdapter adapter = new ResponseAdapter(this, responseList);

        recyclerView = findViewById(R.id.response_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("responses").child(access_code).child(question_id);
        Log.d("RESPONSE_ACTIVITY", question_id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("FIRE_BASE_SUCCESS", "Count of games: "+ Long.toString(dataSnapshot.getChildrenCount()));
                responseList.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Response response = postSnapshot.getValue(Response.class);
                    responseList.add(response);
                }
                Log.d("RESPONSE_ACTIVITY", Integer.toString(responseList.size()));

                adapter.setResponseList(responseList);
                stats();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FIRE_BASE_ERROR", "Failed to read value.", error.toException());
            }
        });

    }
    @SuppressLint("SetTextI18n")
    public void stats() {
        int has_done = 0;
        int never_done = 0;

        for (int i = 0; i < responseList.size(); i++) {
            if (responseList.get(i).isResponse()) {
                never_done++;
            } else if (!responseList.get(i).isResponse()) {
                has_done++;
            }
        }

        if(responseList.size() == 0){
            stats.setText("No one has answered yet!");
        }
        else if(has_done == 0){
            stats.setText("No one in this room has done it!");
        }
        else if (never_done == 0){
            stats.setText("Everyone in this room has done it!");
        }
        else if (never_done > has_done) {
            stats.setText("In this room " + has_done + " of " + Integer.toString(responseList.size()) + " people have done it!");
        }
        else if (has_done > never_done){
            stats.setText("In this room " + never_done + " of " + Integer.toString(responseList.size()) + " people have never done it!");
        }
        else if (has_done == never_done){
            stats.setText("The room is divided equally!");
        }
        else {
            stats.setText("Something broke");
        }

    }
    public void have(View view) {
        Response response = new Response(false, question_id, user_pic, user_key);
        db.child("responses").child(access_code).child(question_id).child(question_id + "-" +user_key).setValue(response);
    }
    public void never(View view) {
        Response response = new Response(true, question_id, user_pic, user_key);
        db.child("responses").child(access_code).child(question_id).child(question_id + "-" +user_key).setValue(response);
    }


}
