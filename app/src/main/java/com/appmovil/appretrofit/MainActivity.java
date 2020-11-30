package com.appmovil.appretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.appmovil.appretrofit.Interface.jsonplaceholder;
import com.appmovil.appretrofit.Model.Posts;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText txtResult;
    private EditText txtId;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = (EditText) findViewById(R.id.txtResult);

    }
    public void btnEnviar(View view)
    {
        txtId = (EditText) findViewById(R.id.txtId);
        if(txtId.length()>0)
        {
            id = Integer.parseInt(txtId.getText().toString());
        }else{
            id=1;
        }

        txtResult.setText("");
        getPosts();
    }
    private void getPosts()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonplaceholder jsonplaceholder = retrofit.create(jsonplaceholder.class);
        Call<List<Posts>> call = jsonplaceholder.getPosts(id);
        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    txtResult.setText("Codigo: " + response.code());
                    return;
                }
                List<Posts> postsList = response.body();
                String content = "";
                for(Posts post: postsList){
                    content += "\n" + "postId: "+post.getPostId()+ "\n";
                    content += "id: "+post.getId()+ "\n";
                    content += "name: "+post.getName()+ "\n";
                    content += "email: "+post.getEmail()+ "\n";
                    content += "body: "+post.getBody()+ "\n";
                    txtResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                txtResult.setText(t.getMessage());
            }
        });
    }
}