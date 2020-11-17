package th.ac.su.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import th.ac.su.speedrecords.db.AppDatabase;
import th.ac.su.speedrecords.model.User;
import th.ac.su.speedrecords.util.AppExecutors;

public class AddUserActivity extends AppCompatActivity {
    public EditText distance,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        distance = findViewById(R.id.distance_text);
        time = findViewById(R.id.time_text);

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double distanceTextDouble = Double.parseDouble(distance.getText().toString());
                double timeTextDouble = Double.parseDouble(time.getText().toString());
                double resultDoubleSpeed = distanceTextDouble / timeTextDouble * 3.6;
                boolean over = false;

                String resultSpeed = String.format(
                        Locale.getDefault(), "%.1f", resultDoubleSpeed
                );
                String resultDist = String.format(
                        Locale.getDefault(), "%.1f", distanceTextDouble
                );
                String resultTime = String.format(
                        Locale.getDefault(), "%.1f", timeTextDouble
                );

                if(resultDoubleSpeed > 80)
                    over = true;

                final User user = new User(0, Double.parseDouble(resultSpeed),
                        Double.parseDouble(resultDist),
                        Double.parseDouble(resultTime),over);

                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() { // worker thread
                        AppDatabase db = AppDatabase.getInstance(AddUserActivity.this);
                        db.userDao().addUser(user);

                    }
                });
            }
        });

        /*Button test = findViewById(R.id.test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(AddUserActivity.this);
                dialog.setTitle(R.string.app_name);
                double distanceTextDouble = Double.parseDouble(distance.getText().toString());
                double timeTextDouble = Double.parseDouble(time.getText().toString());
                double resultDouble = distanceTextDouble / timeTextDouble * 3.6;

                String resultStr = String.format(
                        Locale.getDefault(), "%.1f", resultDouble
                );
                dialog.setMessage(String.valueOf(resultStr));
                dialog.setPositiveButton("ํYES",null);


                dialog.show();
            }
        });*/
    }
}