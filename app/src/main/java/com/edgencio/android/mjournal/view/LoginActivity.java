package com.edgencio.android.mjournal.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.edgencio.android.mjournal.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private SignInButton btnSignIn;
    ProgressDialog mProgressDialog;
    private static int RC_SIGN_IN=007;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        initGoogleSignIn();
    }



    private void signIn() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Signing In. Please Wait");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        Intent sigInIntent= Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(sigInIntent,RC_SIGN_IN);
    }

    public void initGoogleSignIn() {
        btnSignIn=(SignInButton)findViewById(R.id.google_sign_in_button);
        btnSignIn.setOnClickListener(this);

        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.google_sign_in_button:
                signIn();
                break;

        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Toast.makeText(this,"Connection Failed",Toast.LENGTH_LONG).show();
    }




    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("ERROR", result.getStatus().toString());
        Log.e("ERROR", result.isSuccess()+"");
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Bundle bundle = new Bundle();
            bundle.putString("username", personName);
            bundle.putString("email", email);


            mProgressDialog.dismiss();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        } else {
            mProgressDialog.dismiss();
            Toast.makeText(this,"Error during Login",Toast.LENGTH_LONG).show();
        }
    }

}



