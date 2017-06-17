package com.alphabgammainc.nestfinder.GoogleService.Caching;

import java.util.HashMap;
import java.util.List;

/**
 * Created by davidhuang on 2017-06-12.
 */

public interface FindLocationCallBack {
    void locationResult(List<HashMap<String, String>> places);
}
