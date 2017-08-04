package com.example.lisbeth.mywork;

/**
 * Created by LISBETH on 2017/07/10.
 */

public class word {
    //** Default translation for the word
    private String mDefaultTranslation;
private int mAudioResourceId;
    /**Miwork translation for the word*/
    private String mMiworkTranslation;
/**Image resource ID for the word*/
    private int mImageResourseID=No_IMAGE_PROVIDED;
    private static final int No_IMAGE_PROVIDED=-1;

    public word(String DefaultTranslation, String MiworkTranslation ) {
        mDefaultTranslation = DefaultTranslation;
        mMiworkTranslation = MiworkTranslation;
        }


    public word(String DefaultTranslation, String MiworkTranslation,int ImageResourseID,int audioResourceId) {
        mDefaultTranslation = DefaultTranslation;
        mMiworkTranslation = MiworkTranslation;
        mAudioResourceId=audioResourceId;
        mImageResourseID = ImageResourseID;
    }

    public void setmDefaultTranslation(String mDefaultTranslation) {
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public void setmMiworkTranslation(String mMiworkTranslation) {
        this.mMiworkTranslation = mMiworkTranslation;
    }

    /**get the default translation  for the word*/
    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**get the default translation  for the word*/
    public String getmMiworkTranslation() {
        return mMiworkTranslation;
    }

/**return the image resource ID of the word*/
    public int getmImageTranslation () {
        return mImageResourseID;
    }
        /**return whether or not there is an image for this word*/
        public boolean hasImage(){
       return mImageResourseID != No_IMAGE_PROVIDED;
    }

public int getmAudioResourceId (){return mAudioResourceId; }
}

