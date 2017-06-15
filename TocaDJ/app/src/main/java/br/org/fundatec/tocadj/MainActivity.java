package br.org.fundatec.tocadj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private ListView mListViewMusicas;
    private RequestQueue mVolleyQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mListViewMusicas = (ListView)findViewById(R.id.listviewmusicas);
        mVolleyQueue = Volley.newRequestQueue(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddMusicActivity.class));
            }
        });
    }

    public void lerMusicas(){
//        GET "https://fundatecti09.firebaseio.com/musicas.json"

        StringRequest req = new StringRequest("https://fundatecti09.firebaseio.com/musicas.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            //TODO ADD SOME LOGIC HERE -> Transform response in an array...? show in mListViewMusicas
//                            for( ? )
//                            Iterator<?> + while ?
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Error - JSONObject Error", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error - Request Error", Toast.LENGTH_SHORT).show();
                        Log.e("LERMUSICAS", error.getMessage());
                    }
                }
        );
        mVolleyQueue.add(req);

//        JsonObjectRequest json = new JsonObjectRequest(Request.Method.GET,
//                "https://fundatecti09.firebaseio.com/musicas.json",
//                new Response.Listener<Musica[]>() {
//                    @Override
//                    public void onResponse(Musica[] response){
//                        ArrayAdapter<Musica> adapter = new ArrayAdapter<>(
//                                MainActivity.this,
//                                android.R.layout.simple_list_item_1,
//                                response);
//                        mListViewMusicas.setAdapter(adapter);
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e("LERMUSICAS", error.getMessage());
//                    }
//                }
//        );
    }

}
