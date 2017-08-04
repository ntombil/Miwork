package com.example.lisbeth.mywork;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import static android.media.AudioManager.*;
@RequiresApi(api = Build.VERSION_CODES.N)

public class numbers extends AppCompatActivity {

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
       final  ArrayList<word>words= new ArrayList<word>() ;
        words .add(new word("one","lutti",R.drawable.number_one,R.raw.number_one ));
        words .add(new word("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        words .add(new word("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words .add(new word("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words .add(new word("Five","massokka",R.drawable.number_five,R.raw.number_five));
        words .add(new word("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        words .add(new word("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words .add(new word("Eigth","kenekaku",R.drawable.number_eight,R.raw.number_eight));
        words .add(new word("Nine","kawita",R.drawable.number_nine,R.raw.number_nine));
        words .add(new word("Ten","wo'e",R.drawable.number_ten,R.raw.number_ten));


//diplay a single word
        WordAdapter adapter= new WordAdapter(this,words,R.color.category_numbers);
        ListView listview=(ListView)findViewById(R.id.RootView);
        listview.setAdapter(adapter);

listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
    @Override
    public void onItemClick(AdapterView<?> parent, View view ,int position,long id) {
        word Word = words.get(position);

        int results =mAudioManager.requestAudioFocus(mOnAudioFocusChangeLister, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        if (results==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){

            mMediaPlayer = MediaPlayer.create(numbers.this, Word.getmAudioResourceId());

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
