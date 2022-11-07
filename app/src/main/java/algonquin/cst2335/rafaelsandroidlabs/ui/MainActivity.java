package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.button.setOnClickListener(click ->
        {
            Intent nextPage = new Intent(MainActivity.this, ChatRoom.class);
            startActivity(nextPage);
        });
    }


    // Activity is visible, but not responding to touch
    @Override
    protected void onStart() {
        super.onStart();

        Log.e("MainActivity", "In onStart()");
    }

    // Activity is visible, and responding to touch
    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "In onResume()");
    }


    // Activity is visible, and not responding to touch
    @Override
    protected void onPause() {
        super.onPause();
        Log.e("MainActivity", "In onPause()");
    }

    // No longer visible
    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "In onStop()");
    }

    // Clear memory
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}