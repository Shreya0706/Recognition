package com.example.face;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Settingid extends AppCompatActivity {

    EditText Ed_name,Ed_id,Ed_age,Ed_contact;
    //Button insert;
    DatabaseHelper dbh;
    ImageView back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingid);
        Ed_id=(EditText)findViewById(R.id.editTextTextPersonName);
        Ed_name=(EditText)findViewById(R.id.editTextTextPersonName2);
        Ed_age=(EditText)findViewById(R.id.editTextTextPersonName3);
        Ed_contact=(EditText)findViewById(R.id.editTextTextPersonName4);
        back1 = findViewById(R.id.backbutton1);
        dbh= new DatabaseHelper(Settingid.this);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settingid.this,MainActivity.class));
            }
        });
    }
    public void insertrow(View view)
    {
        Boolean result=  dbh.insertrow(Ed_id.getText().toString(),Ed_name.getText().toString(),Ed_age.getText().toString(),Ed_contact.getText().toString());
        Toast.makeText(this,"Insert : "+result,Toast.LENGTH_SHORT).show();
    }

    public void updaterow(View view)
    {
        Boolean result=  dbh.updaterow(Ed_id.getText().toString(),Ed_name.getText().toString(),Ed_age.getText().toString(),Ed_contact.getText().toString());
        Toast.makeText(Settingid.this,"Update : "+result,Toast.LENGTH_SHORT).show();
    }
    public void deleterow(View view)
    {
        Boolean result=  dbh.deleterow(Ed_id.getText().toString());
        Toast.makeText(Settingid.this,"Delete : "+result,Toast.LENGTH_SHORT).show();
    }

    public void viewrows(View view)
    {
        Cursor result=dbh.viewrows();
        if (result.getCount()==0) {
            Toast.makeText(Settingid.this, "NO Entry Found",Toast.LENGTH_LONG).show();

        }
        else
        {
            StringBuffer buffer = new StringBuffer();
            buffer.append("\tId\t\tName \t\tAge\t\tContact NO "+ "\t\n");
            while(result.moveToNext())
            {
                buffer.append("\t"+ result.getString(0)+"\t");
                buffer.append("\t"+ result.getString(1)+"\t");
                buffer.append("\t"+ result.getString(2)+"\t");
                buffer.append("\t"+ result.getString(3)+"\t");
                buffer.append("\n");

            }
            AlertDialog.Builder builder=new AlertDialog.Builder(Settingid.this);
            builder.setCancelable(true);
            builder.setTitle("Studnet Details");
            builder.setMessage(buffer.toString());
            builder.show();
        }



    }
}