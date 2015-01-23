package com.mobica.prhe.excercise1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.et_address)
    TextView etAddress;

    @InjectView(R.id.btn_test)
    Button btnTest;

    @OnClick(R.id.btn_test)
    public void buttonTestClicked() {
        try {
            URL requestURL = new URL(etAddress.getText().toString());
            new SendRequestAsyncTask(MainActivity.this).execute(requestURL);
        } catch (MalformedURLException e) {
            Toast.makeText(MainActivity.this, "Wrong URL!!!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
