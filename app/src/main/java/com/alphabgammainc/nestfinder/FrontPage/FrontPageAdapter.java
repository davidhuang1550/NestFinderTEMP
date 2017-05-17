package com.alphabgammainc.nestfinder.FrontPage;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alphabgammainc.nestfinder.Classes.Locations;
import com.alphabgammainc.nestfinder.R;
import com.alphabgammainc.nestfinder.Utilities.ImageManager;

import java.util.ArrayList;

/**
 * Created by davidhuang on 2017-04-25.
 */

public class FrontPageAdapter  extends RecyclerView.Adapter<FrontPageAdapter.ViewHolder> implements View.OnClickListener {
    private Activity mActivity;
    private ArrayList<Locations> locationses;
    private ManageMap callback;
    private RecyclerView WrapperView;

    /**
     *
     * @param activity
     * @param locationses
     */
    public FrontPageAdapter(Activity activity, ArrayList<Locations> locationses, ManageMap callback, RecyclerView recyclerView ){
        this.mActivity = activity;
        this.locationses =locationses;
        this.callback = callback;
        this.WrapperView = recyclerView;
    }

    @Override
    public FrontPageAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.front_page_listview_item, parent, false);

        ViewHolder holder = new ViewHolder(v);

        v.setOnClickListener(this);

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

    /**
     * if we want to have onclick to transition to a details page then comment out the callback.
     * @param v
     */
    @Override
    public void onClick(View v) {
        int itemPosition = WrapperView.getChildLayoutPosition(v);
        callback.setMarkerFocusCallback(locationses.get(itemPosition));

        /**
         * comment out above if we want to transition this to a details page and replace the new Fragment argument with an actual
         * fragment with the details page.
         */


        //  FragmentTransaction transaction= mActivity.getFragmentManager().beginTransaction();
        //  transaction.setCustomAnimations(R.animator.enter_anim,R.animator.exit_anim,R.animator.enter_anim_back,R.animator.exit_anim_back);
        //  transaction.add(R.id.content_frame,new Fragment()).addToBackStack("Posts").commit();
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
