package com.alphabgammainc.nestfinder.Utilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by soutrikbarua on 2017-05-20.
 */

public class ConfirmationDialog extends DialogFragment {
    private Activity mActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mActivity);

        final Bundle bundle =getArguments();


        if(bundle!=null){
            try {
                alertDialog.setTitle(bundle.getString("Title"));
                alertDialog.setMessage(bundle.getString("Message", "Confirmation sent to manager"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            alertDialog.setMessage("Confirmation sent to manager");
        }
        alertDialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setShowsDialog(false);
                dismiss();
            }
        });

        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               /* boolean isback = bundle.getBoolean("IsBack",true);
                if(isback==false) {
                //        ((MainActivity) mActivity).onBackPressed();
                }*/
            }
        });
        return alertDialog.create();
    }

}
