package com.sss.carolina.minecraftsmaps;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by carolina on 08.06.17.
 */

public class FlyDream extends AppCompatActivity implements ViewSwitcher.ViewFactory,
        android.view.GestureDetector.OnGestureListener {


    private ImageSwitcher mImageSwitcher;
    ImageButton button;
    TextView textView;


    int position = 0;
    private int[] mImageIds = {R.drawable.flydraw, R.drawable.flyscreen};

    private GestureDetector mGestureDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;



    AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.description);

        assetManager = getAssets();

        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(this);


        assetManager = getAssets();


        button = (ImageButton)findViewById(R.id.downloadButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Inflens inflens = new Inflens("/mnt/internal/Minecraft");

                InputStream in = null; // открываем файл
                try {
                    in = assetManager.open("Maps/Fly.zip");
                    inflens.loadzip(in);
                    Toast.makeText(FlyDream.this, "Downloaded", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });

        Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(2000);
        Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(2000);

        textView = (TextView) findViewById(R.id.descr);

        textView.setText("Survive of the falling from a big height in the Minecraft map, show your courage\n" +
                "\n" +
                "This map is one of many MCPE maps of the \"Dropper\" series; you have 6 different tunnels (or holes) on this map to fall. Each MCPE tunnel is unique and you have to concentrate and collect all your hidden talents in one fist to do the first step and start falling, as a result of which you can both, come out victorious, flying all through the tunnel, and break yourself, not to be dodged in time. Most likely, the first attempt will not be very successful for you in the Minecraft game, but you will not stop, you will try it many times, again and again.\n" +
                "If you are brave, strong and you are not afraid of heights, then you choose the right map. Download the Minecraft map, where you can dive into a world of adventure and extraordinary emotions.  You will not regret it for a moment! The MCPE map task is very simple but it is complex at the same time. Drop down and try not to break!\n" +
                "Attention! This map is not developed by Mojang. Minecraft is a trademark of Mojang AB. Please note that we are not affiliated with Mojang AB, but we adhere to the terms set out by Mojang AB at https://account.mojang.com/terms ");

        mImageSwitcher.setInAnimation(inAnimation);
        mImageSwitcher.setOutAnimation(outAnimation);

        mImageSwitcher.setImageResource(mImageIds[0]);

        mGestureDetector = new GestureDetector(this, this);

    }




    public void setPositionNext() {
        position++;
        if (position > mImageIds.length - 1) {
            position = 0;
        }
    }

    public void setPositionPrev() {
        position--;
        if (position < 0) {
            position = mImageIds.length - 1;
        }
    }


    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new
                ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        imageView.setBackgroundColor(0xFF000000);
        return imageView;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                return false;
            // справа налево
            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                setPositionNext();
                mImageSwitcher.setImageResource(mImageIds[position]);
            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                // слева направо
                setPositionPrev();
                mImageSwitcher.setImageResource(mImageIds[position]);
            }
        } catch (Exception e) {
            // nothing
            return true;
        }
        return true;
    }
}
