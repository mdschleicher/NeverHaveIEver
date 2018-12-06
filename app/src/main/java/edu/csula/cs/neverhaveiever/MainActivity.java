package edu.csula.cs.neverhaveiever;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import edu.csula.cs.neverhaveiever.models.Game;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<Game> gameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Select Room");
        recyclerView = findViewById(R.id.game_recycler_view);

        gameList = new ArrayList<>();

        final GameAdapter adapter = new GameAdapter(this, gameList);

        final DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("games");

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                gameList.clear();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Game game = postSnapshot.getValue(Game.class);
                    gameList.add(game);
                }
                adapter.setGameList(gameList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FIRE_BASE_ERROR", "Failed to read value.", error.toException());
            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.create_Game) {
            Intent create_game = new Intent(MainActivity.this, CreateGame.class);
            startActivity(create_game);
            return true;
        }
//        else if (itemThatWasClickedId == R.id.join_Game){
//            System.out.println("JOINING GAME");
//        }
        return super.onOptionsItemSelected(item);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
