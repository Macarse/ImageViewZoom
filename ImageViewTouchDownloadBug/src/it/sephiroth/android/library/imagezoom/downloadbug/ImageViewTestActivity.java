package it.sephiroth.android.library.imagezoom.downloadbug;

import android.content.Intent;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouch.OnImageViewTouchDoubleTapListener;
import it.sephiroth.android.library.imagezoom.ImageViewTouch.OnImageViewTouchSingleTapListener;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase.DisplayType;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase.OnDrawableChangeListener;
import it.sephiroth.android.library.imagezoom.downloadbug.utils.DecodeUtils;
import android.app.Activity;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class ImageViewTestActivity extends Activity {
	
	private static final String LOG_TAG = "image-test";

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		setContentView( R.layout.main );
	}

    public void imageOneButtonClick(final View v) {
        launchFullScreenActivity("https://dl.dropbox.com/u/12706953/test1.jpg");
    }

    public void imageTwoButtonClick(final View v) {
        launchFullScreenActivity("https://dl.dropbox.com/u/12706953/test2.png");
    }

    private void launchFullScreenActivity(final String url) {
        final Intent launchIntent = new Intent(this, FullScreenActivity.class);
        launchIntent.putExtra(FullScreenActivity.URL_KEY, url);
        startActivity(launchIntent);
    }
}
