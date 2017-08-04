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

public class family extends AppCompatActivity {
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
        words .add(new word("Father","apa",R.drawable.family_father,R.raw.family_father));
        words .add(new word("Mother","ata",R.drawable.family_mother,R.raw.family_mother));
        words .add(new word("Son","angsi",R.drawable.family_son,R.raw.family_son));
        words .add(new word("Daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        words .add(new word("Older Brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words .add(new word("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words .add(new word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        words .add(new word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words .add(new word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words .add(new word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_father));
//diplay a single word
        WordAdapter adapter= new WordAdapter(this,words,R.color.category_family);
        ListView listview=(ListView)findViewById(R.id.RootView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view ,int position,long id) {
                word Word = words.get(position);

                int results =mAudioManager.requestAudioFocus(mOnAudioFocusChangeLister, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (results==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

                    mMediaPlayer = MediaPlayer.create(family.this, Word.getmAudioResourceId());

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
