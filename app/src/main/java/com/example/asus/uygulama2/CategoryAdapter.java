package com.example.asus.uygulama2;

/*  ESİN GEDİK
    14011501
    Mobil Programlama Dersi 2. Ödevi
*/

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView category_name;
        public CardView card_view;

        public ViewHolder(View view)
        {
            super(view);
            card_view = (CardView)view.findViewById(R.id.card_view);
            category_name = (TextView)view.findViewById(R.id.category_name);
        }
    }

    List<String> list_category;
    CustomItemClickListener listener;

    public CategoryAdapter(List<String> list_category, CustomItemClickListener listener)
    {
        this.list_category = list_category;
        this.listener = listener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card_view_layout, parent, false);
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
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.category_name.setText(list_category.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list_category.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}