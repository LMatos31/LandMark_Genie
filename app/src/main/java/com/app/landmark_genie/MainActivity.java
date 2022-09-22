package com.app.landmark_genie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

//References: Tutlane.com. 2021. Android ListView with Examples - Tutlane. [online] Available at: <https://www.tutlane.com/tutorial/android/android-listview-with-examples> [Accessed 26 May 2021].
//References: Stack Overflow. 2021. take a picture and save it using android studio. [online] Available at: <https://stackoverflow.com/questions/40117332/take-a-picture-and-save-it-using-android-studio> [Accessed 26 May 2021].
//References: Stack Overflow. 2021. How to save user input from EditText to a variable to be used on a different Activity. [online] Available at: <https://stackoverflow.com/questions/31090558/how-to-save-user-input-from-edittext-to-a-variable-to-be-used-on-a-different-act> [Accessed 26 May 2021].
//References: Professor DK, 2021. Simple Login App Tutorial Using Android Studio 2.3.3 (NEW). [video] Available at: <https://www.youtube.com/watch?v=lF5m4o_CuNg> [Accessed 26 May 2021].
//References: 2021. CodeWithMazn. [video] Available at: <https://www.youtube.com/c/CodeWithMazn/videos> [Accessed 3 July 2021].
//References: 2021. Educatree. [video] Available at: <https://www.youtube.com/watch?v=iy6WexahCdY> [Accessed 3 July 2021].
//References: Studio, R., Quinter, J., Mamo, A., Berenguer, J. and yash, t., 2021. Retrieve Firebase data to ListView in Android Studio. [online] Stack Overflow. Available at: <https://stackoverflow.com/questions/52167162/retrieve-firebase-data-to-listview-in-android-studio> [Accessed 3 July 2021].


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email, password;
    Button login;
    Button reg;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        reg = (Button) findViewById(R.id.btnReg);
        reg.setOnClickListener(this);

        login = (Button) findViewById(R.id.btnLogin);
        login.setOnClickListener(this);

        email = (EditText) findViewById(R.id.txtEmail);
        password = (EditText) findViewById(R.id.txtPass);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.btnLogin:
                userLogin();
                break;
        }

    }

    private void userLogin() {
        String eml = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if(eml.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if(pass.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if(pass.length() < 6){
            password.setError("Password must be longer that 6 characters!");
            password.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(eml, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(MainActivity.this, Map.class));
                }else{
                    Toast.makeText(MainActivity.this, "Failed to Login, Try again!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}