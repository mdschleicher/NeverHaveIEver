package edu.csula.cs.neverhaveiever;

import android.content.Context;
import android.content.Intent;
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

    private Context context;



    // constructor
    QuestionAdapter(Context context, List<Question> questionList) {
        this.questionList = questionList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.ask_question, parent, false);

        return new GameViewHolder(itemView, this.context);
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
        private Context context;


        public GameViewHolder(View itemView, Context context) {
            super(itemView);


            // populating our holder to content of the layout.
            host_profile_picture =  itemView.findViewById(R.id.question_person);
            name = itemView.findViewById(R.id.question_name);
            question = itemView.findViewById(R.id.question_question);
            this.context = context;
            // sets the on click listener for item.
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            Intent completed_Profile = new Intent(this.context , ResponseActivity.class);


            String joinCode = questionList.get(getAdapterPosition()).getAccess_code();
            String question = questionList.get(getAdapterPosition()).getQuestion();
            String author_id = questionList.get(getAdapterPosition()).getUserId();
            String question_id = questionList.get(getAdapterPosition()).getId();
            completed_Profile.putExtra("access_code", joinCode);
            completed_Profile.putExtra("question", question);
            completed_Profile.putExtra("question_id", question_id);
            completed_Profile.putExtra("author_id", author_id);

            context.startActivity(completed_Profile);
        }

    }
}
