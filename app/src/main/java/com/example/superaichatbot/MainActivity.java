package com.example.superaichatbot;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> mMsgList = new ArrayList<>();

    private EditText inputText;

    private Button sendBtn;

    private RecyclerView msgRecycleView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (EditText) findViewById(R.id.input_text);
        sendBtn = (Button) findViewById(R.id.send_btn);
        msgRecycleView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecycleView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(mMsgList);
        msgRecycleView.setAdapter(adapter);
        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String contentSend = inputText.getText().toString();
                if (!"".equals(contentSend)) {
                    // Close the soft keyboard.
                    InputMethodManager inputMethodManager = (InputMethodManager)  getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                    // Display new message.
                    String contentReceived = contentSend.replace("你", "我")
                            .replace("吗", "").replace("？", "！");
                    Msg msg = new Msg(contentSend, Msg.TYPE_SENT);
                    Msg msg2 = new Msg(contentReceived, Msg.TYPE_RECEIVED);
                    mMsgList.add(msg);
                    mMsgList.add(msg2);
                    // When a new message comes, update the display.
                    adapter.notifyItemInserted(mMsgList.size() - 1);
                    // Scroll ListView to the last line.
                    msgRecycleView.scrollToPosition(mMsgList.size() - 1);
                    inputText.setText("");
                }
            }
        });
    }
}
