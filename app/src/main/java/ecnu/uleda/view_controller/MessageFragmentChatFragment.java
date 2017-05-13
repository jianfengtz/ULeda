package ecnu.uleda.view_controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ecnu.uleda.R;
import ecnu.uleda.model.Msg;

/**
 * Created by zhaoning on 2017/5/2.
 */

public class MessageFragmentChatFragment extends Fragment{
    private List<Msg> msgList=new ArrayList<>();
    private EditText inputText;
    private Button send;
    private Button buttonBack;
    private TextView mTitle;
    private RecyclerView msgRecyclerView;
    private MessageAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMsgs();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.message_fragment_chat_fragment, container, false);
        inputText = (EditText)view.findViewById(R.id.message_edit_text);
        send=(Button)view.findViewById(R.id.button_send_message);
        buttonBack=(Button)view.findViewById(R.id.button_chat_back);
        mTitle=(TextView)view.findViewById(R.id.chat_title);
        //TODO
        //Msg还需重建
        msgRecyclerView=(RecyclerView)view.findViewById(R.id.chat_recycle_view);
//        final Fragment leftFragment=new MessageFragmentLeftFragment();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this.getContext());
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter= new MessageAdapter (msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemChanged(msgList.size()-1);//有新消息，刷新RecyclerView显示
                    msgRecyclerView.scrollToPosition(msgList.size()-1);//将RecyclerView定位到最后一行
                    inputText.setText("");//清空输入内容
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                replaceFragment(leftFragment);
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.（测试）", Msg.TYPE_RECEIVED); msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?（测试）", Msg.TYPE_SEND); msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. （测试）", Msg.TYPE_RECEIVED); msgList.add(msg3);
    }

}