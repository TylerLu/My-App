package lu.tyler.myapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends Activity {

    TextView defaultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defaultTextView = (TextView)findViewById(R.id.defaultTextView);

        new GetUSDExchangeRatesTask().execute();
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

    private class GetUSDExchangeRatesTask extends AsyncTask<Void, Void, ExchangeRate[]> {

        @Override
        protected ExchangeRate[] doInBackground(Void... param) {
            return USDUtil.GetExchangeRates();
        }

        @Override
        protected void onPostExecute(ExchangeRate[] exchangeRates) {
             if(exchangeRates.length > 0) {
                float rate = exchangeRates[0].getValue();
                defaultTextView.setText(Float.toString(rate));
            }
        }
    }
}
