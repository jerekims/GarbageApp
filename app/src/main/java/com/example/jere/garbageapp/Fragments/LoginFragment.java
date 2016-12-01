package com.example.jere.garbageapp.Fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jere.garbageapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;
    private TextView tv_register;
    private SharedPreferences pref;
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);


        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_register:
                goToRegister();
                break;

            case R.id.btn_login:
                login();

//                if(!validate(email,password)) {
//
//                } else {
//
//                    Snackbar.make(getView(), "Fields are empty !", Snackbar.LENGTH_LONG).show();
//                }
//                break;

        }
    }
    public void login() {
        Log.d(TAG, "Login");

        if (!validate()){
            onLoginFailed();
            return;
        }

        btn_login.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == REQUEST_SIGNUP) {
//            if (resultCode == RESULT_OK) {
//
//                // TODO: Implement successful signup logic here
//                // By default we just finish the Activity and log them in automatically
//                this.finish();
//            }
//        }
//    }

//    @Override
//    public void onBackPressed() {
//        // disable going back to the MainActivity
//        getActivity().onBackPressed();(true);
//    }

    public void onLoginSuccess() {
        btn_login.setEnabled(true);
        getActivity().finish();
    }

    public void onLoginFailed() {
        Snackbar.make(getView(), "Login failed !", Snackbar.LENGTH_LONG).show();
        btn_login.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("enter a valid email address");
            valid = false;
        } else {
            et_email.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            et_password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            et_password.setError(null);
        }

        return valid;
    }

//    private void loginProcess(String email,String password){
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        RequestInterface requestInterface = retrofit.create(RequestInterface.class);
//
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        ServerRequest request = new ServerRequest();
//        request.setOperation(Constants.LOGIN_OPERATION);
//        request.setUser(user);
//        Call<ServerResponse> response = requestInterface.operation(request);
//
//        response.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
//
//                ServerResponse resp = response.body();
//                Snackbar.make(getView(), resp.getMessage(), Snackbar.LENGTH_LONG).show();
//
//                if(resp.getResult().equals(Constants.SUCCESS)){
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
//                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
//                    editor.putString(Constants.NAME,resp.getUser().getName());
//                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
//                    editor.apply();
//                    goToProfile();
//
//                }
//                progress.setVisibility(View.INVISIBLE);
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//
//                progress.setVisibility(View.INVISIBLE);
//                Log.d(Constants.TAG,"failed");
//                Snackbar.make(getView(), t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
//
//            }
//        });
//    }

    private void goToRegister(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_activity_container,new RegisterFragment());
        ft.commit();
    }

    private void goToProfile(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_activity_container,new ProfileFragment());
        ft.commit();
    }
}
