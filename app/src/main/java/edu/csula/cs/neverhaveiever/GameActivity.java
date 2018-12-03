package edu.csula.cs.neverhaveiever;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.csula.cs.neverhaveiever.models.Question;

public class GameActivity extends AppCompatActivity {

    DatabaseReference db;
    FloatingActionButton actionButton;
    TextView g_game_lobby_name;
    EditText questionInput;

    final String MY_PREFS_NAME = "GAME";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        g_game_lobby_name = findViewById(R.id.g_l_n);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String game_name = prefs.getString("game_name", null);
        if (game_name != null) {
            g_game_lobby_name.setText(game_name);
        }
        actionButton = findViewById(R.id.game_ask_question);
        db = FirebaseDatabase.getInstance().getReference();

        actionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onCreateDialog(savedInstanceState).show();
            }
        });

        //        onCreateDialog(savedInstanceState).show();

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final EditText edditext = new EditText(this);

        builder.setTitle("Enter your statement.");
        builder.setMessage("Never Have I Ever");
        builder.setView(edditext);
        builder.setPositiveButton("Never Have I Ever", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences prefs_user = getSharedPreferences("USER", MODE_PRIVATE);
                String user_key = prefs_user.getString("user_key", null);
                SharedPreferences prefs_game = getSharedPreferences("GAME", MODE_PRIVATE);
                String gameId  = prefs_game.getString("game_key", null);
                String key = db.push().getKey();

                String statement = edditext.getText().toString();

                Question question = new Question(key, statement, gameId, user_key);

                db.child("questions").child(key).setValue(question);
            }
        });

        return builder.create();
    }
}
