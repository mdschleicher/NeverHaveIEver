package edu.csula.cs.neverhaveiever;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import edu.csula.cs.neverhaveiever.models.Response;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.ResponseViewHolder>{

    private List<Response> responseList;
    private final LayoutInflater mInflater;

    ResponseAdapter(Context context, List<Response> responseList) {
        this.responseList = responseList;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public ResponseAdapter.ResponseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.response, parent, false);

        return new ResponseAdapter.ResponseViewHolder(itemView);
    }

    void setResponseList(List<Response> ResponseList) {
        responseList = ResponseList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (responseList != null)
            return responseList.size();
        return  0;
    }

    @Override
    public void onBindViewHolder(ResponseAdapter.ResponseViewHolder holder, int position) {

        Response response = responseList.get(position);
        if (!response.isResponse()) {
            holder.response_text.setText("Has!");
            holder.cardView.setCardBackgroundColor(Color.RED);
        } else {
            holder.response_text.setText("Never!");
            holder.cardView.setCardBackgroundColor(Color.GREEN);
        }

        String imageUrl = response.getUserPicture();
        if (imageUrl != null) {
            Picasso.get().load(imageUrl).into(holder.response_image);
        }
    }

    class ResponseViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView response_text;
        private ImageView response_image;
        private CardView cardView;

        public ResponseViewHolder(View itemView) {
            super(itemView);
            // populating our holder to content of the layout.
            response_text =  itemView.findViewById(R.id.response_text);
            response_image = itemView.findViewById(R.id.response_image);
            cardView = itemView.findViewById(R.id.response_card_view);
            // sets the on click listener for item.
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

        }


    }
}
