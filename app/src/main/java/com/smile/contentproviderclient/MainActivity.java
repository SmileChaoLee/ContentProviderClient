package com.smile.contentproviderclient;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String authority = new String("com.smile.contentprovidertest.provider01");
    private static final String providerURI = new String("content://"+authority+"/employees");
    private static final Uri contentURI = Uri.parse(providerURI);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button retrieveButton = findViewById(R.id.buttonRetrieve);
        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve  employee records
                // String URL = "content://com.smile.provider.SmileCompany/employees";
                // String URL = EmployeeContentProvider.providerURL();
                // Uri URI = Uri.parse(URL);

                // String selection = EmployeeContentProvider.employeeId + " = ? ";
                // String[] selectionArgs = {"2"};

                String selection = null;
                String[] selectionArgs = null;
                String[] projection = {"emId", "emName", "emPhone"};
                Cursor c = getContentResolver().query(contentURI, projection, selection, selectionArgs, "name");
                if (c.moveToFirst()) {
                    do{
                        /*
                        Toast.makeText(this,
                                c.getString(c.getColumnIndex(EmployeeContentProvider.employeeId)) +
                                        ", " +  c.getString(c.getColumnIndex( EmployeeContentProvider.employeeName)) +
                                        ", " + c.getString(c.getColumnIndex( EmployeeContentProvider.employeePhone)),
                                Toast.LENGTH_SHORT).show();
                                */


                        Toast.makeText(getApplicationContext(),
                                c.getString(0) +
                                        ", " +  c.getString(1) +
                                        ", " + c.getString(2),
                                Toast.LENGTH_SHORT).show();

                    } while (c.moveToNext());
                }

                c.close();
            }
        });
    }
}