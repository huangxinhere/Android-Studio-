package com.example.quizeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String TAG = "CheatActivity";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.quizeactivity.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.quizeactivity.answer_shown";
    private static final String SAVE_IS_CHEAT = "saveIsCheat";
    private boolean mAnswerIsTrue;
    //private boolean isAnswerShown;//原想法：直接把是否显示答案放进键值
    private boolean cheatedFlag = false;//默认没有作弊

    private TextView mAnswerTextView;
    private Button mShowAnswerButton;

    public static boolean wasAnswerShown (Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null){
            cheatedFlag = savedInstanceState.getBoolean(SAVE_IS_CHEAT);
            setAnswerShownResult(cheatedFlag);//问题,怎么确定是否作弊？
        }

        //getIntent方法返回了由startActivity转发的Intent对象
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false); //后者为什么是默认的？不能是空值吗；前者怎么就知道正确答案呢？难道是getintent就得到数组对象
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);//结合布局的textView，显示什么？

        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);//结合布局
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);//引对错按钮的响应执行——toast
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    int cx = mShowAnswerButton.getWidth() / 2;
                    int cy = mShowAnswerButton.getHeight() / 2;
                    float radius = mShowAnswerButton.getWidth();
                    Animator anim = ViewAnimationUtils
                            .createCircularReveal(mShowAnswerButton, cx, cy, radius,0);
                    anim.addListener(new AnimatorListenerAdapter(){
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            mShowAnswerButton.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();

                }
            }
        });

    }

    public void onSaveInstanceState(Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        saveInstanceState.putBoolean(SAVE_IS_CHEAT,cheatedFlag);
    }

    public static Intent newIntent(Context packageContext,boolean answerIsTrue){
        Intent intent = new Intent(packageContext,CheatActivity.class);//区分创建intent和增加数据的表达
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);//
        return intent;
    }//这个新增内容的处理可以写在MainActivity里面，但它其实不需要知道这些细节

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);//子代码回发结果代码（区别不同的子activity）+附加的信息
    }

}

