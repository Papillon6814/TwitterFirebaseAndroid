package com.example.fbapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.OAuthProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        OAuthProvider.Builder provider = OAuthProvider.newBuilder("twitter.com");

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Task<AuthResult> pendingResultTask = firebaseAuth.getPendingAuthResult();
        if (pendingResultTask != null) {
            // There's something already here! Finish the sign-in for your user.
            pendingResultTask
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    // User is signed in.
                                    // IdP data available in
                                    // authResult.getAdditionalUserInfo().getProfile().
                                    // The OAuth access token can also be retrieved:
                                    // authResult.getCredential().getAccessToken().
                                    // The OAuth secret can be retrieved by calling:
                                    // authResult.getCredential().getSecret().
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure.
                                }
                            });
        } else {
            // There's no pending result so you need to start the sign-in flow.
            // See below.

            firebaseAuth
                    .startActivityForSignInWithProvider(/* activity= */ this, provider.build())
                    .addOnSuccessListener(
                            new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    // User is signed in.
                                    // IdP data available in
                                    // authResult.getAdditionalUserInfo().getProfile().
                                    // The OAuth access token can also be retrieved:
                                    // authResult.getCredential().getAccessToken().
                                    // The OAuth secret can be retrieved by calling:
                                    // authResult.getCredential().getSecret().
                                    Context context = getApplicationContext();
                                    Toast.makeText(context , "????????????????????????", Toast.LENGTH_LONG).show();
                                }
                            })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure.
                                }
                            });

        }
    }
}