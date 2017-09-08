package com.example.tomi.pos_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.tomi.pos_android.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Tab One1");
        host.addTab(spec);

        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tab Two");
        host.addTab(spec);

        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab Three");
        host.addTab(spec);

        Map<String, Integer> item = new HashMap<String, Integer>();
        String[] itemName = {"Beef", "port", "lamb", "chicken", "Beef", "port", "lamb", "chicken", "Beef", "port", "lamb", "chicken"};
        int[] itemPrice = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        for (int i = 0; i < itemName.length; i++) {
            item.put(itemName[i], itemPrice[i]);
        }
        Map<String, Integer> total = new HashMap<String, Integer>();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ItemAdapter(this, itemName, itemPrice));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "Fuck" + position,
                        Toast.LENGTH_LONG).show();
                System.out.println(position);
//                total.put();
            }
        });





    }

}
