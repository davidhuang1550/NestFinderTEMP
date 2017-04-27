package com.alphabgammainc.nestfinder.FrontPage;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphabgammainc.nestfinder.Classes.Locations;
import com.alphabgammainc.nestfinder.R;
import com.alphabgammainc.nestfinder.Ulilities.ImageManager;

import java.util.ArrayList;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class FrontPageAdapter  extends RecyclerView.Adapter<FrontPageAdapter.ViewHolder>  {
    private Activity mActivity;
    private ArrayList<Locations> locationses;

    /**
     *
     * @param activity
     * @param locationses
     */
    public FrontPageAdapter(Activity activity, ArrayList<Locations> locationses){
        this.mActivity = activity;
        this.locationses =locationses;
    }

    @Override
    public FrontPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.front_page_listview_item, parent, false);

        // we will set the listener here to imflate a new fragment.
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(FrontPageAdapter.ViewHolder holder, int position) {

        ImageManager.downloadImage(mActivity,holder.image, locationses.get(position).getRentImage());
        holder.address.setText(this.locationses.get(position).getAddress());
        holder.price.setText(Double.toString((this.locationses.get(position).getPrice())));

    }

    @Override
    public int getItemCount() {
        return locationses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        TextView price;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            address = (TextView)itemView.findViewById(R.id.location);
            price = (TextView)itemView.findViewById(R.id.price);
            image = (ImageView)itemView.findViewById(R.id.image);
        }

    }

}
