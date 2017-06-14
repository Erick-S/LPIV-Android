package br.org.fundatec.linhasonibus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue vq;
    private ListView mLinhasOnibus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLinhasOnibus();

        mLinhasOnibus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinhaOnibus linha = ((ArrayAdapter<LinhaOnibus>)parent.getAdapter()).getItem(position);
//                Toast.makeText(MainActivity.this, linha.nome, Toast.LENGTH_SHORT).show();
//                Snackbar.make(view, linha.nome, Snackbar.LENGTH_SHORT).show();
//                Intent intent = new Intent(MainActivity.this, ItinerarioActivity.class);
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("LINHAID", linha.id);
                startActivity(intent);
            }
        });
    }

    private void getLinhasOnibus() {
        mLinhasOnibus = (ListView)findViewById(R.id.mLinhasOnibus);
//        final ArrayList<LinhaOnibus> linhasOnibus = new ArrayList<>();
        vq = Volley.newRequestQueue(this);
        GsonRequest<LinhaOnibus[]> request = new GsonRequest<>(
                "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o",
                LinhaOnibus[].class,
                null,
                new Response.Listener<LinhaOnibus[]>() {
                    @Override
                    public void onResponse(LinhaOnibus[] response) {
//                        for(int i = 0; i < response.length; i++){
//                            LinhaOnibus temp = new LinhaOnibus();
//                            temp.codigo = response[i].codigo.toString();
//                            temp.id = response[i].id.toString();
//                            temp.nome = response[i].nome.toString();
//                            linhasOnibus.add(temp);
//                        }
                        ArrayAdapter<LinhaOnibus> adapter = new ArrayAdapter<>(
                                MainActivity.this,
                                android.R.layout.simple_expandable_list_item_1,
                                response);
                        mLinhasOnibus.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LINHAONIBUS", error.getMessage());
            }
        });
        vq.add(request);
    }
}
