package com.example.music.Service;

import android.util.Log;

public class APIService {
    private static String base_url=DiaChiIP.ip+"music/server/";
    public static Dataservice getService(){
        Log.d("BBBB",base_url);
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);

    }
}
