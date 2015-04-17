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

/**
 * 
 * @description  一题测试  <br />
 * @author CxiaoX
 *
 * 2015年4月18日上午1:06:13
 */
public class OneSelectTestActivity extends PortraitActivity implements OnClickListener {

	private static final String TAG ="OneSelectTestActivity";
	
	//参数相关
	private int typeId =1;
	private int testId =3;
	
	//数据相关
	private DataManagerUtil datautil;
	private List<Question> list;
	private List<Answer> ansList;
	private Question Curitem;
	private String myAnswer;
	
	//控件相关
	private HeadView headView;
	private TextView txProgress;
	private TextView txQuestion;
	private RadioButton rbAnsA;
	private RadioButton rbAnsB;
	private RadioButton rbAnsC;
	private LinearLayout llAnswer;
	private LinearLayout llQuestion;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datautil = new DataManagerUtil(this);
		setContentView(R.layout.activity_skip_test);
		initParams();
		initView();
		initViewData();
	}
	

	private void initParams() {
//		typeId = getIntent().getIntExtra(EXTRA_TYPE_ID, 1);
//		testId = getIntent().getIntExtra(EXTRA_TEST_ID, 1);
		
		list=Question.getQuestionBySql(typeId, testId, datautil.openDatabase());
		ansList = Answer.getAnswerBySql(typeId, testId, datautil.openDatabase());
		datautil.closeDatabase();
		
		Log.i(TAG, "list size ="+ list.size());
		Log.i(TAG, "ansList size ="+ ansList.size());
		
		
	}

	private void initView() {
		initHeadView();
		
		txProgress = (TextView) findViewById(R.id.tx_progress);
		txQuestion = (TextView) findViewById(R.id.tx_question);
		rbAnsA = (RadioButton) findViewById(R.id.rb_ans_a);
		rbAnsB = (RadioButton) findViewById(R.id.rb_ans_b);
		rbAnsC = (RadioButton) findViewById(R.id.rb_ans_c);
		llAnswer = (LinearLayout) findViewById(R.id.layout_answer);
		llQuestion = (LinearLayout) findViewById(R.id.ll_question);
		
		
		rbAnsA.setOnClickListener(this);
		rbAnsB.setOnClickListener(this);
		rbAnsC.setOnClickListener(this);
		
		
	}
	
	private void showAnswer() {
		llAnswer.setVisibility(View.VISIBLE);
		llQuestion.setVisibility(View.GONE);
		
		TextView txAns1=(TextView) llAnswer.findViewById(R.id.tx_answer_1);
		TextView txAns2=(TextView) llAnswer.findViewById(R.id.tx_answer_2);
		TextView txAns3=(TextView) llAnswer.findViewById(R.id.tx_answer_3);
		
		for(int i=0;i<ansList.size();i++){
			Answer item =ansList.get(i);
			if(item.answer_str_id.equals(myAnswer)){
				txProgress.setText("测试结果");
				txAns1.setText(item.answer_1);
				txAns2.setText(item.answer_2);
				if(item.answer_3!=null && !"".equals(item.answer_3) ){
						txAns3.setText(item.answer_3);
				} 
			}
		}
		
	}
	
	private void initHeadView() {
		headView = (HeadView) findViewById(R.id.head_view);
		setHeadViewTitle(typeId);
		headView.setLeftOnClickListener(this);
		
	}
	
	
	private int curQuestionId=1;
	private void initViewData() {
		showQuestion();
	}
	
	
	/**
	 * 得到questionId 下的题目
	 * @return Question
	 */
	private Question getQuestionById(int questionId){
		for(int i=0;i<list.size();i++){
			Question item = list.get(i);
			if(item.question_id == questionId)
				return  item;
		}
		return null;
	}
	
	
	
	
	private void showQuestion() {
		Curitem =getQuestionById(curQuestionId);
	
		txProgress.setText(String.format("进度%s/%s", curQuestionId,list.size()));
		txQuestion.setText(Curitem.question_content);
		rbAnsA.setText(Curitem.answer_a);
		rbAnsB.setText(Curitem.answer_b);
		rbAnsC.setText(Curitem.answer_c);
//		if(Curitem.answer_c!=null&&  !"".equals(Curitem.answer_c)){
//			rbAnsC.setVisibility(View.VISIBLE);
//			rbAnsC.setText(Curitem.answer_c);
//		}else{
//			rbAnsC.setVisibility(View.GONE);
//		}
//		
	}

	private void setHeadViewTitle(int typeId){
		String title ="";
		if(typeId==0) {
			title = "职业测试";
		}else if(typeId == 1){
			title ="性格测试";
		}else if(typeId ==2){
			title = "情感测试";
		}
		headView.setTitle(title);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case HeadView.LEFT_ICON:
			finish();
			break;
		case R.id.rb_ans_a:
			onClickAnswer(Curitem.value_a);
			 
			break;
		case R.id.rb_ans_b:
			onClickAnswer(Curitem.value_b);
			break;
		case R.id.rb_ans_c:
			onClickAnswer(Curitem.value_c);
			break;

		default:
			break;
		}
//		showQuestion();
	}
	
	private void onClickAnswer(String value){
		myAnswer = value;
		showAnswer();
			
	}
	
	/**
	 * 判断是否是字母
	 */
	private boolean isLetter(String value){
		if("A".equals(value) || "B".equals(value) ||
		   "C".equals(value) || "D".equals(value) ||
		   "E".equals(value) || "F".equals(value)){
			myAnswer = value;
			return true;
		}
		return false;
	}

}
