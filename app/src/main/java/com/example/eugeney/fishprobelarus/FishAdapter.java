package com.example.eugeney.fishprobelarus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FishAdapter extends ArrayAdapter<InformationFish> {

    private LayoutInflater inflater;
    private int layout;
    private List<InformationFish> fishes;

    public FishAdapter(Context context, int resource, List<InformationFish> states) {
        super(context, resource, states);
        this.fishes = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView fishView = view.findViewById(R.id.fishImage);
        TextView nameView = view.findViewById(R.id.name);

        InformationFish informationFish = fishes.get(position);

        int fishImage = fishView.getResources().getIdentifier(informationFish.getImage(),"drawable","com.example.eugeney.fishprobelarus" );
        fishView.setImageResource(fishImage);
        nameView.setText(informationFish.getName());

        return view;
    }
}