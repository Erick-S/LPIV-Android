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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddMusicActivity extends AppCompatActivity {

    private RequestQueue mVolleRequestQueue;
    private EditText mEdtMusic;
    private EditText mEdtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mVolleRequestQueue = Volley.newRequestQueue(this);
        mEdtMusic = (EditText)findViewById(R.id.edtMusic);
        mEdtName = (EditText)findViewById(R.id.edtName);

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
//            obj.put("music", "Palästinalied - Walther von der Vogelweide");
//            obj.put("user", "Erick S.");
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
