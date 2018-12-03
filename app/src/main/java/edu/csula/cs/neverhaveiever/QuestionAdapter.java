package edu.csula.cs.neverhaveiever;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Question;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.GameViewHolder>{

    private final LayoutInflater mInflater;

    private List<Question> questionList;



    String MY_PREFS_NAME = "GAME";

    // constructor
    QuestionAdapter(Context context, List<Question> questionList) {
        this.questionList = questionList;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.ask_question, parent, false);

        return new GameViewHolder(itemView);
    }

    void setQuestionList(List<Question> QuestionList) {
        questionList = QuestionList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (questionList != null)
            return questionList.size();
        return  0;
    }


    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {

        Question currentNewsItem = questionList.get(position);

        holder.name.setText(currentNewsItem.getName());
        holder.question.setText("Never Have I Ever "+ currentNewsItem.getQuestion());
        String imageUrl = currentNewsItem.getProfilePic();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(holder.host_profile_picture);
        }

    }



    class GameViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView name;
        private TextView question;
        private ImageView host_profile_picture;

        public GameViewHolder(View itemView) {
            super(itemView);
            // populating our holder to content of the layout.
            host_profile_picture =  itemView.findViewById(R.id.question_person);
            name = itemView.findViewById(R.id.question_name);
            question = itemView.findViewById(R.id.question_question);

            // sets the on click listener for item.
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

//            Intent completed_Profile = new Intent(activity , GameActivity.class);
//
//
//            String joinCode = gameList.get(getAdapterPosition()).getJoinCode();
//            String name = gameList.get(getAdapterPosition()).getName();
//            // Define a new intent to browse the url of the specific item
//            // SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//
//            editor.putString("game_key", joinCode);
//            editor.putString("game_name", name);
//            editor.apply();
//
//            activity.startActivity(completed_Profile);
        }

    }
}
