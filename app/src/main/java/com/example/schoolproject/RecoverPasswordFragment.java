package com.example.schoolproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// The screen where the user can recover their password in case they forgot it
public class RecoverPasswordFragment extends Fragment {
    private static final int SEND_SMS = 0;
    Button btLoginFragment, btSendSMS;
    EditText etUserName;
    TextView tvError;

    public RecoverPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recover_password, container, false);

        checkPermission(Manifest.permission.SEND_SMS, SEND_SMS);

        btLoginFragment = view.findViewById(R.id.btLoginFragment);
        etUserName = view.findViewById(R.id.etUserName);
        btSendSMS = view.findViewById(R.id.btSendSMS);
        tvError = view.findViewById(R.id.tvError);

        HelperDB helperDB =  new HelperDB(getActivity());

        // Navigates to the Login screen when the button is pressed
        btLoginFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new LoginFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Sends an SMS to the user containing their username and password the button is pressed
        btSendSMS.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString();
                User user = helperDB.getRecord(userName);
                if (user != null) {
                    String stMessage = "Username: " + userName + ", Password: " + user.getUserPwd();
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(user.getUserPhone(), null, stMessage,
                            null, null);
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, new LoginFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else
                    tvError.setText("User does not exist");
            }
        });

        return view;
    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(getActivity(), permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(getActivity(), new String[] { permission }, requestCode);
        }
    }
}