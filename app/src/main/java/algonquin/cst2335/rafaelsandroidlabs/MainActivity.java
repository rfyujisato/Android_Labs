package algonquin.cst2335.rafaelsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityMainBinding;

/** This page validates the requirements of the inserted password after
 *  the click of the button, and it shows a toast message and the change
 *  the value of the Text View when the password does not meet the requirements.
 *  @author Rafael Yuji Sato
 *  @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This variable holds the field with the password entry*/
    private EditText et = null;

    /** This variable holds the button on the bottom of the screen*/
    private Button btn = null;

    RequestQueue queue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        et = binding.editText;
        btn = binding.button;

        btn.setOnClickListener( clk ->{
            String cityName = et.getText().toString();
            String stringURL = "";

            try {
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(cityName, "UTF-8") +
                            "&appid=7e943c97096a9784391a981c4d878b22&units=metric";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                    (response) -> {
                        try {
                            JSONObject coord = response.getJSONObject( "coord" );

                            JSONObject mainObject = response.getJSONObject("main");
                            double current = mainObject.getDouble("temp");
                            double min = mainObject.getDouble("temp_min");
                            double max = mainObject.getDouble("temp_max");
                            int humidity = mainObject.getInt("humidity");

                            int vis = response.getInt("visibility");

                            String name = response.getString( "name" );

                            JSONArray weather = response.getJSONArray("weather");
                            JSONObject position0 = weather.getJSONObject(0);
                            String description = position0.getString("description");
                            String iconName = position0.getString("icon");

                        } catch (JSONException e ) {
                            e.printStackTrace();
                        }

                    }, //success callback function
                    (error) -> {
                        int i = 0;
                        i++;
                    }); //error callback function
            queue.add(request);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        });
    }
}