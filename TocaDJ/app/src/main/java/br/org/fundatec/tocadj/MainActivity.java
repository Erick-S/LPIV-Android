package br.org.fundatec.tocadj;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Music> musics = new ArrayList<>();
    private RequestQueue mVolleyQueue;
//    Find view by id fields
//    private ListView mListViewMusicas;
//    private SwipeRefreshLayout mRefresh;

//    Butterknife annotations
    @BindView(R.id.listviewmusicas) ListView mListViewMusicas;
    @BindView(R.id.refresh) SwipeRefreshLayout mRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        Find View by id methods
//        mRefresh = (SwipeRefreshLayout)findViewById(R.id.refresh);
//        mListViewMusicas = (ListView)findViewById(R.id.listviewmusicas);
//        --//--//--
//        Butterknife method
        ButterKnife.bind(this);
        mRefresh.setColorSchemeResources(R.color.colorAccent);
        mVolleyQueue = Volley.newRequestQueue(this);
        mRefresh.setEnabled(true);
        mRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                YoYo.with(Techniques.Shake)
//                        .duration(200)
                        .playOn(mRefresh);
                lerMusicas();
            }
        });

        mListViewMusicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Music music = ((ArrayAdapter<Music>)parent.getAdapter()).getItem(position);
                Intent intent = new Intent(MainActivity.this, AddMusicActivity.class);
                intent.putExtra("key", music.getKey());
                intent.putExtra("music", music.getMusic());
                intent.putExtra("user", music.getUser());
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddMusicActivity.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        lerMusicas();
    }

    public void lerMusicas(){
//        GET "https://fundatecti09.firebaseio.com/musicas.json"

        StringRequest req = new StringRequest("https://fundatecti09.firebaseio.com/musicas.json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            jsonObject.toString();

                            Iterator<?> keys = jsonObject.keys();

                            while( keys.hasNext() ) {
                                String key = (String)keys.next();
                                if ( jsonObject.get(key) instanceof JSONObject ) {
                                    String user = ((JSONObject) jsonObject.get(key)).get("user").toString();
                                    String music = ((JSONObject) jsonObject.get(key)).get("music").toString();
                                    musics.add(0, new Music(key, music, user));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        ArrayAdapter<Music> adapter = new ArrayAdapter<>(
                                MainActivity.this,
                                android.R.layout.simple_list_item_1,
                                musics);
                        mListViewMusicas.setAdapter(adapter);
                        mRefresh.setRefreshing(false);
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
