package edu.csula.cs.neverhaveiever;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import edu.csula.cs.neverhaveiever.models.Game;
import edu.csula.cs.neverhaveiever.models.Question;

public class GameActivity extends AppCompatActivity {

    DatabaseReference db;
    FloatingActionButton actionButton;
    TextView g_game_lobby_name;
    EditText questionInput;
    String game_key;

    final String MY_PREFS_NAME = "GAME";

    private List<Question> questionList;
    private RecyclerView recyclerView;
    private String gameId;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        questionList = new ArrayList<Question>();

        //g_game_lobby_name = findViewById(R.id.g_l_n);


        Intent intent = getIntent();

        String game_name = intent.getStringExtra("game_name");
        gameId = intent.getStringExtra("game_key");

        if (game_name != null) {
            setTitle(game_name.toUpperCase());
            //    g_game_lobby_name.setText(game_name);
        }
        actionButton = findViewById(R.id.game_ask_question);
        db = FirebaseDatabase.getInstance().getReference();

        actionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onCreateDialog(savedInstanceState).show();
            }
        });

        final QuestionAdapter adapter = new QuestionAdapter(this, questionList);


        recyclerView = findViewById(R.id.question_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("questions").child(gameId);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("FIRE_BASE_SUCCESS", "Count of games: "+ Long.toString(dataSnapshot.getChildrenCount()));
                questionList.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Question question = postSnapshot.getValue(Question.class);
                    questionList.add(question);
                }

                adapter.setQuestionList(questionList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FIRE_BASE_ERROR", "Failed to read value.", error.toException());
            }
        });



    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText edditext = new EditText(this);

        builder.setTitle("Enter your statement.");
        builder.setMessage("Never Have I Ever");
        builder.setView(edditext);
        builder.setPositiveButton("Submit Statement", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences prefs_user = getSharedPreferences("USER", MODE_PRIVATE);
                String user_key = prefs_user.getString("user_key", null);
                String user_image_url = prefs_user.getString("user_image_url", null);
                String user_name = prefs_user.getString("user_name", null);
                SharedPreferences prefs_game = getSharedPreferences("GAME", MODE_PRIVATE);

                String key = db.push().getKey();

                String statement = edditext.getText().toString();

                Question question = new Question(key, statement, gameId, user_key, user_name, user_image_url);

                db.child("questions").child(gameId).child(key).setValue(question);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null).show();
        return builder.create();
    }
}
