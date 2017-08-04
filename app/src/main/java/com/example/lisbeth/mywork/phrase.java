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

public class phrase extends AppCompatActivity {
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
      final  ArrayList<word> words= new ArrayList<word>() ;
words .add(new word("Where are you going?","minto wuksus") );
        words .add(new word("What is your name?","tinna oyaase'na") );
        words .add(new word("My name is","oyaaset") );
        words .add(new word("How are you feeling?","michaksas") );
        words .add(new word("I'm feeling good?","kuchi achit") );
        words .add(new word("Are you coming?","aanas'aa") );
        words .add(new word("Yes, I'm coming","haa'aanam") );
        words .add(new word("I'm coming","aanam") );
        words .add(new word("let's go.","yoowutis") );
        words .add(new word("Come here","anni'nem") );

        //diplay a single word
        WordAdapter adapter= new WordAdapter(this,words,R.color.category_phrases);
        ListView listview=(ListView)findViewById(R.id.RootView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view ,int position,long id) {
                word Word = words.get(position);

                int results =mAudioManager.requestAudioFocus(mOnAudioFocusChangeLister, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (results==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaPlayer = MediaPlayer.create(phrase.this, Word.getmAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
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
