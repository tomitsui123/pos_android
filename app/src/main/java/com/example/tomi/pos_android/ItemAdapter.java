package com.example.tomi.pos_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by tomi on 9/2/17.
 */

public class ItemAdapter extends BaseAdapter {
    private Context context;
    private String[] itemName;
    private int[] itemPrice;
    private static LayoutInflater inflater = null;
    public ItemAdapter(MainActivity mainActivity, String[] itemName, int[] itemPrice) {
        this.context= mainActivity;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return itemName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class Holder {
        TextView name;
        TextView price;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.item, null);
        holder.name= rowView.findViewById(R.id.itemName);
        holder.price= rowView.findViewById(R.id.itemPrice);

        holder.name.setText(itemName[position]);
        holder.price.setText(String.valueOf(itemPrice[position]));

//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "You Clicked "+itemName[position], Toast.LENGTH_LONG).show();
//            }
//        });
        return rowView;
    }
}