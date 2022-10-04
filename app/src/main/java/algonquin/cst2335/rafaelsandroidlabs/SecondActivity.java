package algonquin.cst2335.rafaelsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding variableBinding;

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

    }
}