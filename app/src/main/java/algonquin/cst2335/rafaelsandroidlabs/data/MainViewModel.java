package algonquin.cst2335.rafaelsandroidlabs.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel { //survive rotation changes
    public MutableLiveData<String> editString = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();
}
