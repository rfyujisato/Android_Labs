package algonquin.cst2335.rafaelsandroidlabs;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding variableBinding;
    ActivityResultLauncher<Intent> cameraResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        variableBinding.profileImage.setImageBitmap(thumbnail);
                        //
                        FileOutputStream fOut = null;
                        try { fOut = openFileOutput("Picture.png", Context.MODE_PRIVATE);
                            thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                            fOut.flush();
                            fOut.close();
                        }
                        catch (IOException e)
                        { e.printStackTrace();
                        }
                        //
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        variableBinding.textView.setText("Welcome " + emailAddress);

        variableBinding.phoneButton.setOnClickListener(click ->
        {
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse("tel:" + variableBinding.phoneNumber.getText().toString()));
            startActivity(call);
        });

        variableBinding.pictureButton.setOnClickListener(click ->{
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraResult.launch(cameraIntent);
        });
//
        File myImage = new File(getFilesDir(), "Picture.png");
        if(myImage.exists()){
            Bitmap image = BitmapFactory.decodeFile("/data/data/algonquin.cst2335.rafaelsandroidlabs/files/Picture.png");
            variableBinding.profileImage.setImageBitmap(image);
        }
//
    }
}