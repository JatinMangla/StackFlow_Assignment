package com.example.assignment_try;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private List<Question> questions;
    private Context context;

    public QuestionAdapter(List<Question> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item, parent, false);
        return new QuestionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        final Question temp = questions.get(position);
        //holder.positionNumber.setText("Question number : " + String.valueOf(position + 1));
        holder.questionTitle.setText("Question : " + questions.get(position).getTitle());
        //holder.link.setText("Link : " + questions.get(position).getLink());
        holder.positionNumber.setText("Name : "+questions.get(position).getOwner().getDisplay_name());
        holder.link.setText("score : "+questions.get(position).getScore());
        //holder.creationDate.setText("date : " +questions.get(position).getCreation_date());
        Glide.with(holder.image.getContext()).load(questions.get(position).getOwner().profile_image).into(holder.image);

        holder.questionTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,web.class);
                intent.putExtra("service",temp.getLink());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();

    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView positionNumber;
        TextView questionTitle;
        TextView link, creationDate;
        ImageView image;


        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            positionNumber = itemView.findViewById(R.id.name);
            questionTitle = itemView.findViewById(R.id.title);
            link = itemView.findViewById(R.id.extra1);
            image =itemView.findViewById(R.id.image);
            creationDate = itemView.findViewById(R.id.date);


        }
    }
}
