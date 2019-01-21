package com.example.temporary;

import android.content.Context;
import android.os.AsyncTask;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class PostIdeasTask extends AsyncTask<Void, Void, Void> {
    Context context;
    Idea idea;
    ToPost post;
    DataBaseHelper db;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    public PostIdeasTask(Context context, Idea idea) {
        this.context = context;
        this.idea = idea;
        db = new DataBaseHelper(context);
        post = new ToPost(idea);
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            NetworkService.getInstance()
                    .getIdeasAPI()
                    .postIdea(post).enqueue(new Callback<Idea>() {
                @Override
                public void onResponse(Call<Idea> call, Response<Idea> response) {
                    if (response.isSuccessful()) {
                        db.insertIdea(idea);
                    }
                }

                @Override
                public void onFailure(Call<Idea> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}

