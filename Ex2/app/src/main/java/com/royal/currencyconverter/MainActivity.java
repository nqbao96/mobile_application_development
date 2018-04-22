package com.royal.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String CurArr[]={
            "USD",
            "EUR",
            "VND",
            "THB" // Thai Baht
    };

    public class Currency {
        private final String name;
        private final double rate;
        public Currency(String name, double rate) {
            this.name = name;
            this.rate = rate;
        }
    }
    Currency[] currencies = {
            new Currency("USD", 1.0),
            new Currency("EUR", 0.813681),
            new Currency("VND", 22774.89),
            new Currency("THB", 31.3431),
    };

    EditText edtInput;
    TextView txtResult;
    Button btnConvert;
    Spinner spinSource, spinTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInput = findViewById(R.id.editInput);
        txtResult = findViewById(R.id.textResult);
        btnConvert = findViewById(R.id.buttonConvert);

        spinSource = findViewById(R.id.spinnerSource);
        spinTarget = findViewById(R.id.spinnerTarget);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, CurArr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinSource.setAdapter(adapter);
        //spinSource.setOnItemSelectedListener(new SpinnerActivity());
        spinTarget.setAdapter(adapter);
    }

//    private class SpinnerActivity implements AdapterView.OnItemSelectedListener {
//        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//            Object item = parent.getItemAtPosition(pos);
//        }
//        public void onNothingSelected(AdapterView<?> parent) {
//        }
//    };

    public void OnClick_Convert(View view) {
        if (edtInput.getText().toString().length()==0){
            return;
        }else {
            int indexSource = 0;
            int indexTarget = 0;
            double input = Double.parseDouble(edtInput.getText().toString());
            double output;
            String sourceCur = String.valueOf(spinSource.getSelectedItem());
            String targetCur = String.valueOf(spinTarget.getSelectedItem());
            for (int i = 0; i < 4; i++) {
                if (currencies[i].name == sourceCur) {
                    indexSource = i;
                }
                if (currencies[i].name == targetCur) {
                    indexTarget = i;
                }
            }
            double rate = currencies[indexTarget].rate / currencies[indexSource].rate;
            output = input * rate;
            txtResult.setText(Double.toString(input) + " " + sourceCur + " = " + Double.toString(output) + " " + targetCur);
        }
    }
}
