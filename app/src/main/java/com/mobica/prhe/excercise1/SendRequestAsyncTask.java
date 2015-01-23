package com.mobica.prhe.excercise1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.URL;

/**
 * Created by przemek on 23.01.15.
 */
public class SendRequestAsyncTask extends AsyncTask<URL, Void, Integer> {

    private static final Integer ERROR_CODE = -1;
    private static final int STATUS_OK = 200;
    private Activity activity;
    private ProgressDialog progressDialog;

    public SendRequestAsyncTask(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        showProgressDialog();
    }

    @Override
    protected Integer doInBackground(URL... urls) {
        Integer responseCode = sendRequest(urls[0]);
        return responseCode;
    }

    private Integer sendRequest(URL url) {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response;
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            return ERROR_CODE;
        }

        return response.code();
    }

    @Override
    protected void onPostExecute(Integer code) {
        super.onPostExecute(code);
        hideProgressDialog();
        Resources resources = activity.getResources();
        if (code.intValue() == STATUS_OK) {
            Toast.makeText(activity, resources.getString(R.string.response_OK), Toast.LENGTH_SHORT).show();
        } else if (code.intValue() == ERROR_CODE) {
            Toast.makeText(activity, resources.getString(R.string.response_ERROR), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, resources.getString(R.string.response_FAIL), Toast.LENGTH_SHORT).show();
        }
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(activity.getResources().getString(R.string.loading_message));
        progressDialog.show();
    }

    private void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }
}
