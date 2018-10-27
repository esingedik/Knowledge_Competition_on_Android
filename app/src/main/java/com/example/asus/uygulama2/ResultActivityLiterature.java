package com.example.asus.uygulama2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivityLiterature extends Activity {

    public TextView trueTextView, falseTextView;
    Button againButton;
    String trueV, falseV;
    SharedPreferences preferencesT,preferencesF;
    SharedPreferences.Editor editorT,editorF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        trueTextView = (TextView) findViewById(R.id.trueTextView);
        falseTextView = (TextView)findViewById(R.id.falseTextView);
        againButton = (Button) findViewById(R.id.again_button);

        preferencesT = getSharedPreferences("LiteratureTrueScore",MODE_PRIVATE);
        editorT = preferencesT.edit();

        trueV =  preferencesT.getString("LiteratureTrueScore", "");
        trueTextView.setText(trueV);

        preferencesF = getSharedPreferences("LiteratureFalseScore",MODE_PRIVATE);
        editorF = preferencesF.edit();

        falseV =  preferencesF.getString("LiteratureFalseScore", "");
        falseTextView.setText(falseV);

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoIntent = new Intent(getApplicationContext(),InfoActivity.class);
                startActivity(infoIntent);
                finish();
            }
        });
    }
}