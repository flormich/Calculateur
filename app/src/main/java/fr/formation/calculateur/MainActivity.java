package fr.formation.calculateur;

import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    TextView tvResultat;
    Double tauxPrec = 1.00;
    Double anglePrec = 180.00;
    Button rbEuros, rbDollard, rbLivre, rbBit;

    final Double TAUX_EURO = 1.0;
    final Double TAUX_USD = 1.185;
    final Double TAUX_GBP = 0.88;
    final Double TAUX_BTC = 0.01;

    final Double DEGRES = 180.00;
    final Double RAD = Math.PI;
    final Double GRAD = 200.00;

    Double tauxEuro = TAUX_EURO;
    Double tauxDollard = TAUX_USD;
    Double tauxLivre = TAUX_GBP;
    Double tauxBit = TAUX_BTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResultat = findViewById(R.id.tvResultat);

        rbEuros = findViewById(R.id.rbEuros);
        rbEuros.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tauxEuro = Double.parseDouble(tvResultat.getText().toString());
                Toast.makeText(MainActivity.this, "Taux Euros modifié", Toast.LENGTH_SHORT).show();
                vibrate();
                return true;
            }
        });

        rbDollard = findViewById(R.id.rbDollard);
        rbDollard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tauxDollard = Double.parseDouble(tvResultat.getText().toString());
                Toast.makeText(MainActivity.this, "Taux Dollars modifié", Toast.LENGTH_SHORT).show();
                vibrate();
                return false;
            }
        });

        rbBit = findViewById(R.id.rbBit);
        rbBit.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tauxBit = Double.parseDouble(tvResultat.getText().toString());
                Toast.makeText(MainActivity.this, "Taux BitCoin modifié", Toast.LENGTH_SHORT).show();
                vibrate();
                return false;
            }
        });

        rbLivre = findViewById(R.id.rbLivre);
        rbLivre.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tauxLivre = Double.parseDouble(tvResultat.getText().toString());
                Toast.makeText(MainActivity.this, "Taux LivreSterling modifié", Toast.LENGTH_SHORT).show();
                vibrate();
                return false;
            }
        });
    }

    public void effacer(View view) {
        tvResultat.setText("");
    }

    public void touch (View view) {
        Button b = (Button) view;
        if (tvResultat.getText().toString().equals("0")) {
            tvResultat.setText(b.getText());
        } else {
            tvResultat.setText(tvResultat.getText().toString() + b.getText());
        }
    }

    public void plus(View view) {
        tvResultat.setText(tvResultat.getText() + " + ");
    }
    public void moins(View view) {
        tvResultat.setText(tvResultat.getText() + " - ");
    }
    public void multi(View view) {
        tvResultat.setText(tvResultat.getText() + " * ");
    }
    public void diviser(View view) {
        tvResultat.setText(tvResultat.getText() + " / ");
    }

    public void egale(View view) {
        Evaluator eval = new Evaluator();
        String ecran = tvResultat.getText().toString();
        try {
            tvResultat.setText(eval.evaluate(ecran));
        } catch (EvaluationException e) {
            e.printStackTrace();
        }

    }

    public void convDevise (Double taux){
        String ecran = tvResultat.getText().toString();
        Double valeur = Double.parseDouble(ecran);
        Double result = valeur / tauxPrec * taux;
        DecimalFormat str = new DecimalFormat("#.####");
        String tx = str.format(result);
        tx = tx.replace(",", ".");

        tvResultat.setText(tx);
        tauxPrec = taux;
    }

    public void vibrate() {
        Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        if(vib.hasVibrator()){
            int tpsvib = 400;
            vib.vibrate(tpsvib);
        }
    }

    public void convAngle (Double angle) {
        String ecran = tvResultat.getText().toString();
        Double valeur = Double.parseDouble(ecran);
        Double result = valeur / anglePrec * angle;
        DecimalFormat str = new DecimalFormat("#.####");
        String ang = str.format(result);
        ang = ang.replace(",", ".");
        tvResultat.setText(ang);
        anglePrec = angle;
    }

    public void btEur(View view) {
        convDevise(tauxEuro);
    }
    public void btDoll(View view) {
        convDevise(tauxDollard);
    }
    public void btLivre(View view) {
        convDevise(tauxLivre);
    }
    public void btBtc(View view) {
        convDevise(tauxBit);
    }

    public void btDeg(View view) {
        convAngle(DEGRES);
    }
    public void btRad(View view) {
        convAngle(RAD);
    }
    public void btGrd(View view) {
        convAngle(GRAD);
    }


}
