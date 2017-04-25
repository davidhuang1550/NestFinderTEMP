package com.alphabgammainc.nestfinder.FrontPage;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphabgammainc.nestfinder.R;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class FrontPageAdapter  extends RecyclerView.Adapter<FrontPageAdapter.ViewHolder>  {
    Activity activity;
    String[] description;

    /**
     *
     * @param activity
     * @param desc
     */
    public FrontPageAdapter(Activity activity, String[] desc){
        this.activity = activity;
        this.description =desc;
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
        holder.desc.setText(description[position]);
        holder.image.setImageResource(R.drawable.messagerec);

    }

    @Override
    public int getItemCount() {
        return description.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView desc;
        ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            desc = (TextView)itemView.findViewById(R.id.desc);
            image = (ImageView)itemView.findViewById(R.id.image);
        }

    }

}
