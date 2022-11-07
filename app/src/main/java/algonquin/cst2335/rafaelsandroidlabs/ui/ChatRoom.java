package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import algonquin.cst2335.rafaelsandroidlabs.R;
import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    ArrayList<String> messages = new ArrayList<>();

    ActivityChatRoomBinding binding;

    RecyclerView.Adapter<MyRowHolder> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.button2.setOnClickListener( click ->{
            messages.add(binding.textInput.getText().toString());

            //clear the previous text:
            adapter.notifyItemChanged(messages.size()-1); //which position has changed
            binding.textInput.setText(""); //remove what was there
        });

        binding.recycleView.setAdapter(adapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //This function creates a ViewHolder object which represents a single row in the list
                View root;
                root = getLayoutInflater().inflate(R.layout.sent_message, parent, false);
                return new MyRowHolder(root);
            }

            @Override
            public int getItemViewType(int position) {
                return 0; //0 represents send, text on the left
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) { //This initializes a ViewHolder to go at the row specified by the position parameter.
                String thisRow = messages.get(position);

                holder.message.setText(thisRow);
                holder.time.setText("");
            }

            @Override
            public int getItemCount() { //This function just returns an int specifying how many items to draw.
                return messages.size();
            }
        });
    }
    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView message;
        TextView time;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
        }
    }
}