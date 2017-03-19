package ecnu.uleda;

import android.content.Intent;
import android.content.res.ObbInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.ViewGroup.*;

/**
 * Created by Shensheng on 2016/10/17.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private Button mLogin;
    private EditText mUserName;
    private EditText mPassword;
    private PopupWindow mPopupWindow;
    long mExitTime = System.currentTimeMillis();
    int keyCode;
    KeyEvent event;

    private TextView mRegister;
    private TextView mPasswordForget;

    private Button FindPassWord;
    private Button MessageLogin;
    private Button CancelFindBack;

    private Handler mHandler=new Handler(){
      @Override
      public void handleMessage(Message msg){
          if(mUOC.getIsLogined()){
              Intent intent=new Intent(LoginActivity.this,UMainActivity.class);
              startActivity(intent);
              LoginActivity.this.finish();
              Toast.makeText(LoginActivity.this,"欢迎您，"+mUOC.getUserName()+"！",Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(LoginActivity.this,"登陆错误："+mUOC.getMessage(),Toast.LENGTH_SHORT).show();
              setAllEnabled(true);
          }
      }
    };
    private UserOperatorController mUOC=UserOperatorController.getInstance();
    @Override
    protected void onCreate(Bundle  savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        onKeyDown( keyCode,  event);

    }


    protected void init(){
        mLogin=(Button)findViewById(R.id.login_button);
        mUserName=(EditText)findViewById(R.id.user_name);
        mPassword=(EditText)findViewById(R.id.password);
        mRegister = (TextView)findViewById(R.id.login_text);

        mPasswordForget = (TextView)findViewById(R.id.password_forgotten) ;

        //测试
        mUserName.setText("dizy");
        mPassword.setText("zy980018");
        //测试
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAllEnabled(false);
                new Thread(){
                    @Override
                    public void run(){
                        mUOC.login(mUserName.getText().toString(),mPassword.getText().toString());
                        try {
                            if(mUOC.getIsLogined()) {
                                UTaskManager.getInstance().refreshTaskInList();
                            }
                        }catch (UServerAccessException e){
                            e.printStackTrace();
                        }
                        Log.d("LoginActivity",mUOC.getMessage());
                        Message message=new Message();
                        mHandler.sendMessage(message);
                    }
                }.start();
            }
        });
        mRegister.setOnClickListener(this);
        mPasswordForget.setOnClickListener(this);


    }


    private void setAllEnabled(boolean a){
        mUserName.setEnabled(a);
        mPassword.setEnabled(a);
        mLogin.setEnabled(a);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime= System.currentTimeMillis();

            } else{
                finish();
                Intent intent = new Intent(this,UMainActivity.class);
                intent.putExtra(UMainActivity.TAG_EXIT, true);
                startActivity(intent);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.login_text:
            {
                Intent i = new Intent(LoginActivity.this,UserRegister.class);
                startActivity(i);
                break;
            }
            case R.id.password_forgotten:
            {
                showPopMenu();
                break;
            }
            case R.id.cancelFindBack:
            {
                mPopupWindow.dismiss();
                break;
            }
            case R.id.findBackPassword:
            {
                mPopupWindow.dismiss();
                break;
            }
            case R.id.messageLogin:
            {
                Intent i = new Intent(LoginActivity.this,GetBackByNumber.class);
                startActivity(i);
            }
        }
    }
    private void showPopMenu()
    {
        View view = View.inflate(this.getApplicationContext(),R.layout.activity_forget_password,null);

        FindPassWord = (Button)view.findViewById(R.id.findBackPassword);
        MessageLogin = (Button) view.findViewById(R.id.messageLogin);
        CancelFindBack = (Button) view.findViewById(R.id.cancelFindBack);

        FindPassWord.setOnClickListener(this);
        MessageLogin.setOnClickListener(this);
        CancelFindBack.setOnClickListener(this);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        view.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this.getApplicationContext(), R.anim.fade_in));
        LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.forget_password);
        ll_popup.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this.getApplicationContext(), R.anim.push_bottom_in));

        if(mPopupWindow==null){
            mPopupWindow = new PopupWindow(LoginActivity.this);
            mPopupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

            mPopupWindow.setFocusable(true);
            mPopupWindow.setOutsideTouchable(true);
        }
        mPopupWindow.setContentView(view);
        mPopupWindow.showAtLocation(mPasswordForget, Gravity.BOTTOM, 0, 0);
        mPopupWindow.update();

    }




}
