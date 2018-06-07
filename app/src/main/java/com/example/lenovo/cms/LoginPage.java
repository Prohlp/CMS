package com.example.lenovo.cms;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginPage extends AppCompatActivity {
    private EditText emailID;
    private EditText _password;
    private TextView Login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //refernce all
        firebaseauth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        emailID=(EditText)findViewById(R.id.email);
        _password=(EditText)findViewById(R.id.Password);
        Login=(TextView)findViewById(R.id.Login);
        //then listener to text button
        //_Register.setOnClickListener(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUserHere();
            }
        });

    }
    private void LoginUserHere(){
        String em=emailID.getText().toString();
        String pass=_password.getText().toString();
        if(TextUtils.isEmpty(em)){
            //email field is empty
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            //Password field is empty
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_LONG).show();
            return;
        }
        //User will register here
        progressDialog.setMessage("Loading...Please Wait");
        progressDialog.show();
        //Login via Email and Password
        firebaseauth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(this, new
                OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginPage.this,"LoginSuccessful !",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }else{
                            FirebaseAuthException e=(FirebaseAuthException)task.getException();
                            Toast.makeText(LoginPage.this,"Failed Login:"+e.getMessage(),Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });

    }

}
