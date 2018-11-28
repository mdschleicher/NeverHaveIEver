package edu.csula.cs.neverhaveiever;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import edu.csula.cs.neverhaveiever.database.NeverHaveIEverViewModel;
import edu.csula.cs.neverhaveiever.models.User;


public class MainActivity extends AppCompatActivity {

    private TextInputEditText name;
    private TextInputEditText image;

    private NeverHaveIEverViewModel neverHaveIEverViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        name = findViewById(R.id.name);
        image = findViewById(R.id.image);

        neverHaveIEverViewModel = ViewModelProviders.of(this).get(NeverHaveIEverViewModel.class);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                neverHaveIEverViewModel.CreateUser(name.getText().toString(), image.getText().toString());
            }
        });

        neverHaveIEverViewModel.getAllUser().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> user) {
                if (user.size() > 0 ) {
                    Log.d("SOMETHING_UNIQUE", user.get(0).getName());
                    Log.d("SOMETHING_UNIQUE", Integer.toString(user.size()));
                }
            }
        });

    }

}
