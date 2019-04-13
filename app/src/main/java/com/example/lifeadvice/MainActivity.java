package com.example.lifeadvice;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private class GetAdviceClass extends AsyncTask<Void, Void, Slip>{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.adviceslip.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AdviceClient adviceClient = retrofit.create(AdviceClient.class);

        TextView textView = findViewById(R.id.adviceView);
        Button button = findViewById(R.id.button);

        // the code below on the progress dialog was taken from stackoverflow.com/questions/9170228/android-asynctask-dialog-circle
        ProgressDialog progDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();

            progDialog.setMessage("Loading Advice...");
            progDialog.setIndeterminate(false);
            progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progDialog.setCancelable(true);
            progDialog.show();

        }

        @Override
        protected Slip doInBackground(Void... voids) {
            Call<Advice> adviceCall = adviceClient.getAdvice();

            try {
                Response<Advice> adviceResponse = adviceCall.execute();
                Slip slip = adviceResponse.body().getSlip();
                return slip;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Slip result){
            textView.setText("\"" + result.getAdvice() + "\"");
            button.setText("Get More Advice");
            progDialog.dismiss();
        }

    }

    public void launchAdvice(View view){
        GetAdviceClass myAdviceClass = new GetAdviceClass();
        myAdviceClass.execute();
    }


}


