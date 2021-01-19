package com.example.recyclerviewtest1_3;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface OnItemClickListener {
    public void onItemClick(PlaceAdapter.CustomViewHolder holder, View view, int position);
    public void onItemLongClick(PlaceAdapter.CustomViewHolder holder, View view, int position);
}
