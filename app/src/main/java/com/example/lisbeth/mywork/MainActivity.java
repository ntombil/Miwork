package com.example.lisbeth.mywork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView numbers=(TextView)findViewById(R.id.txtNumbers) ;
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, numbers.class);
                startActivity(numbersIntent);

                TextView family=(TextView)findViewById(R.id.txtFamily) ;
                family.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick (View v) {
                        Intent colorIntent = new Intent(MainActivity.this, family.class);
                        startActivity(colorIntent);

                TextView color=(TextView)findViewById(R.id.txtColor) ;
                color.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick (View v){
                    Intent colorIntent = new Intent(MainActivity.this, colours.class);
                    startActivity(colorIntent);


                        TextView phrase=(TextView)findViewById(R.id.txtPhrase) ;
                        phrase.setOnClickListener(new View.OnClickListener(){

                            @Override
                            public void onClick (View v) {
                                Intent phraseIntent = new Intent(MainActivity.this,phrase.class);
                                startActivity(phraseIntent);





                                    }

                                    {

                          {


                                }}




                });
            };
                    });
                    }

                    })


;;}});}

    @Override
    public void onClick(View v) {

    }
}


