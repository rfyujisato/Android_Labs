package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.rafaelsandroidlabs.R;
import algonquin.cst2335.rafaelsandroidlabs.data.ChatRoomViewModel;
import algonquin.cst2335.rafaelsandroidlabs.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    ChatRoomViewModel chatModel ;
    ArrayList<ChatMessage> messages;

    ActivityChatRoomBinding binding;

    RecyclerView.Adapter<MyRowHolder> adapter;
    ChatMessageDAO mDAO;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        messages = chatModel.messages.getValue();

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "MessageDatabase").build();
        mDAO = db.cmDAO();

        if(messages == null)
        {
            chatModel.messages.postValue(messages = new ArrayList<>());

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() ->
            {
                messages.addAll( mDAO.getAllMessages() ); //Get data from database
                runOnUiThread( () ->  binding.recycleView.setAdapter(adapter)); //Load the RecyclerView
            });
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateandTime = sdf.format(new Date());

        binding.send.setOnClickListener( click ->{
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessage(binding.textInput.getText().toString());
            chatMessage.setTimeSent(currentDateandTime);
            chatMessage.setSentButton(true);


            chatModel.messages.getValue().add(chatMessage);
            //clear the previous text:
            adapter.notifyItemChanged(messages.size()-1); //which position has changed
            binding.textInput.setText(""); //remove what was there

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                mDAO.insertMessage(chatMessage);
            });
        });

        binding.receive.setOnClickListener( click ->{
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setMessage(binding.textInput.getText().toString());
            chatMessage.setTimeSent(currentDateandTime);
            chatMessage.setSentButton(false);

            chatModel.messages.getValue().add(chatMessage);
            //clear the previous text:
            adapter.notifyItemChanged(messages.size()-1); //which position has changed
            binding.textInput.setText(""); //remove what was there

            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute( () -> {
                mDAO.insertMessage(chatMessage);
            });
        });

        adapter = new RecyclerView.Adapter<MyRowHolder>() {
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
        };
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