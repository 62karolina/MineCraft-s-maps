package com.sss.carolina.minecraftsmaps;

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

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by carolina on 08.06.17.
 */

public class Halloween extends AppCompatActivity implements ViewSwitcher.ViewFactory,
        android.view.GestureDetector.OnGestureListener {


    private int[] mImageIds = { R.drawable.halloweendraw, R.drawable.halloweenscreen};

    TextView textView;
    ImageSwitcher mImageSwitcher;
    ImageButton button;

    AssetManager assetManager;

    private GestureDetector mGestureDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.description);

        textView = (TextView)findViewById(R.id.descr);
        mImageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);

        mImageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(this);

        Animation inAnimation = new AlphaAnimation(0, 1);
        inAnimation.setDuration(2000);
        Animation outAnimation = new AlphaAnimation(1, 0);
        outAnimation.setDuration(2000);

        mImageSwitcher.setInAnimation(inAnimation);
        mImageSwitcher.setOutAnimation(outAnimation);

        mImageSwitcher.setImageResource(mImageIds[0]);

        assetManager = getAssets();

        mGestureDetector = new GestureDetector(this, this);

        button = (ImageButton)findViewById(R.id.downloadButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Inflens inflens = new Inflens("/mnt/internal/Minecraft");

                InputStream in = null; // открываем файл
                try {
                    in = assetManager.open("Maps/halloween.zip");
                    inflens.loadzip(in);
                    Toast.makeText(Halloween.this, "Downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

        textView.setText("A horrible Minecraft map for true mystic and Halloween lovers\n" +
                "\n" +
                "When you get tired of regular world routine and want to experience some extraordinary fearful feelings you should definitely try this MCPE map. The map is very dark and easily brings you to the world of mystic and horror.  Everything is designed in the spirit of Halloween: the huge moon with sinister image on it, the square pumpkins, weird buildings with orange lights, spiderwebs, tomb roods which make you think you are on a real graveyard. Everything adds some kind of mystics of Halloween holiday to this Minecraft map. \n" +
                "You will admire this MCPE map and get the unforgettable emotions while exploring it. Have you ever dreamed to visit the witch\u0092s house and the house of ugly giant? On this Minecraft map you will have a great opportunity to explore them, but be very careful as the numerous monsters are wandering all around. \n" +
                "Download this Minecraft map and enjoy exploring the horrible mystic world. Our application is easy to be installed, same as MCPE maps downloader. \n" +
                "Attention! This map is not developed by Mojang. Minecraft is a trademark of Mojang AB. Please note that we are not affiliated with Mojang AB, but we adhere to the terms set out by Mojang AB at https://account.mojang.com/terms");


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