package com.sourabh.appnews.core;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sourabh.DTO.RegisterDTO;
import com.sourabh.businessService.AuthenticationInterface;
import com.sourabh.businessService.AuthenticationService;

public class SignUpActivity extends Activity {
    public Boolean authentic = false;
    AuthenticationInterface authenticationInterface;
    Intent navigationIntent;
    private RegisterDTO registerDTO;

    public RegisterDTO getRegisterDTO() {
        return registerDTO;
    }

    public void setRegisterDTO(RegisterDTO registerDTO) {
        this.registerDTO = registerDTO;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupform);
        authenticationInterface = new AuthenticationService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sign_up, menu);
        return true;
    }

    public void signUpUser(View view) {
        validateUserInput();
        assembleDTO();
        AsyncSignUp asyncSignUp = new AsyncSignUp();
        asyncSignUp.execute();


    }

    void assembleDTO() {
        registerDTO = new RegisterDTO();
        EditText userName = (EditText) findViewById(R.id.signup_userName);
        EditText password = (EditText) findViewById(R.id.signup_password);
        EditText mobileNo = (EditText) findViewById(R.id.MobileNo);
        EditText emailId = (EditText) findViewById(R.id.Email_ID);
        EditText confirmPassword = (EditText) findViewById(R.id.confirm_password);
        EditText refferalCode = (EditText) findViewById(R.id.refferalCode);
        EditText address = (EditText) findViewById(R.id.address);
        EditText locality = (EditText) findViewById(R.id.locality);
        EditText city = (EditText) findViewById(R.id.city);
        EditText state = (EditText) findViewById(R.id.State);
        EditText age = (EditText) findViewById(R.id.age);
        registerDTO.setUsername(userName.getText().toString());
        registerDTO.setPassword(password.getText().toString());
        registerDTO.setMobileNo(mobileNo.getText().toString());
        registerDTO.setEmailAddress(emailId.getText().toString());
        registerDTO.setRefferalCode(refferalCode.getText().toString());
        registerDTO.setAddress(address.getText().toString());
        registerDTO.setLocality(locality.getText().toString());
        registerDTO.setCity(city.getText().toString());
        registerDTO.setState(state.getText().toString());
        registerDTO.setAge(age.getText().toString());


    }

    void validateUserInput() {

    }

    private class AsyncSignUp extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... arg0) {
            authentic = authenticationInterface.doRegister(registerDTO).getIsAuthentic();
            return null;
        }

        protected void onPostExecute(Void result) {

            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_Successfull), Toast.LENGTH_LONG).show();
            if (authentic) {
                navigationIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(navigationIntent);
            }
        }

    }


}
