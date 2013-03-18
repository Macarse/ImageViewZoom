package it.sephiroth.android.library.imagezoom.downloadbug;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FullScreenActivity extends Activity implements ImageViewTouch.OnImageViewTouchSingleTapListener {
    public static final String URL_KEY = "url_key";

    private View mProgress;
    private ImageViewTouch mImageViewTouch;
    private String mImageUrl;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_screen);

        mImageUrl = getIntent().getStringExtra(URL_KEY);
        mProgress = findViewById(R.id.progress);
        mImageViewTouch = (ImageViewTouch) findViewById(R.id.large_image);

        mImageViewTouch.setSingleTapListener(this);

        new DownloadImagesTask().execute();
    }


    @Override
    public void onSingleTapConfirmed() {
        finish();
    }


    private Bitmap downloadImage(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (Exception e) {
            // Exceptions are not important in this sample code
        }

        return bm;
    }

    public class DownloadImagesTask extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... voids) {
            return downloadImage(mImageUrl);
        }

        @Override
        protected void onPostExecute(Bitmap bm) {
            if ( bm != null ) {
                mProgress.setVisibility(View.GONE);
                mImageViewTouch.setImageBitmap(bm);
            }
        }
    }
}