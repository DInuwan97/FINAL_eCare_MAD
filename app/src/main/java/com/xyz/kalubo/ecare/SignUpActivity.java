package com.xyz.kalubo.ecare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText editName,editEmail,editPassword,editConfirmPaswoird;
    Button btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myDb = new DataBaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_username);
        editEmail = (EditText)findViewById(R.id.editText_email);
        editPassword = (EditText)findViewById(R.id.editText_pwrd);
        editConfirmPaswoird = (EditText)findViewById(R.id.editText_cpwrd);

        btnSignUp = (Button) findViewById(R.id.button_signup);

        AddPatient();
    }

    public void AddPatient(){
        btnSignUp.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                       boolean isInserted =  myDb.insertData(editName.getText().toString(),
                                        editEmail.getText().toString(),
                                        editPassword.getText().toString(),
                                        editConfirmPaswoird.getText().toString());

                       if(isInserted == true)
                           Toast.makeText(SignUpActivity.this,"Sucessfully Registered!!!",Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(SignUpActivity.this,"Registration is Denied!!!",Toast.LENGTH_LONG).show();
                    }

                }
        );


    }

    public void showSignInActivity(View view){
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
    }
}
