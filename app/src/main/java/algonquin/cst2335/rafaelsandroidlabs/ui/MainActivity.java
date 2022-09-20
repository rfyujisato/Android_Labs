package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import algonquin.cst2335.rafaelsandroidlabs.data.MainViewModel;
import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater()); //load premade variables from viewbinding
        setContentView(variableBinding.getRoot());

/*      TextView mytext = variableBinding.textview;
        Button btn = variableBinding.mybutton;
        EditText myedit = variableBinding.myeditext;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytext.setText("Your edit text has:" + editString);
            }
        });
*/

        variableBinding.mybutton.setOnClickListener( (vw) -> { // another option is without () or using anonymous parameter
            model.editString.postValue(variableBinding.myeditext.getText().toString());
        } );

        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has " + s);
        });
    }

}