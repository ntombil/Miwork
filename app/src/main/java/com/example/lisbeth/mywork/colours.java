package com.example.lisbeth.mywork;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class colours extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeLister=
            new AudioManager.OnAudioFocusChangeListener() {

                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange== AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    else if (focusChange== AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();
                    }
                    else if (focusChange==AudioManager.AUDIOFOCUS_LOSS){
                        releaseMediaPlayer();
                    }
                }
            };




    android.media.MediaPlayer.OnCompletionListener mCompletionListener=new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private android.util.Log Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        //create and setup the {@link Audiomanager to request audio focus

        mAudioManager =(AudioManager)getSystemService(Context.AUDIO_SERVICE);


        //create an list of words
       final ArrayList<word> words= new ArrayList<word>() ;

        //word.add(one)
        words .add(new word("red","wetetti",R.drawable.color_red,R.raw.color_red));
        words .add(new word("masturd yellow","chiwiita",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words .add(new word("dusty yellow","topiisa",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words .add(new word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        words .add(new word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        words .add(new word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        words .add(new word("black","kululli",R.drawable.color_black,R.raw.color_black));
        words .add(new word("white","kelelli",R.drawable.color_white,R.raw.color_white));
//diplay a single word
        WordAdapter adapter= new WordAdapter(this,words,R.color.category_colors);
        ListView listview=(ListView)findViewById(R.id.RootView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view ,int position,long id) {
                word Word = words.get(position);

                int results =mAudioManager.requestAudioFocus(mOnAudioFocusChangeLister, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (results==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(colours.this, Word.getmAudioResourceId());

                    mMediaPlayer.start();// no need to call prepare(); create() does that for you
                    mMediaPlayer.setOnCompletionListener(mCompletionListener) ;

                }



            }
        });
    }
    protected void OnStop(){
        super.onStop();
        releaseMediaPlayer();}


    private  void releaseMediaPlayer(){
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer=null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeLister);
        }



    }}
