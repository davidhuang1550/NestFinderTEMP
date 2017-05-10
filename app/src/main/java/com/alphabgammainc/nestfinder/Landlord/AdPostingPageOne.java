package com.alphabgammainc.nestfinder.Landlord;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.alphabgammainc.nestfinder.R;

/**
 * Created by davidhuang on 2017-05-09.
 */

public class AdPostingPageOne extends Fragment implements View.OnClickListener, Pages {

    private View mView;
    private Activity mActivity;
    private EditText adTitle;
    private EditText adDescription;
    private EditText adPrice;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.ad_posting_page_one, container, false);

        adTitle = (EditText) mView.findViewById(R.id.adTitle);
        adDescription = (EditText) mView.findViewById(R.id.adDescription);
        adPrice = (EditText) mView.findViewById(R.id.adPrice);

        mView.findViewById(R.id.nextpage).setOnClickListener(this);

        return mView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.nextpage) {
            String adTitleString = adTitle.getText().toString();
            String adDescriptionString = adDescription.getText().toString();
            String adPriceString = adPrice.getText().toString();

            if(AdPostingValidation.checkForEmptyString(adTitleString) &&
                    AdPostingValidation.checkForEmptyString(adDescriptionString)&&
                    AdPostingValidation.checkForEmptyString(adPriceString)) {

            }
            // validate, store info then call back.
            // create some error feedback

        }
    }
}
