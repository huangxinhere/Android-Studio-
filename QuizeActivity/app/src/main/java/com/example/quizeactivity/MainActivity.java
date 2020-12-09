package com.example.quizeactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
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
    private static final String KEY_ANSWERED = "answered";
    private static final String KEY_CORRECT = "correct";
    private static final int REQUEST_CODE_CHEAT = 0;
    private Button mTrueButton;
    private Button mFalseButton;//实例
    private ImageButton mNextButton;
    private ImageButton mPreButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int userAnsweredCorrect = 0;//记录答对的题目数量

    //数组对象是Question类的对象，所以不停的构造;然后引用的字符串资源是整型；对象与对象之间要用逗号隔开
    private Question[] mQuestionBank= new Question[]{
      new Question(R.string.question_australia,true),
      new Question(R.string.question_mideast,false),
      new Question(R.string.question_oceans,true)
    };

    private int mCurrentIndex = 0;//什么用？意思是当前索引
    private boolean mIsCheater;

/****************************************************************************************************/
    @Override               //bundle有捆绑的意思
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);//https://www.cnblogs.com/Chenshuai7/p/5290918.html有详细介绍
        /*   userAnsweredCorrect = savedInstanceState.getInt(KEY_CORRECT);//取出数据之后app运行不了？？*/

        if (savedInstanceState != null){//检验存储的bundle信息????
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);
        }//好像又反过来赋值了

        mQuestionTextView=(TextView) findViewById(R.id.question_text_view);//理解是把视图的test引为这里的实例
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;//我觉得要首先变成按钮才有互动的窗口/不是，不需要
                updateQuestion();
            }
        });
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
                showRecord();
            }
        });

        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(MainActivity.this,
                                       R.string.incorrect_toast,
                                       Toast.LENGTH_SHORT).show();*/
                checkAnswer(false);
                showRecord();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了附加信息，注释掉Intent intent = new Intent(MainActivity.this,CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();//提取布尔值
                Intent intent = CheatActivity.newIntent(MainActivity.this,answerIsTrue);//布尔值给子activity作为他的intent，而intent由基本（包名、）增加
                startActivityForResult(intent,REQUEST_CODE_CHEAT);//带有返回要求的启动方法
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1) % mQuestionBank.length;//递增数组索引；当进行点击后的mCurrentIndex能保存新的赋值，为什么？猜想：多次调用同一个方法，其体内的变量能保持状态？
                mIsCheater = false;
                updateQuestion();//方法删除
                showRecord();
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
                showRecord();
            }
        });

        updateQuestion();//单独悬在这？好像只有显示问题那儿要用，但不能mQuestion.updateQuestion()?

    }//安卓编程的定位函数，主要是引用.R文件里的引用名。一般在R.java文件里系统会自动帮你给出你在XML里定义的ID或者Layout里面的名称，例如：Button button=(Button)findViewById(R.id.button01);这样就引用了XML（res里的布局文件）文件里面的button，使得在写.java的按钮时能与XML里的一致。

    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//这怎么了？？覆盖这个原有的方法
        if (requestCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if (data == null){
                return;
            }
        }//if语句在干嘛？要求同时满足结果代码和请求代码才赋值解析的结果
        mIsCheater = CheatActivity.wasAnswerShown(data);
    }

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
    public void onSaveInstanceState(Bundle savedInstanceState) {//英文解释：保存实例状态**在更新配置（例如旋转）时保存数据？
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);//？？？？对应index,
        savedInstanceState.putInt(KEY_CORRECT,userAnsweredCorrect);

        if (savedInstanceState != null) {//问题：什么东西？

            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX,0);

            boolean answerIsAnswered[] = new boolean[mQuestionBank.length];
            for (int i = 0; i < mQuestionBank.length; i++) {
                answerIsAnswered[i] = mQuestionBank[i].isAnswered();
            }
            savedInstanceState.putBooleanArray(KEY_ANSWERED, answerIsAnswered);
        }//将是否回答的数据存入数组，然后放在Bundle中
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

    private void checkIfAnswered(){//封装——检查是否回答过问题的函数*****true/false键、pre/next键要用到
        boolean answerIsAnswered = mQuestionBank[mCurrentIndex].isAnswered();//mAnswered 有赋值吗？？
        if (answerIsAnswered == true){
            //如果题目被回答，则按键设置不可按下
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }else{
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }

    private void updateQuestion()//将公共代码放在单独的私有方法里
    {//不属于onCreate方法里面了，独立出来
        Log.d(TAG,"Updating question test",new Exception());
        int question = mQuestionBank[mCurrentIndex].getTestResId();
        mQuestionTextView.setText(question);
        checkIfAnswered();
    }

    private void checkAnswer(boolean userPressedTrue)
    {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;//什么用_作为判断结果的统一变量

        if (mIsCheater){
            messageResId = R.string.judgment_toast;
        }else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                userAnsweredCorrect++;//只是记录答对次数
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        mQuestionBank[mCurrentIndex].setAnswered(true);//判断完正误之后就调用接受参数的方法改变Boolean值
        checkIfAnswered();//所以就能有禁掉的指令

        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT)
                .show();
    }

    private void showRecord(){
        boolean allAnswered = true ;
        String message = null;
        double correctMark = 0;
        int correctAnswerNum = 0;

        for (int i = 0; i < mQuestionBank.length; i++){
            if (mQuestionBank[i].isAnswered() == false){
                allAnswered = false;
                break;
            }//只要有没答完的，allAnswered都是false；每一次调用这个方法时都会检查是否完全答完
        }
        if (allAnswered == true){//直到完全答完
            correctMark = (double) userAnsweredCorrect/mQuestionBank.length;
            correctMark = (double)((int)(correctMark*10000)/100.0);//保留后两位
            message = "正确率" + String.valueOf(correctMark) + "%";
            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
        }

    }
}