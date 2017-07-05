package com.example.adrianreda.zadanie12;

import android.app.Activity;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    Button addData;
    EditText eName;
    EditText eBrand;
    EditText eYear;
    EditText eid;
    DbAdapter dh;
    Button getdata;
    Button deletedata;
    Button updatedata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dh = new DbAdapter(this);
        addData = (Button) findViewById(R.id.button);
        eName = (EditText) findViewById(R.id.editText);
        eBrand = (EditText) findViewById(R.id.editText2);
        eYear = (EditText) findViewById(R.id.editText3);
        getdata = (Button) findViewById(R.id.button2);
        deletedata = (Button) findViewById(R.id.button3);
        eid = (EditText) findViewById(R.id.editText4);
        updatedata =(Button) findViewById(R.id.updateButton);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dh.addData(eid.getText().toString(),eName.getText().toString(),eBrand.getText().toString(),eYear.getText().toString())){
                    Toast.makeText(MainActivity.this,"Added",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteCursor kursor = dh.getData();
                if (kursor.getCount() > 0){
                    StringBuffer buff = new StringBuffer();
                    while (kursor.moveToNext()){
                        buff.append("id:"+kursor.getString(0)+"\n");
                        buff.append("nazwa::"+kursor.getString(1)+"\n");
                        buff.append("marka:"+kursor.getString(2)+"\n");
                        buff.append("rok:"+kursor.getString(3)+"\n");
                    }
                    showMessage("rekord",buff.toString());
                }
                else{
                    showMessage("brak rekordow","brak rekordow");
                }
            }
        });
        deletedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dh.delateData(eid.getText().toString());
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();

            }
        });


        updatedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dh.updateData(eid.getText().toString(), eName.getText().toString(),
                        eBrand.getText().toString(),eYear.getText().toString());
            }
        });


    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.show();

    }
}
