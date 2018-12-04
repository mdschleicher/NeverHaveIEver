package edu.csula.cs.neverhaveiever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import edu.csula.cs.neverhaveiever.models.Game;
import edu.csula.cs.neverhaveiever.models.User;

public class CreateGame extends AppCompatActivity {

    DatabaseReference db;
    TextView name_of_game;
    TextView access_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_game);

        name_of_game  = findViewById(R.id.name_of_game);
        access_code = findViewById(R.id.access_code);

        db = FirebaseDatabase.getInstance().getReference();

    }

    public void onSignUp(View view) {

        Game game = new Game(name_of_game.getText().toString(), access_code.getText().toString());
        db.child("games").child(access_code.getText().toString()).setValue(game);

    }
}
