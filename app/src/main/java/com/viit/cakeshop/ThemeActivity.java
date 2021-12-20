package com.viit.cakeshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.viit.cakeshop.R;


public class ThemeActivity extends Activity {

    private int [] mLogos;
    private GridView mGridView;
    private int cakeOpt;
    private int creamOpt;
    private int weightOpt;
    private EditText mThemeEdit;
    private EditText mTitleEdit;
    private EditText mNumberEdit;
    private EditText mEmailEdit;
    private EditText mDateEdit;
    private Button mContinueButton;
    private EditText mAddressEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);
        Intent intent = getIntent();
//        cakeOpt = intent.getIntExtra("cakeOpt", 2);
//        creamOpt = intent.getIntExtra("creamOpt", 2);
//        weightOpt = intent.getIntExtra("weightOpt", 0);
//        Toast.makeText(this, " " + cakeOpt + " " + creamOpt, Toast.LENGTH_LONG).show();
        mThemeEdit = (EditText) findViewById(R.id.theme_edit_text);
        mTitleEdit = (EditText) findViewById(R.id.cake_edit_text);
        mNumberEdit = (EditText) findViewById(R.id.phone_edit);
        mDateEdit = (EditText) findViewById(R.id.dateTime);
        mEmailEdit = (EditText) findViewById(R.id.emailText_edit);
        mAddressEdit =(EditText) findViewById(R.id.address_edit);
//        mLogos = Pages.themePics;
//        ThemeAdapter adapter = new ThemeAdapter(this, mLogos);
//        mGridView = (GridView) findViewById(R.id.themeGrid);
//        mGridView.setAdapter(adapter);

        mContinueButton = (Button) findViewById(R.id.nextButton);
        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String themeText =  mThemeEdit.getText().toString();
                String titleText = mTitleEdit.getText().toString();
                String numberText = mNumberEdit.getText().toString();
                String dateText = mDateEdit.getText().toString();
                String emailText = mEmailEdit.getText().toString();
                String addressText = mAddressEdit.getText().toString();

                Constant.cakeText = titleText;
                Constant.cakeTheme = themeText;
                Constant.dateText = dateText;
                Constant.numberText = numberText;
                Constant.emailText = emailText;
                Constant.address = addressText;

                Intent intent = new Intent(ThemeActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });
    }


}
