package com.Fyp.navismarthajj;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowSavedPathList extends AppCompatActivity {
    String placename, from, to, daterr;
    String[] place = null;
    String[] origen = null;
    String[] dest = null;
    String[] dater = null;
    public ListView list;
    public ArrayAdapter<String> listAdapter;
    int k=1;
    String Slecteditem;
    Cursor resultSet;
    String name, phone;
    static SQLiteDatabase mydatabase;
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_saved_path_list);

        try {
            resultSet = mydatabase.rawQuery("Select * from Currentlocationpoints2",
                    null);
            Toast.makeText(getApplicationContext(),place[k],Toast.LENGTH_SHORT).show();

            resultSet.moveToFirst();
            while (resultSet.isAfterLast() == false) {

                place[k] = resultSet.getString(0);
                origen[k] = resultSet.getString(1);
                dest[k] = resultSet.getString(2);
                dater[k] = resultSet.getString(3);
                k++;
                Toast.makeText(getApplicationContext(),place[k],Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_LONG).show();
        }

        try {
            adapter = new CustomListAdapter(this, place, origen, dest, dater);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_LONG).show();
        }
        try {
            Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_LONG).show();
            list = (ListView) findViewById(R.id.pathlist);
            list.setAdapter(adapter);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"3",Toast.LENGTH_LONG).show();
        }
        try {
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    // TODO Auto-generated method stub
                    Slecteditem = place[+position];

                    showluv();
                }
            });
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(),"4",Toast.LENGTH_LONG).show();
        }
    }

    public void showluv(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = this.getLayoutInflater().inflate(R.layout.content_geo_custom_layer, null);
        builder.setView(inflater.inflate(R.layout.content_geo_custom_layer, null))
                // Add action buttons
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        final TextView showpath = (TextView) findViewById(R.id.showpath);
                        showpath.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ShowSavedPathList.this,ShowSavedPath.class);
                                intent.putExtra("keyName", Slecteditem);
                                startActivity(intent);
                            }
                        });

                        TextView shownavigation = (TextView) findViewById(R.id.navigatestart);
                        shownavigation.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ShowSavedPathList.this,NavigationSavedPath.class);
                                intent.putExtra("keyName", Slecteditem);
                                startActivity(intent);

                            }
                        });

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
