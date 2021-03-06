package com.example.msg.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.msg.MainActivity;
import com.example.msg.R;
import com.example.msg.ResMainActivity;
import com.example.msg.SignUp.SelectSignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText id;
    private EditText password;

    private Button login;
    private Button signup;
    private FirebaseRemoteConfig FirebaseRemoteConfig;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseRemoteConfig =FirebaseRemoteConfig.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        id=(EditText)findViewById(R.id.loginActivity_edittext_id);
        password=(EditText)findViewById(R.id.loginActivity_edittext_password);


        login=(Button)findViewById(R.id.loginActivity_button_login);
        signup =(Button)findViewById(R.id.loginActivity_button_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loginEvent();
                }catch (Exception e){

                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SelectSignupActivity.class));
            }
        });

        //????????? ??????????????? ?????????
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    //?????????

                    FirebaseFirestore db=FirebaseFirestore.getInstance();
                                //?????? user ??????
                                DocumentReference docRef=db.collection("User").document(user.getUid());
                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful()){
                                DocumentSnapshot document=task.getResult();
                                if(document.exists()){
                                    Log.d(TAG,"user "+document.getData());

                                    if((Boolean)document.getData().get("sanction")){
                                        Log.d(TAG,"The user is suspended");
                                        Toast.makeText(getApplicationContext(), "?????? ???????????? ????????? ??????????????????.", Toast.LENGTH_LONG).show();
                                        FirebaseAuth.getInstance().signOut();
                                    }
                                    else{
                                        Log.d(TAG,"user login success");
                                        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                                else {
                                    //?????? ???????????????
                                    Log.d(TAG, "Go restuser");
                                }
                            }
                            else{
                                Log.d(TAG,"User error");
                            }
                        }
                    });

                    //restaurant_user ??????
                    docRef=db.collection("Restaurant").document(user.getUid());
                    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot document=task.getResult();
                                if(document.exists()){
                                    Log.d(TAG,"Restuser "+document.getData());
                                    if((Boolean) document.getData().get("approved")){
                                        Log.d(TAG,"approved Restuser");
                                        Intent intent=new Intent(LoginActivity.this, ResMainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else{
                                        Log.d(TAG,"not yet approved Restuser");
                                        Toast.makeText(getApplicationContext(), "?????? ???????????? ?????? ??????????????????. ????????? ?????????????????? ?????? ????????????????????????.", Toast.LENGTH_LONG).show();
                                        FirebaseAuth.getInstance().signOut();
                                    }

                                }
                                else
                                    Log.d(TAG,"No such user");
                            }
                            else{
                                Log.d(TAG,"RestaurantUser error");
                            }
                        }
                    });

                }else{
                    //????????????
                }
            }
        };




    }



    void loginEvent(){
        firebaseAuth.signInWithEmailAndPassword(id.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {    //???????????? ??????????????? ?????????????????? ?????? (????????? ??? ?????? ???????????? ???????????? ????????? ??????)
                if(!task.isSuccessful()){
                    //????????? ??????
                    Toast.makeText(LoginActivity.this,"?????? ????????? ????????????",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(authStateListener);
    }


}
