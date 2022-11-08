package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.rafaelsandroidlabs.R;
import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    ArrayList<ChatMessage> messages = new ArrayList<>();

    ActivityChatRoomBinding binding;

    RecyclerView.Adapter<MyRowHolder> adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateandTime = sdf.format(new Date());

        binding.send.setOnClickListener( click ->{
            ChatMessage chatMessage = new ChatMessage(binding.textInput.getText().toString(),
                    currentDateandTime, true);

            messages.add(chatMessage);
            //clear the previous text:
            adapter.notifyItemChanged(messages.size()-1); //which position has changed
            binding.textInput.setText(""); //remove what was there
        });

        binding.receive.setOnClickListener( click ->{
            ChatMessage chatMessage = new ChatMessage(binding.textInput.getText().toString(),
                    currentDateandTime, false);

            messages.add(chatMessage);
            //clear the previous text:
            adapter.notifyItemChanged(messages.size()-1); //which position has changed
            binding.textInput.setText(""); //remove what was there
        });

        binding.recycleView.setAdapter(adapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //This function creates a ViewHolder object which represents a single row in the list
                View root;
                if(viewType == 0) {
                    root = getLayoutInflater().inflate(R.layout.sent_message, parent, false);
                    return new MyRowHolder(root);
                } else {
                    root = getLayoutInflater().inflate(R.layout.receive_message, parent, false);
                    return new MyRowHolder(root);
                }
            }

            @Override
            public int getItemViewType(int position) {
                if(messages.get(position).getIsSentButton() == true) {
                    return 0;
                } else {
                    return 1;
                }
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) { //This initializes a ViewHolder to go at the row specified by the position parameter.
                ChatMessage thisRow = messages.get(position);
                holder.message.setText(thisRow.getMessage());
                holder.time.setText(thisRow.getTimeSent());
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

    class ChatMessage{
        String message;
        String timeSent;
        boolean isSentButton;

        public ChatMessage(String m, String t, boolean sent) {
            message = m;
            timeSent = t;
            isSentButton = sent;
        }

        public String getMessage(){
            return message;
        }

        public String getTimeSent(){
            return timeSent;
        }

        public boolean getIsSentButton(){
            return isSentButton;
        }
    }
}