package com.sourabh.appnews.core;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.sourabh.businessService.RechargeService;
import com.sourabh.entity.OperatorPlansSegregator;
import com.sourabh.entity.User;
import com.sourabh.utility.Utilities;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RechargeActivity extends Activity {

    OperatorPlansSegregator operatorPlansSegregator = null;
    EditText mobileNo;
    Spinner operatorType;
    EditText amount;
    RadioButton rechargeself;
    RadioButton rechargeother;

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 12121) {
            amount.setText(Utilities.readFromSharedPref(getApplicationContext(), "RECHARGE_TYPE"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        mobileNo = (EditText) findViewById(R.id.mobNoForRecharge);
        operatorType = (Spinner) findViewById(R.id.operatorType);
        amount = (EditText) findViewById(R.id.amountToCharge);
        mobileNo.setText(User.getInstance().getMobileNo());
        mobileNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 10) {
                    AsyncRechargeOperator asyncRechargeOperator = new AsyncRechargeOperator();
                    asyncRechargeOperator.execute();
                }
            }
        });

    }

    public void self(View v) {
        rechargeself = (RadioButton) findViewById(R.id.radioButton);
        if (rechargeself.isChecked()) {
            mobileNo.setText(User.getInstance().getMobileNo());

        } else {
            mobileNo.setText("");

        }

    }

    public void other(View v) {
        rechargeother = (RadioButton) findViewById(R.id.radioButton2);
        if (rechargeother.isChecked()) {
            mobileNo.setText("");
        } else {
            mobileNo.setText("");
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recharge, menu);
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

    public void redeem(View v) {
        String operatorCode = "";
        if (Integer.parseInt(User.getInstance().getCredit()) >= Integer.parseInt(amount.getText().toString())) {
            if (operatorType.getSelectedItem().toString().equals("Airtel")) {

                operatorCode = "1";
            } else if (operatorType.getSelectedItem().toString().equals("Vodafone")) {

                operatorCode = "2";
            } else if (operatorType.getSelectedItem().toString().equals("BSNL")) {

                operatorCode = "3";
            } else if (operatorType.getSelectedItem().toString().equals("Reliance CDMA")) {

                operatorCode = "4";
            } else if (operatorType.getSelectedItem().toString().equals("Reliance GSM")) {

                operatorCode = "5";
            } else if (operatorType.getSelectedItem().toString().equals("Aircel")) {

                operatorCode = "6";
            } else if (operatorType.getSelectedItem().toString().equals("MTNL")) {

                operatorCode = "25";
            } else if (operatorType.getSelectedItem().toString().equals("Idea")) {

                operatorCode = "8";
            } else if (operatorType.getSelectedItem().toString().equals("Tata Docomo")) {

                operatorCode = "11";
            } else if (operatorType.getSelectedItem().toString().equals("Virgin GSM")) {

                operatorCode = "14";
            } else if (operatorType.getSelectedItem().toString().equals("Uninor")) {

                operatorCode = "16";
            } else if (operatorType.getSelectedItem().toString().equals("Uninor Special")) {

                operatorCode = "1601";
            } else if (operatorType.getSelectedItem().toString().equals("Videocon")) {

                operatorCode = "17";
            } else if (operatorType.getSelectedItem().toString().equals("Videocon Special")) {

                operatorCode = "1701";
            } else if (operatorType.getSelectedItem().toString().equals("Videocon Special")) {

                operatorCode = "1701";
            } else if (operatorType.getSelectedItem().toString().equals("Videocon Special")) {

                operatorCode = "1701";
            } else {
                operatorCode = "1";
            }
            String result = operatorCode + "&service=" + mobileNo.getText().toString() + "&amount=" + amount.getText();
            AsyncRecharge asyncRecharge = new AsyncRecharge();
            asyncRecharge.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, result);
        } else {
            Toast.makeText(getApplicationContext(), "Insufficient credits", Toast.LENGTH_LONG).show();
        }
    }

    public void viewPlans(View v) {
        OperatorPlans.operatorPlansSegregator = operatorPlansSegregator;
        Intent navigationIntent = new Intent(getApplicationContext(), OperatorPlans.class);
        startActivityForResult(navigationIntent, 12121);
    }

    private class AsyncRecharge extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {

            try {


                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(new HttpGet("http://www.urecharge.in/api/recharge.php?" + arg0[0] + "&orderid=1212&siteid=cor0001&token=acfce968ec7738f474cebbea325a37e9"));

                HttpEntity entity = response.getEntity();
                InputStream is = entity.getContent();
                String the_string_response = convertStreamToString(is);
               /* HttpResponse<JsonNode> response = Unirest.get("https://sphirelabs-mobile-number-portability-india-operator-v1.p.mashape.com/index.php?number=8559859173")
                        .header("X-Mashape-Key", "ZA4RZgMblzmsh2mFxQZCNF6eQ9cdp1gQ49jjsnpCrr5zCTsiFX")
                        .header("Accept", "application/json")
                        .asJson();*/
                Toast.makeText(getApplicationContext(), "Response " + the_string_response, Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("Error in http connection " + e.toString());

            }
            return null;
        }

        protected void onPostExecute(Void result) {
        }

    }

    private class AsyncRechargeOperator extends AsyncTask<String, OperatorPlansSegregator, OperatorPlansSegregator> {
        ProgressDialog pg = new ProgressDialog(RechargeActivity.this);

        @Override
        protected void onPreExecute() {
            pg.setMessage("Loading Operator Info and Plans...");
            pg.setCanceledOnTouchOutside(false);
            pg.show();

        }

        @Override
        protected OperatorPlansSegregator doInBackground(String... arg0) {

            try {
                RechargeService rechargeService = new RechargeService();
                operatorPlansSegregator = rechargeService.fetchOperatorAndPlans(mobileNo.getText().toString());


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("Error in http connection " + e.toString());

            }
            return operatorPlansSegregator;
        }

        @Override
        protected void onPostExecute(OperatorPlansSegregator operatorPlansSegregator) {
            pg.hide();
            operatorType.setSelection(((ArrayAdapter) operatorType.getAdapter()).getPosition(operatorPlansSegregator.getRoaming().get(0).getOperator_master()));

        }

    }
}
