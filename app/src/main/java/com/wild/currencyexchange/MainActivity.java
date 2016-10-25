package com.wild.currencyexchange;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void showFail(Throwable t) {
        t.printStackTrace();
        Toast.makeText(this, "fail: " + t, Toast.LENGTH_SHORT).show();
    }

    private void showResponse(Response<ResponseBody> response) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        try {
            Log.d("RESPONSE", response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
