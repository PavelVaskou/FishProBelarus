package com.example.eugeney.fishprobelarus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ReservoirsAdapter extends ArrayAdapter<InformationReservoir> {
    private LayoutInflater inflater;
    private int layout;
    private List<InformationReservoir> reservoirs ;

    public ReservoirsAdapter(Context context, int resource, List<InformationReservoir> reservoirs) {
        super(context, resource, reservoirs);
        this.reservoirs = reservoirs;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        ImageView fishView = view.findViewById(R.id.image);
        TextView nameView = view.findViewById(R.id.name);

        InformationReservoir informationReservoir = reservoirs.get(position);

        int fishImage = fishView.getResources().getIdentifier(informationReservoir.getImage(),"drawable","com.example.eugeney.fishprobelarus" );
        fishView.setImageResource(fishImage);
        nameView.setText(informationReservoir.getName());

        return view;
    }
}
