package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import algonquin.cst2335.rafaelsandroidlabs.data.MainViewModel;
import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    private String compoundBtn;

    // Getter for compoundBtn
    public String getCompoundBtn() {
        return compoundBtn;
    }

    // Setter for compoundBtn
    public void setCompoundBtn(String compoundBtn) {
        this.compoundBtn = compoundBtn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater()); //load premade variables from viewbinding
        setContentView(variableBinding.getRoot());

/*      Expanded version of lambda functions
        TextView mytext = variableBinding.textView;
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
            variableBinding.textView.setText("Your edit text has " + s);
        });

        //Listener for CheckBox
        variableBinding.checkBox.setOnCheckedChangeListener((cb,isChecked) -> {
            model.isSelected.postValue(isChecked);
            setCompoundBtn("CheckBox");
        });

        //Listener for Switch
        variableBinding.switch1.setOnCheckedChangeListener((sw,isChecked) -> {
            model.isSelected.postValue(isChecked);
            setCompoundBtn("Switch");
        });

        //Listener for Radiobutton
        variableBinding.radioButton.setOnCheckedChangeListener((rb,isChecked) -> {
            model.isSelected.postValue(isChecked);
            setCompoundBtn("RadioButton");
        });

        //Observer to change all compoundButtons when one of then is changed
        model.isSelected.observe(this, selected -> {
//            variableBinding.checkBox.setChecked(selected);
//            variableBinding.switch1.setChecked(selected);
//            variableBinding.radioButton.setChecked(selected);

            Toast toast = Toast.makeText(
                    getApplicationContext(), "You clicked on the " + getCompoundBtn() +
                            " and it is now: " + model.isSelected.getValue(), Toast.LENGTH_SHORT);
            toast.show();
        });

        //Listener for imageView
        variableBinding.imageView.setOnClickListener( (iv) -> {
            variableBinding.textView.setText("Your imageView is Algonquin College Logo");
        });

        //Listener for imageButton that trigger a toast message
        variableBinding.imageButton.setOnClickListener( (ib) -> {
            Toast toast = Toast.makeText(
                    getApplicationContext(), "The width = " + variableBinding.imageButton.getWidth() +
                            " and height = " + variableBinding.imageButton.getHeight(), Toast.LENGTH_SHORT);
            toast.show();
        });
    }

}