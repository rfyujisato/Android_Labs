package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import algonquin.cst2335.rafaelsandroidlabs.data.MainViewModel;
import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    Context context = getApplicationContext();
    public String compoundBtn;
    int duration = Toast.LENGTH_SHORT;

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

        //Listener for the button
        variableBinding.mybutton.setOnClickListener( (vw) -> { // another option is without () or using anonymous parameter
            model.editString.postValue(variableBinding.myeditext.getText().toString());
        } );

        //Observer to change textView when editText is changed
        model.editString.observe(this, s -> {
            variableBinding.textview.setText("Your edit text has " + s);
        });

        //Listener for CheckBox
        variableBinding.checkBox.setOnCheckedChangeListener((cb,isChecked) -> {
            model.isSelected.postValue(isChecked);
            compoundBtn = "CheckBox";
        });

        //Listener for Switch
        variableBinding.switch1.setOnCheckedChangeListener((sw,isChecked) -> {
            model.isSelected.postValue(isChecked);
            compoundBtn = "Switch";
        });

        //Listener for Radiobutton
        variableBinding.radioButton.setOnCheckedChangeListener((rb,isChecked) -> {
            model.isSelected.postValue(isChecked);
            compoundBtn = "RadioButton";
        });

        //Observer to change all compoundButtons when one of then is changed
        model.isSelected.observe(this, selected -> {
            variableBinding.checkBox.setChecked(selected);
            variableBinding.switch1.setChecked(selected);
            variableBinding.radioButton.setChecked(selected);

            Toast toast = Toast.makeText(context, compoundBtn, duration);
            toast.show();
        });
    }

}