package com.sourabh.appnews.core;


import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sourabh.DTO.RegisterDTO;
import com.sourabh.businessService.AuthenticationInterface;
import com.sourabh.businessService.AuthenticationService;
import com.sourabh.database.Thedb;
import com.sourabh.entity.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountDetailsFragment extends Fragment {
    static String message;
    public Boolean authentic = false;
    RegisterDTO registerDTO;
    TextView password;
    TextView emailAddress;
    TextView mobileNo;
    TextView address;
    TextView locality;
    TextView city;
    TextView state;
    TextView confirmPassword;
    TextView age;
    TextView credit;
    Button saveProfile;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Retrieving the currently selected item number
        User user = User.getInstance();
        // Creating view correspoding to the fragment
        View v = inflater.inflate(R.layout.account_details_layout, container, false);

        // Getting reference to the TextView of the Fragment

        password = (TextView) v.findViewById(R.id.signup_password);
        confirmPassword = (TextView) v.findViewById(R.id.confirm_password);
        emailAddress = (TextView) v.findViewById(R.id.Email_ID);
        mobileNo = (TextView) v.findViewById(R.id.MobileNo);
        address = (TextView) v.findViewById(R.id.address);
        locality = (TextView) v.findViewById(R.id.locality);
        city = (TextView) v.findViewById(R.id.city);
        state = (TextView) v.findViewById(R.id.State);
        credit = (TextView) v.findViewById(R.id.Credit);
        age = (TextView) v.findViewById(R.id.age);
        saveProfile = (Button) v.findViewById(R.id.btnSave);
        emailAddress.setText(user.getUsername());
        mobileNo.setText(user.getMobileNo());
        address.setText(user.getAddress());
        locality.setText(user.getLocality());
        city.setText(user.getCity());
        state.setText(user.getState());
        credit.setText(user.getCredit());


        // Setting currently selected river name in the TextView

        password.setText(user.getPassword());


        saveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerDTO = new RegisterDTO();
                registerDTO.setUsername(emailAddress.getText().toString());
                registerDTO.setPassword(password.getText().toString());
                registerDTO.setMobileNo(mobileNo.getText().toString());
                registerDTO.setEmailAddress(emailAddress.getText().toString());
                registerDTO.setUserid(User.getInstance().getUserId());
                registerDTO.setAddress(address.getText().toString());
                registerDTO.setLocality(locality.getText().toString());
                registerDTO.setCity(city.getText().toString());
                registerDTO.setState(state.getText().toString());
                registerDTO.setAge(age.getText().toString());
                registerDTO.setCredit(User.getInstance().getCredit());
                if (validateUserInput()) {
                    AsyncUpdateUser asyncUpdateUser = new AsyncUpdateUser();
                    asyncUpdateUser.execute();
                } else {
                    Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
                }
            }
        });

        // Updating the action bar title
        //getActivity().getActionBar().setTitle(rivers[position]);

        return v;
    }

    boolean validateUserInput() {
        if (registerDTO.getUsername().equals(null) || registerDTO.getUsername().equals("") || registerDTO.getPassword().equals(null) || registerDTO.getPassword().equals("") || registerDTO.getMobileNo().equals(null) || registerDTO.getMobileNo().equals("")) {
            message = "Please fill all required fields.";
            return false;
        } else if (!registerDTO.getPassword().equals(confirmPassword.getText().toString())) {
            message = "Password and Confirm Password don't match.";
            return false;
        } else if (!isValidMail(registerDTO.getUsername())) {
            message = "Email ID is not valid.";
            return false;
        } else if (!isValidMobile(registerDTO.getMobileNo())) {
            message = "Please enter valid 10 digit mobile no.";
            return false;
        } else {
            return true;
        }
    }

    private boolean isValidMail(String email2) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email2);
        check = m.matches();


        return check;
    }

    private boolean isValidMobile(String phone2) {
        boolean check;
        check = phone2.length() == 10;
        return check;
    }

    private class AsyncUpdateUser extends AsyncTask<String, String, String> {
        ProgressDialog Asycdialog = new ProgressDialog(getActivity());

        @Override
        protected String doInBackground(String... arg0) {
            AuthenticationInterface authenticationInterface = new AuthenticationService();
            authentic = authenticationInterface.doRegister(registerDTO).getIsAuthentic();
            Thedb.getInstance(getActivity()).logOut(User.getInstance().getUserId());
            Thedb.getInstance(getActivity()).addUser(User.getInstance());
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Asycdialog.setMessage(getResources().getString(
                    R.string.UPDATING_INFO));
            Asycdialog.setCanceledOnTouchOutside(false);
            // show dialog
            Asycdialog.show();
            registerDTO = new RegisterDTO();
            registerDTO.setUsername(emailAddress.getText().toString());
            registerDTO.setPassword(password.getText().toString());
            registerDTO.setMobileNo(mobileNo.getText().toString());
            registerDTO.setEmailAddress(emailAddress.getText().toString());

            registerDTO.setAddress(address.getText().toString());
            registerDTO.setLocality(locality.getText().toString());
            registerDTO.setCity(city.getText().toString());
            registerDTO.setState(state.getText().toString());
            registerDTO.setAge(age.getText().toString());
            registerDTO.setCredit(User.getInstance().getCredit());
        }

        protected void onPostExecute(String transactionStatus) {
            Asycdialog.hide();
            if (authentic) {
                Toast.makeText(getActivity(), "Information Updated", Toast.LENGTH_LONG).show();
            }

        }
    }
}
