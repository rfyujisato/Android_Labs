package algonquin.cst2335.rafaelsandroidlabs.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.rafaelsandroidlabs.databinding.DetailsLayoutBinding;
import algonquin.cst2335.rafaelsandroidlabs.ui.ChatMessage;

public class MessageDetailsFragment extends Fragment {

    ChatMessage selected;

    public MessageDetailsFragment (ChatMessage m) {
        selected = m;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DetailsLayoutBinding binding = DetailsLayoutBinding.inflate(inflater);

        binding.message.setText("Message: " + selected.getMessage());
        binding.time.setText("Time: " + selected.getTimeSent());
        if (selected.getIsSentButton() == true) {
            binding.sendReceive.setText("This message was sent");
        } else {
            binding.sendReceive.setText("This message was received");
        }
        binding.id.setText("Id: " + selected.getId());

        return binding.getRoot();
    }
}
