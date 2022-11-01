package algonquin.cst2335.rafaelsandroidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/** This page validates the requirements of the inserted password after
 *  the click of the button, and it shows a toast message and the change
 *  the value of the Text View when the password does not meet the requirements.
 *  @author Rafael Yuji Sato
 *  @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    /** This variable holds the text in the center of the screen*/
    private TextView tv = null;

    /** This variable holds the field with the password entry*/
    private EditText et = null;

    /** This variable holds the button on the bottom of the screen*/
    private Button btn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textView);
        EditText et = findViewById(R.id.myEditText);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener( clk ->{
            String password = et.getText().toString();

            if (checkPasswordComplexity(password)) {
                tv.setText("Your password meets the requirements");
            } else {
                tv.setText("You shall not pass!");
            }

        });
    }

    /** This function checks if the String has an Upper Case letter, a lower case letter, a number,
     *  and a special symbol. If it is missing any of these 4 requirements, then show a Toast message
     *  saying which requirements are missing.
     *  @param pw The String object being checked
     *  @return Returns true if the String meets all the requirements, else returns false and shows a toast message
     */
    private boolean checkPasswordComplexity(String pw){

        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;

        String specialChar = "~`!@#$%^&*()-_=+\\|[{]};:'\",<.>/?";
        String upperCaseMsg = "- Upper case letter";
        String lowerCaseMsg = "- Lower case letter";
        String numberMsg = "- Number";
        String specialCharMsg = "- Special character (#$%^&*!@?)";

        for (int i = 0; i < pw.length(); i++) {
            char character = pw.charAt(i);
            // Upper case validation
            if (Character.isUpperCase(character)) {
                foundUpperCase = true;
                upperCaseMsg = "";
            }

            // Lower case validation
            if (Character.isLowerCase(character)) {
                foundLowerCase = true;
                lowerCaseMsg = "";
            }

            // Number validation
            if (Character.isDigit(character)) {
                foundNumber = true;
                numberMsg = "";
            }

            // Special character validation
            if (specialChar.contains(String.valueOf(character))) {
                foundSpecial = true;
                specialCharMsg = "";
            }
        }

        // Check if all conditions are true
        if (foundUpperCase && foundLowerCase && foundNumber && foundSpecial ) {
            return true;
        } else {
            Toast toast = Toast.makeText(
                    getApplicationContext(), "Your password does not have: \n" + upperCaseMsg +
                            "\n" + lowerCaseMsg + "\n" + numberMsg + "\n" + specialCharMsg, Toast.LENGTH_LONG);
            toast.show();
            return false;
        }
    }
}