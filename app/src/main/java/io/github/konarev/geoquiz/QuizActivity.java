package io.github.konarev.geoquiz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    //private LinearLayout mAnswerButtonsGroup;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;
    private int mCorrectAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle savedInstanceState) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        //mAnswerButtonsGroup = (LinearLayout)findViewById(R.id.answer_buttons_group);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                mTrueButton.setVisibility(View.GONE);
                mFalseButton.setVisibility(View.GONE);
*/
                //mAnswerButtonsGroup.setVisibility(View.GONE);
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                mTrueButton.setVisibility(View.GONE);
                mFalseButton.setVisibility(View.GONE);
*/
                //mAnswerButtonsGroup.setVisibility(View.GONE);
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mPreviousIndex = mCurrentIndex;
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = Math.max(mCurrentIndex - 1, 0) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState() called");
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        LinearLayout mAnswerButtonsGroup;
        mAnswerButtonsGroup = (LinearLayout) findViewById(R.id.answer_buttons_group);
        if (mQuestionBank[mCurrentIndex].isAnswerExist()) {
            mAnswerButtonsGroup.setVisibility(View.GONE);
            if (mQuestionBank[mCurrentIndex].isAnswerTrue()) {
                mQuestionTextView.setTextColor(getResources().getColor(R.color.colorCoolAnswer));
            } else {
                mQuestionTextView.setTextColor(getResources().getColor(R.color.colorFailAnswer));
            }
        } else {
            mAnswerButtonsGroup.setVisibility(View.VISIBLE);
            mQuestionTextView.setTextColor(getResources().getColor(R.color.colorNoAnswer));
        }

    }

    private void checkAnswer(boolean userAnswer) {

        mQuestionBank[mCurrentIndex].setAnswerExist(true);
        mQuestionBank[mCurrentIndex].setAnswerTrue(userAnswer == mQuestionBank[mCurrentIndex].isAnswer());

        int messageResId;
        if (mQuestionBank[mCurrentIndex].isAnswerTrue()) {
            messageResId = R.string.correct_toast;
            mCorrectAnswersCount++;
        } else {
            messageResId = R.string.incorrect_toast;
            mCorrectAnswersCount = Math.min(mCorrectAnswersCount - 1, 0);
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).
                show();
/*
        if(mCurrentIndex == (mQuestionBank.length - 1)) {
            String message = String.format(getResources().getString(R.string.correct_percent),mCorrectAnswersCount/mQuestionBank.length);
            Toast.makeText(this, message, Toast.LENGTH_SHORT);
        }
*/
        updateQuestion();

    }
}
