package br.org.fundatec.sqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    private EditText mEdtName;
    private EditText mEdtGender;
    private EditText mEdtBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mEdtName = (EditText)findViewById(R.id.nameEdit);
        mEdtGender = (EditText)findViewById(R.id.genderEdit);
        mEdtBirth = (EditText)findViewById(R.id.birthEdit);
    }

    public void inserirPessoa(View view) {
        BDLite db = new BDLite(this);
        
        Person person = new Person();
        person.setName(mEdtName.getText().toString());
        person.setGender(mEdtGender.getText().toString());
        person.setBirth(mEdtBirth.getText().toString());

        db.insertPerson(person);

        finish();
    }
}
