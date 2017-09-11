package com.example.tomi.pos_android;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TabHost tabHost;
    final Map<String, Integer> total = new HashMap<String, Integer>();
    final Map<String, Integer> item = new HashMap<String, Integer>();
    final String[] itemNameList = {"Beef", "port", "lamb", "chicken", "Beef2", "port2", "lamb2", "chicken2",
            "Beef3", "port3", "lamb3", "chicken3"};
    int[] itemPrice = {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    private int totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < itemNameList.length; i++) {
            item.put(itemNameList[i], itemPrice[i]);
        }

        addTab();
        addGradView();
        Button payBillButton = (Button) findViewById(R.id.payBillButton);
        payBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hello World");
                openPayBillDialog();
            }
        });
    }


    private void addTab() {
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
    }

    private void addGradView() {

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ItemAdapter(this, itemNameList, itemPrice));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "Fuck! is " + itemNameList[position], Toast.LENGTH_SHORT).show();
                String itemName = itemNameList[position];
                openAmountDialog(itemName);
            }
        });
    }

    private int calculateTotal() {
        int result = 0;
        for (Map.Entry<String, Integer> entry: total.entrySet()) {
            result += entry.getValue() * item.get(entry.getKey());
        }
        this.totalAmount = result;
        return result;
    }

    private void renderTable() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.total_table);
        tableLayout.removeViews(2, tableLayout.getChildCount() - 2 );


        for (Map.Entry<String, Integer> entry: total.entrySet()) {
            TextView textItem = new TextView(this);
            TextView textSinglePrice = new TextView(this);
            TextView textAmount = new TextView(this);
            TextView textTotalPrice = new TextView(this);
            textItem.setTextSize(10);
            textSinglePrice.setTextSize(10);
            textAmount.setTextSize(10);
            textTotalPrice.setTextSize(10);

            TableRow tableRow = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.removeAllViews();

            tableRow.setLayoutParams(layoutParams);
            String itemName = entry.getKey();
            int price = item.get(itemName);
            int amount = entry.getValue();
            textItem.setText(itemName);
            textSinglePrice.setText(String.valueOf(item.get(itemName)));
            textAmount.setText(String.valueOf(amount));
            textTotalPrice.setText(String.valueOf(price * amount));

            tableRow.addView(textItem);
            tableRow.addView(textAmount);
            tableRow.addView(textSinglePrice);
            tableRow.addView(textTotalPrice);
            tableLayout.addView(tableRow);
        }
        updateTotalAmount(calculateTotal());


    }

    private void updateTotalAmount(int intTotalAmount) {
        TextView totalAmount = (TextView) findViewById(R.id.total_amount);
        totalAmount.setText(String.valueOf(intTotalAmount));
    }

    public void openAmountDialog(final String itemName) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialogamount_layout,
                (ViewGroup) findViewById(R.id.dialogAmount));
        AlertDialog alert = new AlertDialog.Builder(this).setTitle("數量").setView(layout)
                .setPositiveButton("确定", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Dialog f = (Dialog) dialogInterface;
                        EditText editTextAmount = (EditText) f.findViewById(R.id.editText_amount);
                        if (editTextAmount.getText().toString().compareTo("") == 0) {
                            return;
                        }
                        int amount = Integer.parseInt(editTextAmount.getText().toString());
                        total.put(itemName, amount);
                        renderTable();
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    public void openPayBillDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.dialogpaybill_layout,
                (ViewGroup) findViewById(R.id.dialogPayBill));

        TextView payBillTotalAmount_textView = (TextView) layout.findViewById(R.id.payBillTotalAmount);
        payBillTotalAmount_textView.setText(String.valueOf(totalAmount));

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final Dialog dialog = new Dialog(this);
        alert.setTitle("總計");
        alert.setView(layout);
        final AlertDialog.Builder builder = alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.out.println("onclick");
            }
        });
        alert.setNegativeButton("取消", null);
        alert.show();
    }

}