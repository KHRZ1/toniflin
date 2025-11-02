package com.etflin.toniflin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Spinner tonSpinner;
    private TextView tonAdd, tonTotalTf, tonIndicator, tonAdjust;
    private RecyclerView tonItemList;
    private String[] Excipient;
    private BarangAdapter barangAdapter;
    private ArrayList<BarangItem> barangItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tonSpinner = findViewById(R.id.tonSpinner);
        tonAdd = findViewById(R.id.tonAdd);
        tonItemList = findViewById(R.id.tonItemList);
        tonTotalTf = findViewById(R.id.tonTotalResult);
        tonIndicator = findViewById(R.id.tonIndicator);
        tonAdjust = findViewById(R.id.tonAdjust);


        getExc();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        tonItemList.setLayoutManager(layoutManager);
        barangAdapter = new BarangAdapter(this, barangItems, tonTotalTf, tonAdjust);
        tonItemList.setAdapter(barangAdapter);

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Excipient);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tonSpinner.setAdapter(aa);

        tonAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Input the level of the ingredient in %b/v or %v/v");

            final EditText input = new EditText(this);
            input.setBackgroundResource(R.drawable.border10dp);
            input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            input.setPadding(20,10, 20,10);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(10, 10, 10, 10);
            input.setLayoutParams(layoutParams);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String gram = "0";
                    if(!input.getText().toString().equals("")){
                        gram = input.getText().toString();
                    }
                    String[] data = tonSpinner.getSelectedItem().toString().split("\\t");
                    barangItems.add(new BarangItem(data[0], data[1], gram, false));
                    barangAdapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        });



        tonTotalTf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }


            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString().replace(",", ".");
                if(Double.valueOf(value) < 0.51){
                    tonIndicator.setText("(Hipotonis)");
                    tonIndicator.setTextColor(Color.RED);
                } else if(Double.valueOf(value) > 0.525){
                    tonIndicator.setText("(Hipertonis)");
                    tonIndicator.setTextColor(Color.BLUE);
                } else {
                    tonIndicator.setText("(Isotonis)");
                    tonIndicator.setTextColor(Color.GREEN);
                }
            }
        });
    }


    private void getExc(){
        List<String> Exc = new ArrayList<>();
        try {
            InputStream inputStream = this.getResources().openRawResource(R.raw.tb_exc);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                Exc.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        Excipient = new String[Exc.size()];
        Exc.toArray(Excipient);
    }
}