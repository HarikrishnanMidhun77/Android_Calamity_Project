package com.example.nexus_max.project;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsList extends ArrayAdapter {
    private Activity context;
    private List<News> wList;
    News news;

    public NewsList(Activity context, List<News> wList) {
        super(context, R.layout.weather_layout, wList);
        this.context = context;
        this.wList = wList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem= inflater.inflate(R.layout.news_layout,null,true);

        TextView txt_place=(TextView)listViewItem.findViewById(R.id.txt_n_place);
        TextView txt_time=(TextView)listViewItem.findViewById(R.id.txt_n_time);
        TextView txt_news=(TextView)listViewItem.findViewById(R.id.txt_n_news);

        news=wList.get(position);
        txt_place.setText(news.getPlace());
        txt_time.setText(news.getTime());
        txt_news.setText(news.getNews_cont());
        return listViewItem;
    }
    }
