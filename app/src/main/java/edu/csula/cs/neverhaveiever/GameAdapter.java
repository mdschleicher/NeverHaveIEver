package edu.csula.cs.neverhaveiever;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Game;

import static android.content.Context.MODE_PRIVATE;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder>{

    private final LayoutInflater mInflater;

    private List<Game> gameList;

    private Context context;

    String MY_PREFS_NAME = "GAME";

    // constructor
    GameAdapter(Context context, List<Game> gameList) {
        this.gameList = gameList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.game_item, parent, false);

        return new GameViewHolder(itemView);
    }

    void setGameList(List<Game> GameList) {
        gameList = GameList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (gameList != null)
            return gameList.size();
        return  0;
    }


    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {

        Game currentNewsItem = gameList.get(position);
        holder.game_lobby_name.setText(currentNewsItem.getName());

    }

    class GameViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView game_lobby_name;
        private ImageView host_profile_picture;

        public GameViewHolder(View itemView) {
            super(itemView);
            // populating our holder to content of the layout.
            game_lobby_name =  itemView.findViewById(R.id.game_lobby_name);
            host_profile_picture = itemView.findViewById(R.id.host_profile_picture);

            // sets the on click listener for item.
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent completed_Profile = new Intent(context , GameActivity.class);

            String joinCode = gameList.get(getAdapterPosition()).getJoinCode();
            String name = gameList.get(getAdapterPosition()).getName();


            Log.d("GameAdapterClick", joinCode);
            completed_Profile.putExtra("game_key", joinCode);
            completed_Profile.putExtra("game_name", name);

            context.startActivity(completed_Profile);
        }


    }
}
