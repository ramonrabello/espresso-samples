package com.github.ramonrabello.helloespresso;

import android.content.DialogInterface;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Login screen related to user authentication.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameField;
    private EditText passwordField;
    private TextView authenticationStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        authenticationStatus = (TextView) findViewById(R.id.authentication_status);
    }

    public void login(View view){

        if (areUserCredentialsEmpty()){

            showErrorDialog();

        } else {

           setAuthenticationStatus(areUserCredentialsCorrect());
        }
    }

    private boolean areUserCredentialsEmpty() {
        return usernameField.getText().toString().equals("") ||
                passwordField.getText().toString().equals("");
    }

    private boolean areUserCredentialsCorrect() {
        return usernameField.getText().toString().equals("espresso") &&
                passwordField.getText().toString().equals("35pr3550");
    }

    private void setAuthenticationStatus(boolean areUserCredentialsValid){
        authenticationStatus.setTextColor(areUserCredentialsValid ? ContextCompat.getColor(this, R.color.green_500) : ContextCompat.getColor(this, R.color.red_500));
        authenticationStatus.setText(areUserCredentialsValid ? getString(R.string.user_signed_in) : getString(R.string.invalid_account));
        authenticationStatus.setVisibility(View.VISIBLE);
    }

    private void showErrorDialog() {
        AlertDialog alertDialog = new AlertDialog
                .Builder(this)
                .setTitle(getString(R.string.error_dialog_title))
                .setMessage(getString(R.string.error_dialog_message)).setPositiveButton(getString(R.string.error_dialog_close_button), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        alertDialog.show();
    }

}