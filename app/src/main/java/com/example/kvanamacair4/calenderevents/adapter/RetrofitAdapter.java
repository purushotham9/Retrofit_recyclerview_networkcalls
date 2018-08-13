package com.example.kvanamacair4.calenderevents.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kvanamacair4.calenderevents.R;
import com.example.kvanamacair4.calenderevents.model.DataData;

import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.Viewer>{
    private List<DataData> eventsList;

    @Override
    public Viewer onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_row, parent, false);
        return new Viewer(itemView);
    }

    @Override
    public void onBindViewHolder(Viewer holder, int position) {
        DataData dataData = eventsList.get(position);
        holder.mtv_holidayEvent.setText(dataData.getHolidayEventType());
        holder.mtv_title.setText(dataData.getTitle());
        holder.mtv_name.setText(dataData.getName());
    }

    public RetrofitAdapter(List<DataData> eventsList) {
        this.eventsList = eventsList;
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class Viewer extends RecyclerView.ViewHolder {
        public TextView mtv_holidayEvent, mtv_title, mtv_name;
        public Viewer(View itemView) {
            super(itemView);
            mtv_holidayEvent = itemView.findViewById(R.id.tv_holiday);
            mtv_title = itemView.findViewById(R.id.tv_title);
            mtv_name = itemView.findViewById(R.id.tv_name);

        }
    }
}