package com.sourabh.appnews.core;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sourabh.DTO.LoginDTO;
import com.sourabh.businessService.AuthenticationInterface;
import com.sourabh.businessService.AuthenticationService;
import com.sourabh.database.Thedb;
import com.sourabh.entity.User;

public class LoginActivity extends Activity {

    AuthenticationInterface authenticationInterface;
    Boolean authentic = false;
    Intent navigationIntent;
    private LoginDTO loginDTO;

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        initialize();


    }

    private void initialize() {
        loginDTO = new LoginDTO();
        authenticationInterface = new AuthenticationService();
//		Intent alarmIntent = new Intent(this, AlarmReciever.class);
//		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
//		AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//	    int interval = 10000;
//
//	    manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);

    }

    private boolean checkIfUserSignedIn() {
        boolean flag = false;
        Thedb databaseHandler;
        databaseHandler = Thedb.getInstance(getApplicationContext());
        String userId = databaseHandler.getUser().getUserId();
        flag = userId != null;
        return flag;
    }


    public void signUpForm(View view) {
        navigationIntent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(navigationIntent);

    }

    public void signIn(View view) {

        assembleDTO();
        AsyncSignIn asynSignIn = new AsyncSignIn();
        asynSignIn.execute();


    }

    public void assembleDTO() {

        EditText userName = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        loginDTO.setUsername(userName.getText().toString());
        loginDTO.setPassword(password.getText().toString());
    }

    private class AsyncSignIn extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... arg0) {

            Thedb databaseHandler;
            try {
                authentic = authenticationInterface.doLogin(loginDTO).getIsAuthentic();
                databaseHandler = Thedb.getInstance(getApplicationContext());
                databaseHandler.addUser(User.getInstance());
                return true;
            } catch (Exception ex) {
                return false;
            }

        }

        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.Login_Successfull), Toast.LENGTH_LONG).show();
                if (authentic) {
                    navigationIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(navigationIntent);
                    finish();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
            }
        }

    }

}

