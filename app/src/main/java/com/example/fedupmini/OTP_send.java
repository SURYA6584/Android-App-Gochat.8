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

import com.example.fedupmini.databinding.ActivityOtpSendBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP_send extends AppCompatActivity {


    private ActivityOtpSendBinding binding;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otp_send);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //To avoid ID CASCADING
        binding =ActivityOtpSendBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        binding.PF165.setVisibility(View.INVISIBLE);


        binding.BF165.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(binding.EF165.getText().toString().trim().isEmpty() ){
                    Toast.makeText(OTP_send.this , "Invalid Phone Number",Toast.LENGTH_SHORT).show();
                }
                else
                if(binding.EF165.getText().toString().trim().length()!=10){

                    Toast.makeText(OTP_send.this , "Invalid Phone Number",Toast.LENGTH_SHORT).show();

                }
                else{

                    sendOTP();
                }

            }
        });





    }

    public void sendOTP(){

        binding.PF165.setVisibility(View.VISIBLE);
        binding.BF165.setVisibility(View.INVISIBLE);

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                binding.PF165.setVisibility(View.GONE);
                binding.BF165.setVisibility(View.VISIBLE);
                Toast.makeText(OTP_send.this , e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                binding.PF165.setVisibility(View.GONE);
                binding.BF165.setVisibility(View.VISIBLE);
                Toast.makeText(OTP_send.this , "OTP sent Succesfully",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OTP_send.this, OTP_varification.class);
                intent.putExtra("Phone" ,binding.EF165.getText().toString().trim());
                intent.putExtra("verificationId",verificationId);
                startActivity(intent);

            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + binding.EF165.getText().toString().trim())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

}