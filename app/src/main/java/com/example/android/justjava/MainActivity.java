/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        //int numberOfCoffees = 2;
        //display(quantity);
        //displayPrice(quantity*5);
        EditText name = (EditText) findViewById(R.id.name);
        CheckBox wCream = (CheckBox) findViewById(R.id.whipped_cream);
        CheckBox cTopping = (CheckBox) findViewById(R.id.chocolate_topping);
        String priceMessage = createOrderSummary(5 * quantity, name.getText().toString(), quantity, wCream.isChecked(), cTopping.isChecked());
        //displayMessage(priceMessage);

        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL,new String[]{"subhambiswas@gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT,new String[]{"Coffee Order"});
        email.putExtra(Intent.EXTRA_TEXT,priceMessage);
        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    //private void displayMessage(String Message) {
     //   TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
     //   priceTextView.setText(Message);
    //}

    /**
     * This method displays the given price on the screen.
     */
   // private void displayPrice(int number) {
   //     TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
   //     priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
   // }


    int quantity = 0;

    public void increment(View view) {
        //int quantity = 2;
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        //int quantity = 2;
        if (quantity > 0) quantity = quantity - 1;
        display(quantity);
    }

    private String createOrderSummary(int price, String name, int quantity, boolean hasWhippedCream, boolean hasChocolateTopping) {
        int totalPrice = price;
        if(hasChocolateTopping)
            totalPrice+=2*quantity;
        if(hasWhippedCream)
            totalPrice+=1*quantity;
        String res = "";
        res += "Name : " + name;
        res += "\nQuantity : " + quantity;
        res += "\nWhipped cream addded : " + hasWhippedCream;
        res += "\nChocolate Topping addded : " + hasChocolateTopping;
        res += "\nTotal : " + totalPrice;
        res += "\nThank You!";
        return res;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.android.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
