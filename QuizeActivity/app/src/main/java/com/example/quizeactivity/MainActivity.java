package com.example.quizeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.quizeactivity.R.id.pre_button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";//日志查看生命周期
    private static final String KEY_INDEX="index";
    private Button mTrueButton;
    private Button mFalseButton;//实例
    private Button mNextButton;
    private ImageButton mPreButton;
    private TextView mQuestionTextView;
    //数组对象是Question类的对象，所以不停的构造;然后引用的字符串资源是整型；对象与对象之间要用逗号隔开
    private Question[] mQuestionBank= new Question[]{
      new Question(R.string.question_australia,true),
      new Question(R.string.question_mideast,false),
      new Question(R.string.question_oceans,true)
    };

    private int mCurrentIndex = 0;//什么用？意思是当前索引
/****************************************************************************************************/
    @Override               //bundle有捆绑的意思
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);//https://www.cnblogs.com/Chenshuai7/p/5290918.html有详细介绍

        if (savedInstanceState != null){//检验存储的bundle信息????
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }//好像又反过来赋值了

        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);//理解是把视图的test引为这里的实例

       /* mQuestionTextView.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;//我觉得要首先变成按钮才有互动的窗口
            }
        });*/
       // int question = mQuestionBank[mCurrentIndex].getTestResId();//又设置了question变量，联系数组，getter方法（同一个包内另外的类的方法可调用）得到它的实例/本来数组就是Question类的
        //mQuestionTextView.setText(question);//又把question引到对象，模糊觉得联系起了对象和数组

        mTrueButton=(Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast t=Toast.makeText(MainActivity.this,R.string.corret_toast,Toast.LENGTH_SHORT);
                t.setGravity(Gravity.TOP,0,0);
                t.show();*/
                checkAnswer(true);

                mTrueButton.setEnabled(false);
            }
        });

        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(MainActivity.this,
                                       R.string.incorret_toast,
                                       Toast.LENGTH_SHORT).show();*/
                checkAnswer(false);
                mFalseButton.setEnabled(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;//递增数组索引；当进行点击后的mCurrentIndex能保存新的赋值，为什么？猜想：多次调用同一个方法，其体内的变量能保持状态？
                updateQuestion();//方法删除
            }
        });

        mPreButton = (ImageButton) findViewById(R.id.pre_button);
        mPreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"mCurrentIndex is changed.");
                mCurrentIndex = mCurrentIndex-1;
                if(mCurrentIndex<0)
                    mCurrentIndex=2;
                Log.d(TAG,"this step is done.");
                updateQuestion();
                Log.d(TAG,"all is done.");//
            }
        });

        updateQuestion();//单独悬在这？好像只有显示问题那儿要用，但不能mQuestion.updateQuestion()?

    }//安卓编程的定位函数，主要是引用.R文件里的引用名。一般在R.java文件里系统会自动帮你给出你在XML里定义的ID或者Layout里面的名称，例如：Button button=(Button)findViewById(R.id.button01);这样就引用了XML（res里的布局文件）文件里面的button，使得在写.java的按钮时能与XML里的一致。
/************************************************************************************/
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart（） called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
    /**********************************************************************************************/

    private void updateQuestion()//将公共代码放在单独的私有方法里
    {//不属于onCreate方法里面了，独立出来
        Log.d(TAG,"Updating question test",new Exception());
        int question = mQuestionBank[mCurrentIndex].getTestResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;//什么用_作为判断结果的统一变量

        if (userPressedTrue==answerIsTrue){
            messageResId = R.string.corret_toast;
        }else {
            messageResId=R.string.incorret_toast;
        }

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
                .show();
    }
}