package com.example.jere.garbageapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jere.garbageapp.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends BaseFragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_email,et_password,et_name,et_house;
    private TextView tv_login;
    private ProgressBar progress;
    private MaterialBetterSpinner et_estate,et_location;
    private static final String TAG = "SignupActivity";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        et_name = (EditText)view.findViewById(R.id.et_name);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_house=(EditText)view.findViewById(R.id.et_house);
        et_estate=(MaterialBetterSpinner)view.findViewById(R.id.fragment_register_estate);
        et_location=(MaterialBetterSpinner)view.findViewById(R.id.fragment_register_location);
        et_password = (EditText)view.findViewById(R.id.et_password);

        List<String> mylocations= new ArrayList<>();
        mylocations.add("Langata");
        mylocations.add("Nairobi West");
        mylocations.add("StrathMore");
        mylocations.add("HighRise");

        ArrayAdapter<String> locationadapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mylocations);
        locationadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_location.setAdapter(locationadapter);


        List<String> myestates = new ArrayList<>();
        myestates.add("Funguo");
        myestates.add("Akila");
        myestates.add("Airport View");
        myestates.add("Blue Sky");
        myestates.add("Siwaka Estate");

        ArrayAdapter<String> estateadapter= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, myestates);
        estateadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        et_estate.setAdapter(estateadapter);


        btn_register = (AppCompatButton)view.findViewById(R.id.btn_register);
        tv_login = (TextView)view.findViewById(R.id.tv_login);

        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_login:
                goToLogin();
                break;

            case R.id.btn_register:
                signup();
                break;
        }

    }

    public void signup() {
//    Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        else if (!isNetworkConnected()){
            Snackbar.make(getView(),"No Network Connection.Turn on Your Wifi", Snackbar.LENGTH_LONG).show();
        }
        else {
            btn_register.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();

            String name= et_name.getText().toString();
            String email= et_email.getText().toString();
            String house=et_house.getText().toString();
            String estate=et_estate.getText().toString();
            String location=et_location.getText().toString();
            String pass= et_password.getText().toString();
            String function = "register";

            BackgroundTasks backgroundTasks =new BackgroundTasks(getContext());
            backgroundTasks.execute(function,name,email,house,estate,location,pass);
            Log.d("background","Submitted details");

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onSignupSuccess();
                            // onSignupFailed();
                            progressDialog.dismiss();
                        }
                    }, 1500);
        }


    }


    public boolean validate() {
        boolean valid = true;

        String name= et_name.getText().toString();
        String email= et_email.getText().toString();
        String house=et_house.getText().toString();
        String estate=et_estate.getText().toString();
        String location=et_location.getText().toString();
        String password= et_password.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            et_name.setError("at least 3 characters");
            valid = false;
        } else {
            et_name.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("enter a valid email address");
            valid = false;
        } else {
            et_email.setError(null);
        }

        if (house.isEmpty() || house.length() < 3) {
            et_name.setError("at least 3 characters");
            valid = false;
        } else {
            et_house.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            et_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            et_password.setError(null);
        }

        return valid;
    }

    public void onSignupFailed() {
        Snackbar.make(getView(), "Registration was not successful !", Snackbar.LENGTH_LONG).show();
    }

    public void onSignupSuccess() {
        btn_register.setEnabled(true);
        et_name.setText("");
        et_email.setText("");
        et_house.setText("");
        et_password.setText("");
        //goToLogin();

    }

    public void goToLogin(){
        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_activity_container,login);
        ft.commit();
    }
}