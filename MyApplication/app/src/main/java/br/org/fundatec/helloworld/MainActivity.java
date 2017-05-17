package br.org.fundatec.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtVolume;
    private EditText edtValue;
    private TextView txtVolumeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //My content
        edtVolume = ((EditText)findViewById(R.id.edtVolume));
        edtValue = ((EditText)findViewById(R.id.edtValue));
        txtVolumeValue = ((TextView)findViewById(R.id.txtVolumeValue));
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculate(View view) {
        //Toast.makeText(this, "Hello World!", Toast.LENGTH_SHORT).show();
        float volume = Float.valueOf(edtVolume.getText().toString());
        float value = Float.valueOf(edtValue.getText().toString());
        float volumeValue = ((value/volume)*1000);
//        volumeValue = Math.round(volumeValue*100);
//        volumeValue = volumeValue/100; //Convert value
        String volumeValueTxt = String.format("%.2f", volumeValue); //Format string
        txtVolumeValue.setText("R$"+volumeValueTxt);
    }
}
