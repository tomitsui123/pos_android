package com.example.tomi.pos_android;

import android.app.ActionBar;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.tomi.pos_android.R.id.itemName;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by tomi on 9/9/17.
 */

class CustomListener implements View.OnClickListener {
    private final Dialog dialog;
    private int amount;
    public CustomListener(Dialog dialog, int amount) {
        this.dialog = dialog;
        this.amount = amount;
    }

    @Override
    public void onClick(View v) {

    }
}
