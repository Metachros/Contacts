package com.example.metar.contacts_oc;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText NameET,PhoneET,OMET;
    String uname,uphone,uOM;
    SQLiteDatabase MyDB;
    ConstraintLayout constraintLayout;
    public void onClick(View v) {
        if (v.getId() == R.id.layout) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            constraintLayout = findViewById(R.id.layout);
            constraintLayout.setOnClickListener(this);
        }
    public void SaveDetails(View view) {
        NameET = findViewById(R.id.editName);
        PhoneET = findViewById(R.id.editPhone);
        OMET = findViewById(R.id.editOM);

        uname = NameET.getText().toString();
        uphone = PhoneET.getText().toString();
        uOM = OMET.getText().toString();

        try {
            MyDB = this.openOrCreateDatabase("RegisteredUsers", MODE_PRIVATE, null);
            MyDB.execSQL("CREATE TABLE IF NOT EXISTS RegisteredUsers(name VARCHAR, phone VARCHAR, msg VARCHAR)");
            String SQLCommand="INSERT INTO RegisteredUsers(name,phone,msg) VALUES('"+uname+"','"+uphone+"','"+uOM+"')";
            MyDB.execSQL(SQLCommand);
        } catch (Exception e) {

        }

    }
        public void ViewDetails(View view){
        Intent intent= new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(intent);
    }
}
