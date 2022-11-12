package algonquin.cst2335.rafaelsandroidlabs.ui;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    protected int id;

    @ColumnInfo(name="message")
    protected String message;

    @ColumnInfo(name="timeSent")
    protected String timeSent;

    @ColumnInfo(name="isSentButton")
    protected boolean isSentButton;


    public ChatMessage() { }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getTimeSent(){
        return timeSent;
    }

    public void setTimeSent(String timeSent) {
        this.timeSent = timeSent;
    }

    public boolean getIsSentButton(){
        return isSentButton;
    }

    public void setSentButton(boolean sentButton) {
        isSentButton = sentButton;
    }
}
