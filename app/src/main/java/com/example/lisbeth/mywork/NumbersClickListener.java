package com.example.lisbeth.mywork;

import android.view.View;
import android.widget.Toast;

/**
 * Created by LISBETH on 2017/07/06.
 */

public class NumbersClickListener implements View.OnClickListener  {
    @Override
    public void onClick(View v) {
        Toast .makeText(v.getContext(),"open the list of numbers",Toast .LENGTH_SHORT ).show() ;
}}
