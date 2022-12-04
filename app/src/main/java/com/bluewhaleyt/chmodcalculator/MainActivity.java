package com.bluewhaleyt.chmodcalculator;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.bluewhaleyt.chmodcalculator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // declare variables
    int intChmodDecVal = 0;
    String strChmodSymbol = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        init();

    }

    public void init() {

        binding.btnReset.setOnClickListener(v -> {

            // clear the text input
            binding.etCode.setText("");

            // reset all checkboxes
            binding.cbUR.setChecked(false);
            binding.cbGR.setChecked(false);
            binding.cbOR.setChecked(false);

            binding.cbUW.setChecked(false);
            binding.cbGW.setChecked(false);
            binding.cbOW.setChecked(false);

            binding.cbUE.setChecked(false);
            binding.cbGE.setChecked(false);
            binding.cbOE.setChecked(false);
        });

        binding.etCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String strCode = binding.etCode.getText().toString();

                binding.tvCode.setText(strCode);

                switch (strCode.length()) {
                    case 0:
                        binding.tvCode.setText("000");
                        break;
                    case 1:
                        binding.tvCode.append("00");
                        break;
                    case 2:
                        binding.tvCode.append("0");
                        break;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        chmodResult();

    }

    public void chmodResult() {

        // Owner Read
        checkCheckBox(binding.cbUR, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                intChmodDecVal += 400;
                strChmodSymbol += "r";
                binding.tvCode.setText(String.format("%03d", intChmodDecVal));
                return null;
            }
        }, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                intChmodDecVal -= 400;
                strChmodSymbol += "-";
                binding.tvCode.setText(String.format("%03d", intChmodDecVal));
                return null;
            }
        });

        // Owner Write
        checkCheckBox(binding.cbUW, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                intChmodDecVal += 200;
                strChmodSymbol += "w";
                binding.tvCode.setText(String.format("%03d", intChmodDecVal));
                return null;
            }
        }, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                intChmodDecVal -= 200;
                strChmodSymbol += "-";
                binding.tvCode.setText(String.format("%03d", intChmodDecVal));
                return null;
            }
        });

        // Owner Execute
        checkCheckBox(binding.cbUE, () -> {
            intChmodDecVal += 100;
            strChmodSymbol += "x";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        }, () -> {
            intChmodDecVal -= 100;
            strChmodSymbol += "-";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        });

        // Group Read
        checkCheckBox(binding.cbGR, () -> {
            intChmodDecVal += 40;
            strChmodSymbol += "r";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        }, () -> {
            intChmodDecVal -= 40;
            strChmodSymbol += "-";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        });

        // Group Write
        checkCheckBox(binding.cbGW, () -> {
            intChmodDecVal += 20;
            strChmodSymbol += "w";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        }, () -> {
            intChmodDecVal -= 20;
            strChmodSymbol += "-";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        });

        // Group Execute
        checkCheckBox(binding.cbGE, () -> {
            intChmodDecVal += 10;
            strChmodSymbol += "x";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        }, () -> {
            intChmodDecVal -= 10;
            strChmodSymbol += "-";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        });

        // Other Read
        checkCheckBox(binding.cbOR, () -> {
            intChmodDecVal += 4;
            strChmodSymbol += "r";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        }, () -> {
            intChmodDecVal -= 4;
            strChmodSymbol += "-";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        });

        // Other Write
        checkCheckBox(binding.cbOW, () -> {
            intChmodDecVal += 2;
            strChmodSymbol += "w";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        }, () -> {
            intChmodDecVal -= 2;
            strChmodSymbol += "-";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        });

        // Other Execute
        checkCheckBox(binding.cbOE, () -> {
            intChmodDecVal += 1;
            strChmodSymbol += "x";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        }, () -> {
            intChmodDecVal -= 1;
            strChmodSymbol += "-";
            binding.tvCode.setText(String.format("%03d", intChmodDecVal));
            return null;
        });

    }

    public void checkCheckBox(CheckBox cb, Callable<Void> trueParam, Callable<Void> falseParam) {

        cb.setOnCheckedChangeListener(((compoundButton, isChecked) -> {

            if (isChecked) {
                try {
                    trueParam.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    falseParam.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}