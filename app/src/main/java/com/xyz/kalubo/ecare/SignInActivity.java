package com.xyz.kalubo.ecare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    EditText editText_email,editText_password;
    Button button_signIn;
    TextView mTextViewRegister;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = new DataBaseHelper(this);

        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_password = (EditText) findViewById(R.id.editText_password);

        button_signIn = (Button) findViewById(R.id.button_signIn);

        button_signIn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String user_email = editText_email.getText().toString();
                String user_password = editText_password.getText().toString();

                Boolean res = db.checkUser(user_email,user_password);

                if(res == true){
                    Toast.makeText(SignInActivity.this,"Successfullt Logged In!!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignInActivity.this,"Invalid Emial or Password!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}
