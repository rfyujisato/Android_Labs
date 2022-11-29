package algonquin.cst2335.rafaelsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityMainBinding;

/**
 *  @author Rafael Yuji Sato
 *  @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private EditText et = null;

    private Button btn = null;

    RequestQueue queue = null;

    ImageRequest imgReq;
    Bitmap image;

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
                //stringURL = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(cityName, "UTF-8") +
                //            "&appid=7e943c97096a9784391a981c4d878b22&units=metric";
                stringURL = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(cityName, "UTF-8") +
                        "&appid=83b0b5ef94b06a42cc6352dc8519d6a9&units=metric";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, stringURL, null,
                        (response) -> {
                            try {
                                //JSONObject coord = response.getJSONObject( "coord" );
                                //int vis = response.getInt("visibility");
                                //String name = response.getString( "name" );

                                JSONObject mainObject = response.getJSONObject("main");
                                double current = mainObject.getDouble("temp");
                                double min = mainObject.getDouble("temp_min");
                                double max = mainObject.getDouble("temp_max");
                                int humidity = mainObject.getInt("humidity");

                                JSONArray weather = response.getJSONArray("weather");
                                JSONObject position0 = weather.getJSONObject(0);
                                String description = position0.getString("description");
                                String iconName = position0.getString("icon");

                                String pathname = getFilesDir() + "/" + iconName + ".png";
                                File file = new File(pathname);
                                if(file.exists())
                                {
                                    image = BitmapFactory.decodeFile(pathname);
                                }
                                else {
                                    imgReq = new ImageRequest("https://openweathermap.org/img/w/" + iconName + ".png", new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap bitmap) {
                                            FileOutputStream fOut = null;
                                            try {
                                                fOut = openFileOutput( iconName + ".png", Context.MODE_PRIVATE);
                                                image = bitmap;
                                                image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                                                fOut.flush();
                                                fOut.close();
                                            }
                                            catch(Exception e){
                                                e.printStackTrace();
                                            }
                                        }
                                    }, 1024, 1024, ImageView.ScaleType.CENTER, null, (error) -> {   });
                                    queue.add(imgReq);
                                }

                                runOnUiThread( (  )  -> {

                                    binding.temp.setText("The current temperature is " + current + "°");
                                    binding.temp.setVisibility(View.VISIBLE);

                                    binding.min.setText("The min temperature is " + min + "°");
                                    binding.min.setVisibility(View.VISIBLE);

                                    binding.max.setText("The max temperature is " + max + "°");
                                    binding.max.setVisibility(View.VISIBLE);

                                    binding.humidity.setText("The humidity is " + humidity + "%");
                                    binding.humidity.setVisibility(View.VISIBLE);

                                    binding.icon.setImageBitmap(image);
                                    binding.icon.setVisibility(View.VISIBLE);

                                    binding.description.setText(description);
                                    binding.description.setVisibility(View.VISIBLE);
                                });



                            } catch (JSONException e ) {
                                e.printStackTrace();
                            }
                        }, //success callback function
                        (error) -> {
                            Log.e("Error", "error");
                }); //error callback function
            queue.add(request);
            }
            catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        });
    }
}