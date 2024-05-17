package com.example.fedupmini;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.fedupmini.databinding.ActivityOtpVarificationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class OTP_varification extends AppCompatActivity {

    private ActivityOtpVarificationBinding binding;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_varification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding =ActivityOtpVarificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //autimatic input movement in editext
        editTextInputmove();



        binding.TS465.setText(String.format("+91-%s", getIntent().getStringExtra("Phone") ));
        binding.PS165.setVisibility(View.INVISIBLE);

        verificationId =getIntent().getStringExtra("verificationId");

        //RESENT_OTP
        binding.TS365.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OTP_varification.this , "OTP Sent Succefull." ,Toast.LENGTH_SHORT).show();



            }
        });

        binding.BS165.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.PS165.setVisibility(View.VISIBLE);
                binding.BS165.setVisibility(View.INVISIBLE);
                if(binding.ES165.getText().toString().trim().isEmpty()||
                        binding.ES265.getText().toString().trim().isEmpty()||
                        binding.ES365.getText().toString().trim().isEmpty()||
                        binding.ES465.getText().toString().trim().isEmpty()||
                        binding.ES565.getText().toString().trim().isEmpty()||
                        binding.ES665.getText().toString().trim().isEmpty()){
                    binding.PS165.setVisibility(View.GONE);
                    binding.BS165.setVisibility(View.VISIBLE);
                    Toast.makeText(OTP_varification.this , "OTP is not valid" ,Toast.LENGTH_SHORT).show();


                }else {
                    if(verificationId!=null) {
                        String enteredotp =  binding.ES165.getText().toString().trim()+
                                binding.ES265.getText().toString().trim()+
                                binding.ES365.getText().toString().trim()+
                                binding.ES465.getText().toString().trim()+
                                binding.ES565.getText().toString().trim()+
                                binding.ES665.getText().toString().trim();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, enteredotp);
                        FirebaseAuth.getInstance()
                                .signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if(task.isSuccessful()){
                                            binding.PS165.setVisibility(View.GONE);
                                            binding.BS165.setVisibility(View.VISIBLE);
                                            Toast.makeText(OTP_varification.this , "OTP varified succesfuly",Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(OTP_varification.this , MainActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }else{

                                            Toast.makeText(OTP_varification.this , "OTP is not valid" ,Toast.LENGTH_SHORT).show();

                                        }





                                    }
                                });

                    }



                }

            }
        });







    }
    private void editTextInputmove(){

        binding.ES165.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.ES265.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.ES265.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.ES365.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.ES365.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.ES465.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.ES465.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.ES565.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.ES565.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                binding.ES665.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }


}