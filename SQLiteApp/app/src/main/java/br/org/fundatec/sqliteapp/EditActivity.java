package br.org.fundatec.sqliteapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    private EditText mEdtName;
    private EditText mEdtGender;
    private EditText mEdtBirth;
    private BDLite db;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEdtName = (EditText)findViewById(R.id.nameEdit);
        mEdtGender = (EditText)findViewById(R.id.genderEdit);
        mEdtBirth = (EditText)findViewById(R.id.birthEdit);

        if(getIntent().hasExtra("id")){
            Long id = getIntent().getLongExtra("id", 0L);
            db = new BDLite(this);
            person = db.select(id);
            mEdtName.setText(person.getName());
            mEdtGender.setText(person.getGender());
            mEdtBirth.setText(person.getBirth());
            db.close();
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
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_update) {
            editarPessoa();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void editarPessoa() {
        db = new BDLite(this);

        person.setName(mEdtName.getText().toString());
        person.setGender(mEdtGender.getText().toString());
        person.setBirth(mEdtBirth.getText().toString());

        db.update(person);

        db.close();
        finish();
    }

    public void deletarPessoa(View view){
        db = new BDLite(this);

        db.delete(person.getId());

        db.close();
        finish();
    }
}
