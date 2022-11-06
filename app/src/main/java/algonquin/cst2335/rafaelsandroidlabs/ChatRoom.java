package algonquin.cst2335.rafaelsandroidlabs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {
    ActivityChatRoomBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());  
        
        binding.recycleView.setAdapter(adapter = new RecyclerView.Adapter<>())
    }

}