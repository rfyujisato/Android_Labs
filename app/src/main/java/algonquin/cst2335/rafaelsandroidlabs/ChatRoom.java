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

    RecyclerView.Adapter<MyRowHolder> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setAdapter(adapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //This function creates a ViewHolder object which represents a single row in the list
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) { //This initializes a ViewHolder to go at the row specified by the position parameter.
            }

            @Override
            public int getItemCount() { //This function just returns an int specifying how many items to draw.
                return 0;
            }
        });
    }
    class MyRowHolder extends RecyclerView.ViewHolder {
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}