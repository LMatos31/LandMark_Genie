package com.app.landmark_genie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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


public class Register extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth mAuth;
    EditText fullname, pass, email;
    Button reg;
    Button back;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        logo = (ImageView) findViewById(R.id.logo2);
        logo.setOnClickListener(this);

        reg = (Button) findViewById(R.id.btnReg2);
        reg.setOnClickListener(this);

        back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(this);

        fullname = (EditText) findViewById(R.id.txtName);
        pass = (EditText) findViewById(R.id.txtPass2);
        email = (EditText) findViewById(R.id.txtEmail2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logo2:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnBack:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btnReg2:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String name = fullname.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String eml = email.getText().toString().trim();

        if(name.isEmpty()){
            fullname.setError("Fullname is required!");
            fullname.requestFocus();
            return;
        }

        if(password.isEmpty()){
            pass.setError("Password is required!");
            pass.requestFocus();
            return;
        }

        if(password.length() < 6){
            pass.setError("Password must be longer that 6 characters!");
            pass.requestFocus();
            return;
        }

        if(eml.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(eml, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User has been registered!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(Register.this, "Failed to register, Try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}