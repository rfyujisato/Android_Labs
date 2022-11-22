package algonquin.cst2335.rafaelsandroidlabs.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import algonquin.cst2335.rafaelsandroidlabs.ui.ChatMessage;
import algonquin.cst2335.rafaelsandroidlabs.ui.ChatRoom;


public class ChatRoomViewModel extends ViewModel {
    public MutableLiveData<ArrayList<ChatMessage>> messages = new MutableLiveData< >();
}

