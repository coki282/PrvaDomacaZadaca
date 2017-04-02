package coki.prvadomacazadaca;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText realx;
    EditText imagx;
    EditText realy;
    EditText imagy;
    Button calculate;
    RadioGroup operation;
    public static final String RESULTCOMPLEX = "RESULT";
    public static final String ACOMPLEX = "ACOMPLEX";
    public static final String BCOMPLEX = "BCOMPLEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realx = (EditText) findViewById(R.id.realx);
        imagx = (EditText) findViewById(R.id.imagx);
        realy = (EditText) findViewById(R.id.realy);
        imagy = (EditText) findViewById(R.id.imagy);
        calculate = (Button) findViewById(R.id.calculate);
        operation = (RadioGroup) findViewById(R.id.operation);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(realx.getText().length()==0 || imagx.getText().length()==0 || realy.getText().length()==0 || imagy.getText().length()==0){
                    Toast.makeText(MainActivity.this, "Sve varijable moraju biti postavljene!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double rx = Double.parseDouble(realx.getText().toString());
                double ix = Double.parseDouble(imagx.getText().toString());
                double ry = Double.parseDouble(realy.getText().toString());
                double iy = Double.parseDouble(imagy.getText().toString());

                Complex a = new Complex(rx, ix);
                Complex b = new Complex(ry, iy);
                Complex result;

                int checkedButton = operation.getCheckedRadioButtonId();

                if(checkedButton == R.id.add){
                    result = a.plus(b);
                } else if(checkedButton == R.id.sub){
                    result = a.minus(b);
                } else if(checkedButton == R.id.mul){
                    result = a.times(b);
                } else if(checkedButton == R.id.div){
                    result = a.divides(b);
                } else {
                    Log.d("ERROR", "No radio button in radio group (operation) selected or id not found");
                    throw new RuntimeException("Cheched button id not found");
                }

                if(result == null){
                    Log.d("INITIALIZATION ERROR", "result variable of complex operation uninitialized");
                    throw new NullPointerException("Result variable uninitialized");
                }


                Intent intent = new Intent(MainActivity.this, GraphicDisplayActivity.class);
                intent.putExtra(RESULTCOMPLEX, result);
                intent.putExtra(ACOMPLEX, a);
                intent.putExtra(BCOMPLEX, b);
                startActivity(intent);

            }
        });
    }
}
