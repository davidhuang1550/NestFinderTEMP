package com.alphabgammainc.nestfinder.Landlord;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.alphabgammainc.nestfinder.R;
import com.alphabgammainc.nestfinder.Utilities.ConfirmationDialog;

import java.util.ArrayList;


/**
 * Created by soutrikbarua on 2017-05-17.
 */

public class GridElementAdapter extends RecyclerView.Adapter<GridElementAdapter.SimpleViewHolder>{

    private Activity mActivity;
    private ArrayList<Bitmap> imageList;

    public GridElementAdapter(Activity mActivity,ArrayList<Bitmap> imageList){
        this.mActivity = mActivity;
        this.imageList=imageList;
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final ImageView myImage;

        public SimpleViewHolder(View view) {
            super(view);
            myImage=(ImageView)view.findViewById(R.id.Img);
            myImage.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Bundle bundle = new Bundle();
                    ConfirmationDialog confirmationDialog = new ConfirmationDialog();
                    confirmationDialog.setArguments(bundle);
                    //confirmationDialog.show(.getFragmentManager(),"Alert Dialog Fragment");

                    Toast.makeText(v.getContext(),"Delete",Toast.LENGTH_LONG).show();
                    return true;
                }
            });

        }
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.mActivity).inflate(R.layout.scrollable_grid_elements, parent, false);

        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.myImage.setImageBitmap(imageList.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.imageList.size();
    }
}