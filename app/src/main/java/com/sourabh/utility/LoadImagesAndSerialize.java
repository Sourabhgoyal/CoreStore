package com.sourabh.utility;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.sourabh.singletons.Images;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImagesAndSerialize {

    public synchronized Bitmap fetchImages(String url, Context context) {
        Bitmap bitmap = null;
        if (Images.getFileInstance(context).imageMap.get(url) == null) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteStream);
            Images.getInstance(context).imageMap.put(url, byteStream.toByteArray());
            Utilities.saveToFile(context, "Images", Images.getInstance(context));
        } else {
            bitmap = BitmapFactory.decodeByteArray(Images.getInstance(context).imageMap.get(url), 0, Images.getInstance(context).imageMap.get(url).length);
        }


        return bitmap;
    }
}
