package com.example.sonu.justjava;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity == 0) {
            dialog("Can't go beyond zero");
        } else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    private void dialog(String s) {
        AlertDialog.Builder al = new AlertDialog.Builder(this);
        al.setTitle("Alert Dialog");
        al.setMessage(s);

        al.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int button1) {
                        dialog.cancel();
                    }

                });
        AlertDialog alertDialog = al.create();
        al.show();
    }

    public void submitOrder(View view) {
        cb = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = cb.isChecked();
        int price = calculatePrice(quantity);
        String priceMessage = createOrderSummary(quantity, price, hasWhippedCream);
        displayMessage(priceMessage);
    }

    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }

    private String createOrderSummary(int quantity_, int price_, boolean hasWhippedCream_) {
        String message = "Name: Shrawan Kumar Keshari";
        message += "\nAdd Whipped Cream? " + hasWhippedCream_;
        message += "\nQuantity: " + quantity_ + "\nTotal: $" + price_ + "\nThank You!";
        return message;
    }

    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    private void displayMessage(String name) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(name);
    }
}
