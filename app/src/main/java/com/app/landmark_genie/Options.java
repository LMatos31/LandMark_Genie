package com.app.landmark_genie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//References: Tutlane.com. 2021. Android ListView with Examples - Tutlane. [online] Available at: <https://www.tutlane.com/tutorial/android/android-listview-with-examples> [Accessed 26 May 2021].
//References: Stack Overflow. 2021. take a picture and save it using android studio. [online] Available at: <https://stackoverflow.com/questions/40117332/take-a-picture-and-save-it-using-android-studio> [Accessed 26 May 2021].
//References: Stack Overflow. 2021. How to save user input from EditText to a variable to be used on a different Activity. [online] Available at: <https://stackoverflow.com/questions/31090558/how-to-save-user-input-from-edittext-to-a-variable-to-be-used-on-a-different-act> [Accessed 26 May 2021].
//References: Professor DK, 2021. Simple Login App Tutorial Using Android Studio 2.3.3 (NEW). [video] Available at: <https://www.youtube.com/watch?v=lF5m4o_CuNg> [Accessed 26 May 2021].


public class Options extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button signOut;
    Button save;
    Button add;
    Button filter;
    EditText metric, land, fav;
    landmark landmark;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ref = FirebaseDatabase.getInstance().getReference().child("Landmark");

        metric = (EditText) findViewById(R.id.txtMetric);
        land = (EditText) findViewById(R.id.txtLand);
        fav = (EditText) findViewById(R.id.txtFav);

        signOut = (Button) findViewById(R.id.btnSignOut2);
        signOut.setOnClickListener(this);

        save = (Button) findViewById(R.id.btnSave);
        add.setOnClickListener(this);

        filter = (Button) findViewById(R.id.btnFilter);
        add.setOnClickListener(this);

        add = (Button) findViewById(R.id.btnAdd2);
        add.setOnClickListener(this);

        landmark = new landmark();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSignOut2:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.btnSave:
                landMetric();
                break;

            case R.id.btnFilter:
                landMark();
                break;

            case R.id.btnAdd2:
                landFav();
                break;
        }

    }

    private void landMetric() {
        String met = metric.getText().toString().trim();
        landmark.setMetric(met);
        ref.push().setValue(landmark);
        Toast.makeText(Options.this, "Metric settings have been saved!", Toast.LENGTH_LONG).show();
    }

    private void landMark() {
        String lnd = land.getText().toString().trim();
        landmark.setLandMark(lnd);
        ref.push().setValue(landmark);
        Toast.makeText(Options.this, "Landmark has been filtered!", Toast.LENGTH_LONG).show();
    }

    private void landFav() {
        String frt = fav.getText().toString().trim();
        landmark.setLandMark(frt);
        ref.push().setValue(landmark);
        Toast.makeText(Options.this, "Favorite Landmark has been added to database!", Toast.LENGTH_LONG).show();
    }

}