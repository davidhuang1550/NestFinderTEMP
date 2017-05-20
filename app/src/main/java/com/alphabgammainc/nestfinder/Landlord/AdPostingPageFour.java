package com.alphabgammainc.nestfinder.Landlord;

import android.Manifest;
import android.app.Activity;

import android.content.ClipData;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.alphabgammainc.nestfinder.R;
import com.alphabgammainc.nestfinder.Utilities.PermissionsManager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by soutrikbarua on 2017-05-16.
 */

public class AdPostingPageFour extends Fragment implements Pages,View.OnClickListener,PostingCallback{

    private View mView;
    private Activity mActivity;
    private ImageView myCamera;
    private ImageView myImage;
    private ImageView myUpload;
    private ImageView mImageView;
    static final int REQUEST_IMAGE_CAPTURE =1;
    static final int REQUEST_IMAGE_GALLERY =2;
    private HorizontalGridView horizontalGridView;
    private String mCurrentPhotoPath;
    private ArrayList<Bitmap> mImage;
    private GridElementAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.ad_posting_page_four, container ,false);
        myCamera=(ImageView)mView.findViewById(R.id.ivCamera);
        myImage=(ImageView)mView.findViewById(R.id.ivGallery);
        myUpload=(ImageView)mView.findViewById(R.id.upload);
        mImage = new ArrayList<Bitmap>();
        /**
         * @myCamera : this part lets the user access their device camera
         */
        myCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (myCameraIntent.resolveActivity(mActivity.getPackageManager()) != null) {

                    if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(mActivity,
                                new String[]{Manifest.permission.CAMERA},
                                0);
                    }
                    else{
                    startActivityForResult(myCameraIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            }
        });
        /**
         * @myImage : this part lets the user access the devices gallery
         */
        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PermissionsManager.checkReadExternalPermission(mActivity)){
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
                }
                else{
                    PermissionsManager.requestForSpecificPermission(mActivity);
                }

            }
        });

        horizontalGridView = (HorizontalGridView) mView.findViewById(R.id.gridView);
        mAdapter = new GridElementAdapter(mActivity,mImage);
        horizontalGridView.setAdapter(mAdapter);
        return mView;
    }

    /**
     * This part saves the image taken by the camera to an image view
     * @// TODO: 2017-05-16 - Make an image view to display the picture taken by camera
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImage.add(imageBitmap);
            mAdapter.notifyDataSetChanged();
        }

        if(requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK) {
            /**
             * For a single Image selection from gallery
             */
            if(data.getClipData() != null)
            {
                for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                    Uri targetUri = data.getClipData().getItemAt(i).getUri();
                    setImage(targetUri);
                }
            }
            else{
                /**
                 * For multiple image slection from gallery
                 */
                Uri targetUri = data.getData();
                setImage(targetUri);
            }

        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.alphabgammainc.nestfinder.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mActivity.sendBroadcast(mediaScanIntent);
    }
    @Override
    public void onClick(View v) {

    }
    @Override
    public void switchViews(Bitmap Identifier) {

    }
    /**
     *
     * @param targetUri:method that selects images from the gallery.
     */
    public void setImage(Uri targetUri){
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(targetUri));
            mImage.add(bitmap);
            mAdapter.notifyDataSetChanged();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
