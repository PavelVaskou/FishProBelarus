package com.example.eugeney.fishprobelarus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UsefulAdapter extends ArrayAdapter<InformationUseful> {
    private LayoutInflater inflater;
    private int layout;
    private List<InformationUseful> useful ;

    public UsefulAdapter(Context context, int resource, List<InformationUseful> useful) {
        super(context, resource, useful);
        this.useful = useful;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view= inflater.inflate(this.layout, parent, false);

        ImageView fishView = view.findViewById(R.id.image);
        TextView nameView = view.findViewById(R.id.name);

        InformationUseful informationUseful = useful.get(position);

        int fishImage = fishView.getResources().getIdentifier(informationUseful.getImage(),"drawable","com.example.eugeney.fishprobelarus" );
        fishView.setImageResource(fishImage);
        nameView.setText(informationUseful.getName());

        return view;
    }
}
