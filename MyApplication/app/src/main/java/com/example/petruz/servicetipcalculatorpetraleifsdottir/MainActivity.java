package com.example.petruz.servicetipcalculatorpetraleifsdottir;

import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText    mealPrice;
    private SeekBar     score;
    private Button      calcBtn;
    private TextView    totPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mealPrice =    (EditText) findViewById(R.id.price);
        this.score =        (SeekBar) findViewById(R.id.seekBar);
        this.calcBtn =      (Button) findViewById(R.id.calcBtn);
        this.totPriceText = (TextView) findViewById(R.id.totPrice);

        this.calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mealPrice.getText().toString().matches(""))
                {
                    totPriceText.setText(R.string.price_for_meal);
                    return;
                }

                Double price = Double.valueOf(mealPrice.getText().toString());
                Double tip = 0.0;
                Integer scoreValue;
                Double totPrice;

                scoreValue = score.getProgress();

                if(scoreValue <= 3)
                {
                    tip = 1.1;
                }
                else if (scoreValue > 3 && scoreValue <= 5)
                {
                    tip = 1.13;
                }
                else if(scoreValue > 5 && scoreValue <=7)
                {
                    tip = 1.15;
                }
                else if(scoreValue > 7 && scoreValue <=9)
                {
                    tip = 1.20;
                }
                else
                    {
                        tip = 1.25;
                    }

                totPrice = price * tip;
                totPrice = roundTwoDecimals(totPrice);
                String totalPriceStr = getString(R.string.total_price);

                totPriceText.setText(totalPriceStr + totPrice.toString());

            }
        });


    }

    double roundTwoDecimals(double totPrice) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(totPrice));
    }
}
