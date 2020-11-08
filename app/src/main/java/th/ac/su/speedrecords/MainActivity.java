package th.ac.su.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import th.ac.su.speedrecords.adapter.UserAdapter;
import th.ac.su.speedrecords.db.AppDatabase;
import th.ac.su.speedrecords.model.User;
import th.ac.su.speedrecords.util.AppExecutors;

public class MainActivity extends AppCompatActivity {
    private Button addBtn;
    private RecyclerView mRecyclerView;
    public TextView totalText,overText;



    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final User[] users = db.userDao().getAllUsers();
                final int count = db.userDao().count();
                final int countOver = db.userDao().countOver(true);

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        totalText.setText("TOTAL: "+String.valueOf(count));
                        overText.setText("OVER LIMIT: "+String.valueOf(countOver));
                        UserAdapter adapter = new UserAdapter(MainActivity.this, users);
                        mRecyclerView.setAdapter(adapter);
                    }
                });




            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        totalText = findViewById(R.id.total_text);
        overText = findViewById(R.id.over_text);


         addBtn = findViewById(R.id.add_btn);
         addBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(MainActivity.this, AddUserActivity.class);
                 startActivity(i);
             }
         });

    }
}