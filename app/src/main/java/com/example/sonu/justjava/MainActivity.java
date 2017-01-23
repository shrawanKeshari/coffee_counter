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
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    CheckBox cb_whipped, cb_chocolate;

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

    public void submitOrder(View view) {
        EditText et = (EditText) findViewById(R.id.name_field);
        String name = et.getText().toString();

        if (name.equals("")) {
            dialog("Please Enter the name");
        } else {
            cb_whipped = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
            boolean hasWhippedCream = cb_whipped.isChecked();

            cb_chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
            boolean hasChocolate = cb_chocolate.isChecked();

            int price = calculatePrice(quantity, hasWhippedCream, hasChocolate);
            String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
            displayMessage(priceMessage);
        }
    }

    private int calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (hasWhippedCream)
            basePrice += 1;
        if (hasChocolate)
            basePrice += 2;
        int price = quantity * basePrice;
        return price;
    }

    private String createOrderSummary(int price_, boolean hasWhippedCream_,
                                      boolean hasChocolate_, String name_) {
        String message = "Name: " + name_;
        message += "\nAdd Whipped Cream? " + hasWhippedCream_;
        message += "\nAdd Chocolate? " + hasChocolate_;
        message += "\nQuantity: " + quantity;
        message += "\nTotal: $" + price_ + "\nThank You!";
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
}
