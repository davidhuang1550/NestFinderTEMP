package com.alphabgammainc.nestfinder.Utilities;

import android.net.Uri;

/**
 * Created by davidhuang on 2017-04-26.
 */

public interface ImageCallBack {

    /**
     * implement this when you want to upload an image and have a call back when its ready.
     * @param downloadUrl
     */
    void ImageOnReady(Uri downloadUrl);
}
