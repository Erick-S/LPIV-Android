package br.org.fundatec.linhasonibus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ItinerarioActivity extends AppCompatActivity {

    private ListView itinerarioView;
    private RequestQueue mVolleyQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerario);
        itinerarioView = (ListView)findViewById(R.id.itinerarioView);
        mVolleyQueue = Volley.newRequestQueue(this);
        getItinerario();
    }

    private void getItinerario() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=5487" ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Itinerario itinerario = parse( response );
                        ArrayAdapter<Itinerario.LatLng> adapter = new ArrayAdapter<>(
                                ItinerarioActivity.this, android.R.layout.simple_list_item_1,
                                itinerario.positions
                        );
                        itinerarioView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mVolleyQueue.add(stringRequest);
    }

    private Itinerario parse(String response) {
        Itinerario it = new Itinerario();
        try {
            JSONObject json = new JSONObject(response);
            it.codigo = json.getString("codigo");
            it.idlinha = json.getString("idlinha");
            it.nome = json.getString("nome");
            Integer perna = 0;
            // Get each numbered entry...
            while (json.has(perna.toString())) {
                String teste = json.getJSONObject(perna.toString()).toString();
                Log.i("Perna", " " + teste);
                Double lat = json.getJSONObject(perna.toString()).getDouble("lat");
                Double lng = json.getJSONObject(perna.toString()).getDouble("lng");
                it.positions.add( new Itinerario.LatLng( lat, lng ) );
                perna++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return it;
    }
}
