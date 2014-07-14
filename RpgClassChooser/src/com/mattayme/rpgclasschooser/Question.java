package com.mattayme.rpgclasschooser;

public class Question {

	private int mQuestion;
	private int mNumButtons;
	private int mButton1Text;
	private int mButton2Text;
	private int mButton3Text;
	private int mQuestionId;
	private String mAttribute1;
	private String mAttribute2;
	private String mAttribute3;
	

	public Question(int question,int numButtons, int button1Text, int button2Text,int questionId){
		mQuestion = question;
		mNumButtons = numButtons;
		mButton1Text = button1Text;
		mButton2Text = button2Text;
		mQuestionId = questionId;
	}
	public Question(int question, int numButtons, int button1Text, int button2Text, int button3Text,int questionId){
		mQuestion = question;
		mNumButtons = numButtons;
		mButton1Text = button1Text;
		mButton2Text = button2Text;
		mButton3Text = button3Text;
		mQuestionId = questionId;
	}
	public Question(int question,int numButtons, int button1Text, int button2Text, String attribute1,String attribute2,int questionId){
		mQuestion = question;
		mNumButtons = numButtons;
		mButton1Text = button1Text;
		mButton2Text = button2Text;
		mAttribute1 = attribute1;
		mAttribute2 = attribute2;
		mQuestionId = questionId;
	}

	public Question(int question, int numButtons, int button1Text, int button2Text, int button3Text, String attribute1,String attribute2, String attribute3,int questionId){
		mQuestion = question;
		mNumButtons = numButtons;
		mButton1Text = button1Text;
		mButton2Text = button2Text;
		mButton3Text = button3Text;
		mAttribute1 = attribute1;
		mAttribute2 = attribute2;
		mAttribute3 = attribute3;
		mQuestionId = questionId;
		
	}

	public int getQuestionId() {
		return mQuestionId;
	}
	public String getAttribute1() {
		return mAttribute1;
	}
	public String getAttribute2() {
		return mAttribute2;
	}
	
	public String getAttribute3() {
		return mAttribute3;
	}
	public int getNumButtons() {
		return mNumButtons;
	}
	public void setNumButtons(int numButtons) {
		mNumButtons = numButtons;
	}
	public int getButton1Text() {
		return mButton1Text;
	}

	public void setButton1Text(int button1Text) {
		mButton1Text = button1Text;
	}

	public int getButton2Text() {
		return mButton2Text;
	}

	public void setButton2Text(int button2Text) {
		mButton2Text = button2Text;
	}

	public int getButton3Text() {
		return mButton3Text;
	}

	public void setButton3Text(int button3Text) {
		mButton3Text = button3Text;
	}
	
	public int getQuestion() {
		return mQuestion;
	}

	public void setQuestion(int question) {
		mQuestion = question;
	}
}
