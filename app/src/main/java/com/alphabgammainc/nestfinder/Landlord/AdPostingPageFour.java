package com.alphabgammainc.nestfinder.Landlord;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphabgammainc.nestfinder.MapsActivity;
import com.alphabgammainc.nestfinder.R;

/**
 * Created by davidhuang on 2017-05-14.
 */

public class AdPostingPageFour extends Fragment implements Pages {

    private View mView;
    private Activity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.ad_posting_page_four, container ,false);
        ((AdPostingManager)mActivity).setTitle("Upload an Image");

        ((AdPostingManager)mActivity).getSupportActionBar().show();

        return mView;
    }
}
