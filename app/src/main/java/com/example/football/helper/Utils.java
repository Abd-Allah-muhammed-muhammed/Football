package com.example.football.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.example.football.R;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Utils {
    private static OkHttpClient httpClient;

    public static void fetchSvg(final Context context, final String url, final ImageView target , Activity activity ) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }



                Request request = new Request.Builder().url(url).build();
                httpClient.newCall(request).enqueue(new Callback() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onFailure(Call call, IOException e) {

//                        target.setImageDrawable(context.getDrawable(R.drawable.nodata));
                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        try {
                            InputStream stream = response.body().byteStream();
                            Sharp.loadInputStream(stream).into(target);
                            stream.close();
                        }catch (Exception e){

                        }

                    }
                });

            }

}