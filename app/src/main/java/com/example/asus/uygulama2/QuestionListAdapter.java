package com.example.asus.uygulama2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView question_textview;
        public TextView answer_textview;
        public CardView list_card_view;

        public ViewHolder(View view)
        {
            super(view);
            list_card_view = (CardView)view.findViewById(R.id.list_card_view);
            question_textview = (TextView)view.findViewById(R.id.question_textview);
            answer_textview = (TextView)view.findViewById(R.id.answer_textview);
        }
    }

    List<String> question_list;
    String [] answer_list = new String[10];
    CustomItemClickListener listener;

    public QuestionListAdapter(List<String> list_category,CustomItemClickListener listener)
    {
        this.question_list = list_category;
        this.listener = listener;
    }

    @Override
    public QuestionListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_view_layout, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClick(v, view_holder.getPosition());
            }
        });
        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        holder.question_textview.setText(question_list.get(position));
        holder.answer_textview.setText(answer_list[position]);
    }

    @Override
    public int getItemCount()
    {
        return question_list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}