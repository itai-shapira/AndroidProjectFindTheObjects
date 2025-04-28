package com.example.schoolproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.schoolproject.ml.ModelUnquant;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class PhotoFragment extends Fragment {

    Button btCamera, btGameFragment;
    ImageView ivPhoto;
    TextView tvResult, tvConfidence, tvFound;
    ActivityResultLauncher<Intent> arLauncher;
    final int IMAGE_SIZE = 224;

    public PhotoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_photo, container, false);

        ActivityCompat.requestPermissions(getActivity(),

                new String[]{android.Manifest.permission.CAMERA,

                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,

                        android.Manifest.permission.READ_EXTERNAL_STORAGE},

                1);

        btCamera = view.findViewById(R.id.btCamera);
        ivPhoto = view.findViewById(R.id.ivPhoto);
        btGameFragment = view.findViewById(R.id.btGameFragment);
        tvResult = view.findViewById(R.id.tvResult);
        tvConfidence = view.findViewById(R.id.tvConfidence);
        tvFound = view.findViewById(R.id.tvFound);

        HelperDB helperDB = new HelperDB(getActivity());

        Context context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPreferences", MODE_PRIVATE);
        User currentUser = helperDB.getRecord(sharedPreferences.getString("username", "DefaultName"));

        String stFound = "Found: " + currentUser.getFoundObjectsString();
        tvFound.setText(stFound);

        // Creating the ActivityResultLauncher (takes a photo, classifies it using AI, and updates the database according to the results)
        arLauncher = registerForActivityResult(

                new ActivityResultContracts.StartActivityForResult(),

                new ActivityResultCallback<ActivityResult>() {

                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Intent data = result.getData();
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        ivPhoto.setImageBitmap(bitmap);
                        bitmap = Bitmap.createScaledBitmap(bitmap, IMAGE_SIZE, IMAGE_SIZE, false);
                        int max = classifyImage(bitmap);

                        // Updates the amount of objects the user has found to the database
                        String userName = currentUser.getUserName();
                        /*
                        String userFoundObjects = currentUser.getUserFoundObjects();
                        String newUserFoundObjects = "";

                        for (int i = 0; i < userFoundObjects.length(); i++) {
                            if (i == max)
                                newUserFoundObjects += "1";
                            else
                                newUserFoundObjects += "0";
                        }

                        // Inserts the new data to the database
                        ContentValues cv = new ContentValues();

                        cv.put(helperDB.USER_NAME, currentUser.getUserName());
                        cv.put(helperDB.USER_PWD, currentUser.getUserPwd());
                        cv.put(helperDB.USER_PHONE, currentUser.getUserPhone());
                        cv.put(helperDB.USER_FOUND_OBJECTS, newUserFoundObjects);
                        helperDB.updateRow(helperDB.getAllRecords().indexOf(helperDB.getRecord(userName)) + 1, cv);
                        */

                        String stFound = "Found: " + helperDB.getRecord(userName).getFoundObjectsString();
                        tvFound.setText(stFound);
                    } });

        // Navigates to the Game screen when the button is pressed
        btGameFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new GameFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Takes a photo using the phone's camera
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                arLauncher.launch(intent);
            }});

        return view;
    }

    @SuppressLint("DefaultLocale")
    private int classifyImage(Bitmap bitmap) {
        try {
            ModelUnquant model = ModelUnquant.newInstance(getActivity().getApplicationContext());

            // Create inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 224, 224, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * IMAGE_SIZE * IMAGE_SIZE * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            int[] intValues = new int[IMAGE_SIZE*IMAGE_SIZE];
            bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            int pixel = 0;
            for (int i=0;  i<IMAGE_SIZE; i++) {
                for (int j = 0; j < IMAGE_SIZE; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255.f));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255.f));
                }
            }
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            ModelUnquant.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            float[] confidences = outputFeature0.getFloatArray();
            int max = 0;
            float maxConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    max = i;
                }
            }
            String[] classes = {"Backpack", "Battery", "Calculator", "Charger", "Glasses", "Notebook", "Pencil", "Plastic Bottle", "Shoe", "Umbrella"};
            tvResult.setText(classes[max]);
            tvConfidence.setText(String.format("%.1f%%", confidences[max] * 100));
            // Releases model resources if no longer used.
            model.close();
            return max;

        } catch (IOException e) {
            return 0;
        }
    }
}