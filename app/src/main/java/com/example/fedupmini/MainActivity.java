package com.example.fedupmini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fedupmini.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    String username,collage , email,location,number,cur_add;
    int usercount=0;
    FirebaseDatabase DB;
    String human=" ";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.B4265.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(MainActivity.this , maps.class);
                startActivity(intent);
            }
        });


        Intent i =getIntent();
        cur_add =i.getStringExtra("CUR_Address");
        binding.E4465.setText(cur_add);
        location=binding.E4465.getText().toString();

        binding.B4165.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = binding.E4165.getText().toString();
                collage=binding.E4265.getText().toString();
                email= binding.E4365.getText().toString();
                number =binding.E4565.getText().toString();
                if(!username.isEmpty()&&!collage.isEmpty()&&!email.isEmpty()){
                    usercount++;

                    USERS users = new USERS(username,collage,email,location,number);
                    DB =FirebaseDatabase.getInstance();
                    reference = DB.getReference("HUMANS");
                    human = "human"+usercount;

                    reference.child(human).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            binding.E4165.setText("");
                            binding.E4265.setText("");
                            binding.E4365.setText("");
                            binding.E4465.setText("");
                            binding.E4565.setText("");
                            Toast.makeText(MainActivity.this, "Successfuly Updated" , Toast.LENGTH_SHORT).show();


                        }
                    });

                }
            }






        });




    }
}