package br.org.fundatec.tocadj;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddMusicActivity extends AppCompatActivity {

    private RequestQueue mVolleRequestQueue;
    private Boolean edit = false;
    private Music music;
    @BindView(R.id.edtMusic) EditText mEdtMusic;
    @BindView(R.id.edtName) EditText mEdtName;
    @BindView(R.id.Musica) TextView mMusic;
    @BindView(R.id.Name) TextView mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mVolleRequestQueue = Volley.newRequestQueue(this);
        ButterKnife.bind(this);
        YoYo.with(Techniques.RotateInUpLeft).duration(600).playOn(mEdtMusic);
        YoYo.with(Techniques.RotateInUpLeft).duration(600).playOn(mEdtName);
        YoYo.with(Techniques.RotateInUpLeft).duration(500).playOn(mMusic);
        YoYo.with(Techniques.RotateInUpLeft).duration(500).playOn(mName);

        if(getIntent().hasExtra("key")){
            edit = true;
            music = new Music(getIntent().getStringExtra("key"),
                    getIntent().getStringExtra("music"),
                    getIntent().getStringExtra("user"));
            mEdtMusic.setText(music.getMusic());
            mEdtName.setText(music.getUser());
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send) {
            enviaMusica();
        }

        return super.onOptionsItemSelected(item);
    }

    public void enviaMusica(){
//        POST "https://fundatecti09.firebaseio.com/";  //Docs puts an -demo in demonstration URL...
        try {
            JSONObject obj = new JSONObject();
//            obj.put("music", "Fixed Music");
//            obj.put("user", "Fixed User");
            obj.put("music", mEdtMusic.getText());
            obj.put("user", mEdtName.getText());

            JsonObjectRequest json = new JsonObjectRequest(Request.Method.POST,
                    "https://fundatecti09.firebaseio.com/musicas.json",
                    obj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            finish();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddMusicActivity.this, "Tente Novamente!", Toast.LENGTH_SHORT).show();
                            Log.e("FIREBASE PUT", error.getMessage());
                        }
                    }
            );

            mVolleRequestQueue.add(json);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    } //end brackets for enviaMusica()

}
