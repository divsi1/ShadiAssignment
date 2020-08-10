package com.example.shadiassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.shadiassignment.Model.User;
import com.example.shadiassignment.Roomdb.DatabaseClient;
import com.example.shadiassignment.Roomdb.UserEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onAcceptedOrDeclinedReceived {
    private ArrayList<UserEntity> mUsers = new ArrayList<UserEntity>();
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button btn=(Button)findViewById(R.id.btn_test);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //callToApi();
        showData();
    }

    public void callToApi() {

        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        String url = String.format("https://randomuser.me/api/?results=10");

        StringRequest req = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //success
                        Log.d("string url response", response);
                        try {
                            JSONObject myResponse = new JSONObject(response);
                            JSONArray results = myResponse.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                UserEntity user = new UserEntity();
                                user.setGender(results.getJSONObject(i).getString("gender"));
                                user.setEmail(results.getJSONObject(i).getString("email"));
                                user.setDob(results.getJSONObject(i).getJSONObject("dob").getString("date"));
                                user.setPicture(results.getJSONObject(i).getJSONObject("picture").getString("large"));
//                                user.setFirst_name(results.getJSONObject(i).getJSONObject("name").getString("first"));
//                                user.setLast_name(results.getJSONObject(i).getJSONObject("name").getString("last"));
                                user.setName(results.getJSONObject(i).getJSONObject("name").getString("first") + " " +
                                        results.getJSONObject(i).getJSONObject("name").getString("last"));
                                user.setPhone_number(results.getJSONObject(i).getString("phone"));
                                mUsers.add(user);
                                // for inserting individual users to db
                                onResponseReceived.responseSuccess(user);
                            }
                            onResponseReceived.responseSuccessComplete(mUsers);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //do add to db
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handle error response
                        Log.d("error response", error.toString());

                    }
                }
        );

        queue.add(req);
    }

    OnResponseReceived onResponseReceived = new OnResponseReceived() {
        @Override
        public void responseSuccess(UserEntity userEntity) {
            insertDataToDb(userEntity);
        }

        @Override
        public void responseSuccessComplete(ArrayList<UserEntity> users) {
            UserAdapter adapter = new UserAdapter(MainActivity.this, mUsers, MainActivity.this);
            mRecyclerView.setAdapter(adapter);
        }
    };

    void insertDataToDb(UserEntity userEntity) {
        class insertDBData extends AsyncTask<Void, Void, Void> {
            Context context;
            UserEntity userEntity;

            insertDBData(Context context, UserEntity userEntity) {
                this.context = context;
                this.userEntity = userEntity;
            }

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .insert(userEntity);
                return null;
            }


        }

        insertDBData su = new insertDBData(this, userEntity);
        su.execute();
    }

    //show data in recycler view
    private void showData() {
        class showUsers extends AsyncTask<Void, Void, List<UserEntity>> {

            @Override
            protected List<UserEntity> doInBackground(Void... voids) {
                List<UserEntity> userList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .userDao()
                        .getAll();
                return userList;
            }

            @Override
            protected void onPostExecute(List<UserEntity> users) {
                super.onPostExecute(users);
                if (users.size() != 0) {
                    Log.d("users", "!null");
                    UserAdapter adapter = new UserAdapter(MainActivity.this, users, MainActivity.this);
                    mRecyclerView.setAdapter(adapter);
                } else {
                    Log.d("users", "null");
                    if (isNetworkAvailable()) {
                        Log.d("network available ", "true");

                        //   Toast.makeText(MainActivity.this,"making api call",Toast.LENGTH_SHORT).show();
                        callToApi();
                    } else {
                        Log.d("network available ", "false");
                        Toast.makeText(MainActivity.this, "no internet access", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        }

        showUsers su = new showUsers();
        su.execute();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    void updateDataToDb(UserEntity userEntity) {
        class updateDBData extends AsyncTask<Void, Void, Void> {
            Context context;
            UserEntity userEntity;

            updateDBData(Context context, UserEntity userEntity) {
                this.context = context;
                this.userEntity = userEntity;
            }

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .userDao()
                        .update(userEntity.getIsAcceptedOrDeclined(), userEntity.getEmail());
                return null;
            }


        }

        updateDBData su = new updateDBData(this, userEntity);
        su.execute();
    }

    @Override
    public void updatedUserEntity(UserEntity userEntity) {
        updateDataToDb(userEntity);
        //  showData();
    }
}

