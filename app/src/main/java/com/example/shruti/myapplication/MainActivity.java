package com.example.shruti.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {



        //-------------- code for intent-------------------
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("geo:47.6, -122.3"));
//        if(intent.resolveActivity(getPackageManager()) != null){
//            startActivity(intent);
//        }

        // -------------- code for order summary ---------
        EditText text1 = (EditText)findViewById(R.id.editText);
        String name = text1.getText().toString();
        CheckBox checkBox = (CheckBox)findViewById(R.id.check);
        boolean hasWhippedCream = checkBox.isChecked();
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.check1);
        boolean hasChocolate = checkBox2.isChecked();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        //Intent for email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));// only mail app will handle this
        //  intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT,"Just Java Order for "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

        displayMessage(priceMessage);
    }

    private int calculatePrice(){
        return quantity * 5;
    }
    private String createOrderSummary(String name, int price, Boolean checkValue, boolean checkValue2)
    {
        String priceMessage = "Name:" + name;
        priceMessage  += "\n Add whipped Cream?" + checkValue;
        priceMessage  += "\n Add Chocolate?" + checkValue2;
        priceMessage += "\n Quanity : " + quantity;
        priceMessage += "\n Total: $" + price;
        priceMessage += "\nThank You!";
        return priceMessage;
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayPrice(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.price_text_view);
        quantityTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    public void increment(View view)
    {
        if(quantity ==100){
            Toast.makeText(this, "You can not have more then 100 cups",Toast.LENGTH_SHORT).show();
            return;

        }
        quantity = quantity + 1;
        display(quantity);
    }
    public void decrement(View view){
        if(quantity ==1){
            Toast.makeText(this, "You can not have less then 1 cups",Toast.LENGTH_SHORT).show();

            return;
        }
        quantity = quantity -1;
        display(quantity);
    }
    public void displayMessage(String s)
    {
        TextView priceTextView = (TextView)findViewById(R.id.price_text_view);
        priceTextView.setText(s);
    }

}