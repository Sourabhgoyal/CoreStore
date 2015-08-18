package com.sourabh.backgroundwork;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.sourabh.appnews.core.R;
import com.sourabh.database.Thedb;

import java.util.ArrayList;

public class LoadTestActivity extends Activity {
    Thedb databaseHandler;
    TextView status;
    int counter = 0;
    int upperLimit = 500;
    int maxUpperLimit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_test);
        /*status=(TextView)findViewById(R.id.checkload);
        status.setText("Please wait intializing test...");*/
        AsyncPopulateContacts asyncPopulateContacts = new AsyncPopulateContacts();
        asyncPopulateContacts.execute();
//		status.setText("Running test...");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.load_test, menu);
        return true;
    }

    public void progressComplete() {

        status.setText("Test Complete");
    }

    private class AsyncPopulateContacts extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {


            ArrayList<ContactsDTO> contactsDTOList = new ArrayList<ContactsDTO>();
            ContactsDTO contactsDTO = null;
            databaseHandler = Thedb.getInstance(getApplicationContext());
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            maxUpperLimit = phones.getCount() - 10;
            while (phones.moveToNext() && counter < upperLimit) {

                String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                //if(User.getInstance().getUserId()!=null && User.getInstance().getUserId()!=""  )
                contactsDTO = new ContactsDTO(name, phoneNumber, "", "");
                contactsDTOList.add(contactsDTO);
                databaseHandler.addContact(name, phoneNumber, "");
                counter++;

            }
            phones.close();

            BackgroundWork backgroundWork = new BackgroundWork();
            String result = backgroundWork.addContatcts(contactsDTOList);

            return null;
        }

        protected void onPostExecute(Void result) {
            Toast.makeText(getApplicationContext(), "Load Test Complete", Toast.LENGTH_LONG).show();
            finish();
            /*upperLimit+=200;
			if(upperLimit<maxUpperLimit){
			AsyncPopulateContacts asyncPopulateContacts=new AsyncPopulateContacts();
			asyncPopulateContacts.execute();
			}*/
        }

    }
}
