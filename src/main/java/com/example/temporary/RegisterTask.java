package com.example.temporary;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterTask extends AsyncTask<Void, Void, Void> {
    DataBaseHelper dbhelper;
    User user;

    public RegisterTask(Context context, User user) {
        this.dbhelper = new DataBaseHelper(context);
        this.user = user;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            NetworkService.getInstance()
                    .getUserAPI().register(new ToRegister(user)).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        Log.i("Token", response.body().getToken());
                        //dbhelper.insertToken(response.body().getToken());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
