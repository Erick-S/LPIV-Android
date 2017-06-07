package br.org.fundatec.linhasonibus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
