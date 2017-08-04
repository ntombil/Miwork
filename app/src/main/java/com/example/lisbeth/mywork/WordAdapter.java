package com.example.lisbeth.mywork;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.lisbeth.mywork.R.id.activity_list_item;
import static com.example.lisbeth.mywork.R.id.list_item;

/**
 * Created by LISBETH on 2017/07/10.
 */

public class WordAdapter extends ArrayAdapter <word>{

/**resource ID for the background color for this list of words**/
private int mColorResourceID;

 public WordAdapter(Activity context,ArrayList<word> words,int colorResourceId ){
     super(context,0,words);
    mColorResourceID =colorResourceId;
 }


    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        //check if the existing is being reused, otherwise inflate the view
        View ListItemView = convertView;
        if (ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list, parent, false);
        }
        // get th {@ link AndroidFlavor} object located at this position in the list
        word currentWord = getItem(position);

        //Find the Textview in the List_item .xml.layout with the ID version_name
        TextView miworkTextview = (TextView) ListItemView.findViewById(R.id.Miwork_Textview);
        //Get th version name from the current AndroidFlavor object and set this text
        miworkTextview.setText(currentWord.getmMiworkTranslation());


        TextView defaultTextview = (TextView) ListItemView.findViewById(R.id.Default_TextView);

        defaultTextview.setText(currentWord.getmDefaultTranslation());

        ImageView imageview = (ImageView) ListItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {

            imageview.setImageResource(currentWord.getmImageTranslation());
            imageview.setVisibility(View.VISIBLE);
        }
        else{
            imageview.setVisibility(View.GONE);
        }
        View textContainer=ListItemView.findViewById(R.id.text_context);
        int color= ContextCompat.getColor(getContext(),mColorResourceID);
        textContainer.setBackgroundColor(color);
            return ListItemView;
        }

    }


