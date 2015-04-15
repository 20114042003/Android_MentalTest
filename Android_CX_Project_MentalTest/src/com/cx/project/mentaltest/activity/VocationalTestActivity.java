package com.cx.project.mentaltest.activity;

import java.util.List;

import com.cx.project.mentaltest.R;
import com.cx.project.mentaltest.custom.HeadView;
import com.cx.project.mentaltest.entity.Answer;
import com.cx.project.mentaltest.entity.Question;
import com.cx.project.mentaltest.utils.DataManagerUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class VocationalTestActivity extends Activity implements OnClickListener{
	private static final String TAG = "VocationalTestActivity";
	
	private DataManagerUtil datautil;
	private List<Question> list;
	private List<Answer> ansList;
	
	
	//控件相关
	private HeadView headView;
	private TextView txProgress;
	private TextView txQuestion;
	private RadioButton rbAnsA;
	private RadioButton rbAnsB;
	private LinearLayout llAnswer;
	private LinearLayout llQuestion;
	
	//answer
	private String answer ="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datautil = new DataManagerUtil(this);
		setContentView(R.layout.activity_vocationl_test);
		initParams();
		initView();
		initViewData();
	}

	private void initParams() {
		list=Question.getQuestionBySql(0, 1, datautil.openDatabase());
		ansList = Answer.getAnswerBySql(0, 1, datautil.openDatabase());
		datautil.closeDatabase();
		Log.i(TAG, "size ="+list.size());
		Log.i(TAG,"ansList size="+ ansList.size());
		
	}

	private void initView() {
		headView = (HeadView) findViewById(R.id.head_view);
		txProgress = (TextView) findViewById(R.id.tx_progress);
		txQuestion = (TextView) findViewById(R.id.tx_question);
		rbAnsA = (RadioButton) findViewById(R.id.rb_ans_a);
		rbAnsB = (RadioButton) findViewById(R.id.rb_ans_b);
		llAnswer = (LinearLayout) findViewById(R.id.layout_answer);
		llQuestion = (LinearLayout) findViewById(R.id.ll_question);
		
		rbAnsA.setOnClickListener(this);
		rbAnsB.setOnClickListener(this);
		headView.setLeftOnClickListener(this);
		
		
	}
	
	private int curQuestionId=0;
	private int value;

	private void initViewData() {
		showQuestion();
	}

	private void showQuestion() {
		Question item = list.get(curQuestionId);
		
		txProgress.setText(String.format("进度%s/%s", curQuestionId+1,list.size()));
		txQuestion.setText(item.question_content);
		rbAnsA.setText(item.answer_a);
		rbAnsB.setText(item.answer_b);
		
		curQuestionId = item.question_id;
		Log.i(TAG, "curQuestionId ="+ curQuestionId);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rb_ans_a:
			value+=1;
			getAnswer();
			if(curQuestionId!=list.size()){
				showQuestion();
			}else{
				Log.i(TAG, answer);
				
				showAnswer();
				
//				Toast.makeText(this, "给出答案!", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.rb_ans_b:
			value-=1;
			getAnswer();
			if(curQuestionId!=list.size()){
				showQuestion();
			}else{
				Log.i(TAG, answer);
				showAnswer();
//				Toast.makeText(this, "给出答案!", Toast.LENGTH_SHORT).show();
			}
			break;
			
		case HeadView.LEFT_ICON:
			finish();
			break;

		default:
			break;
		}
		
	}

	private void showAnswer() {
		llAnswer.setVisibility(View.VISIBLE);
		llQuestion.setVisibility(View.GONE);
		
		TextView txAns1=(TextView) llAnswer.findViewById(R.id.tx_answer_1);
		TextView txAns2=(TextView) llAnswer.findViewById(R.id.tx_answer_2);
		TextView txAns3=(TextView) llAnswer.findViewById(R.id.tx_answer_3);
		
		for(int i=0;i<ansList.size();i++){
			Answer item =ansList.get(i);
			if(item.answer_str_id.equals(answer)){
				txProgress.setText("测试结果");
				txAns1.setText(item.answer_1);
				txAns2.setText(item.answer_2);
				if(item.answer_3!=null  &&  !"".equals(item.answer_3)){
					txAns3.setText(item.answer_3);
				}
			}
		}
		
	}

	private void getAnswer() {
		if(curQuestionId%7==0){
			switch (curQuestionId) {
			case 7:
				if(value>0) answer = answer +"E";
				else answer +="I";
				break;
			case 14:
				if(value>0) answer = answer +"N";
				else answer +="S";
				break;
			case 21:
				if(value>0) answer = answer +"F";
				else answer +="T";
				break;
			case 28:
				if(value>0) answer = answer +"J";
				else answer +="P";
				break;
			default:
				break;
			}
		}
	}

}
