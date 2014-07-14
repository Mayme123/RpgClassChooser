package com.mattayme.rpgclasschooser;

import com.google.android.gms.ads.*;
import com.mattayme.rpgclasschooser.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import android.R.color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChooserActivity extends Activity {
	
	private final static String KEY_QUESTION_ID="id";
	private final static String KEY_CLASS_TITLES="classes";
	
	private final static int MENU_RESET = Menu.FIRST;
	private final static int MENU_ABOUT = Menu.FIRST +1;
	
	private RpgClass[] mClassBank;
	private Question[] mQuestionBank;
	private int id=0;
	
	private Set<RpgClass> mClasses;
	private String[] attributes;
	private RpgClass barbarian;
	private RpgClass knight;
	private RpgClass samurai;
	private RpgClass thief;
	private RpgClass spellBlade;
	private RpgClass monk;
	private RpgClass darkKnight;
	private RpgClass blueMage;
	private RpgClass assassin;
	private RpgClass pirate;
	private RpgClass paladin;
	private RpgClass bowDagger;
	private RpgClass trapper;
	private RpgClass blackMage;
	private RpgClass redMage;
	private RpgClass summoner;
	private RpgClass illusionist;
	private RpgClass gambler;
	private RpgClass whiteMage;
	private RpgClass witch;
	private RpgClass sniper;
	private RpgClass beastMaster;
	private RpgClass alchemist;
	private RpgClass bardDancer;
	
	private Question mDmgType1Question;
	private Question mDmgType2Question;
	private Question mRangeQuestion;
	private Question mArmorQuestion;
	private Question mPlayStyle1Question;
	private Question mPlayStyle2Question;
	private Question mWeaponQuestion;
	private Question mStealthQuestion;
	private Question mRangedWeaponQuestion;
	private Question mAlignmentQuestion;
	private Question mLuckQuestion;
	private Question mElementalQuestion;
	private Question mSummonQuestion;
	private Question mSupportTypeQuestion;
	
	private Question mCurrentQuestion;
	private Question mNextQuestion;
	private int response;
	
	private TextView mQuestionTextView;
	private Button mLeftButton;
	private Button mMiddleButton;
	private Button mRightButton;
	private TextView mDebugTextView;
	
	private Typeface typeface;
	
	private void updateQuestion(Question question){
		mCurrentQuestion=question;
		mQuestionTextView.setText(question.getQuestion());
		if(question.getNumButtons()==2){
			mMiddleButton.setVisibility(Button.GONE);
			mLeftButton.setText(question.getButton1Text());
			mRightButton.setText(question.getButton2Text());
		}else{
			mMiddleButton.setVisibility(Button.VISIBLE);
			mLeftButton.setText(question.getButton1Text());
			mMiddleButton.setText(question.getButton2Text());
			mRightButton.setText(question.getButton3Text());
		}
	}
	
	private boolean isDownToOneClass(){
		Boolean downToOne = false;
		if(mClasses.size()==1){
			downToOne = true;
		}
		return downToOne;
	
	}
	private ArrayList<String> retainClasses(){
		ArrayList<String> classesToRetain = new ArrayList<String>();
		for(RpgClass element:mClasses){
			classesToRetain.add(element.getClassTitle());
		}
		return classesToRetain;
	}
	private void populateClassSet(ArrayList<String> classes){
		mClasses.clear();
		for(String element:classes){
			for(RpgClass classBankItem:mClassBank){
				if(classBankItem.getClassTitle().equals(element)){
					mClasses.add(classBankItem);
				}
			}
		}
	}
	private void showClassResultActivity(){
		String classTitle="";
		int classDescription=0;
		for(RpgClass element:mClasses){
			classTitle = element.getClassTitle();
			classDescription = element.getDescription();
		}
		Intent i = new Intent(ChooserActivity.this, ClassDescriptionActivity.class);
		i.putExtra(ClassDescriptionActivity.EXTRA_CLASS_TITLE, classTitle);
		i.putExtra(ClassDescriptionActivity.EXTRA_CLASS_DESCRIPTION, classDescription);
		startActivity(i);
	}
	private void resetClassSet(){
		for(RpgClass element: mClassBank){
			mClasses.add(element);
		}
	}
	private void determineNextQuestion(Question currentQuestion){
		if(currentQuestion.equals(mRangeQuestion)){
			if(mClasses.contains(barbarian)){
				mNextQuestion = mDmgType1Question;
			}else{
				mNextQuestion = mDmgType2Question;
			}
		}else if(currentQuestion.equals(mDmgType1Question)){
			mNextQuestion = mArmorQuestion;
		}else if(currentQuestion.equals(mDmgType2Question)){
			if(mClasses.contains(assassin)){
				mNextQuestion = mStealthQuestion;
			}else if(mClasses.contains(darkKnight)){
				mNextQuestion = mAlignmentQuestion;
			}else if(mClasses.contains(sniper)){
				mNextQuestion = mPlayStyle1Question;
			}else if(mClasses.contains(alchemist)){
				mNextQuestion = mLuckQuestion;
			}else{
				mNextQuestion = mPlayStyle2Question;
			}
		}else if(currentQuestion.equals(mArmorQuestion)){
			if(mClasses.contains(samurai)){
				mNextQuestion = mWeaponQuestion;
			}else{
				mNextQuestion = mPlayStyle1Question;
			}
		}else if(currentQuestion.equals(mWeaponQuestion)){
			//add switch to ending activity
		}else if(currentQuestion.equals(mStealthQuestion)){
			if(mClasses.contains(pirate)){
				mNextQuestion = mRangedWeaponQuestion;
			}else{
				mNextQuestion = mPlayStyle1Question;
			}
		}else if(currentQuestion.equals(mPlayStyle2Question)){
			if(mClasses.contains(blackMage)){
				mNextQuestion = mElementalQuestion;
			}
			else if(mClasses.contains(summoner)){
				mNextQuestion = mSummonQuestion;
			}else if(mClasses.contains(illusionist)){
				mNextQuestion = mSupportTypeQuestion;
			}
		}
	}
	/*private void debugTextViewUpdate(){
		StringBuilder debug = new StringBuilder();
		
		for(RpgClass classes:mClasses){
			debug.append(classes.getClassTitle());
		}
		mDebugTextView.setText(debug);
	}*/

	private void classElimination(Question question){
		Iterator<RpgClass> it = mClasses.iterator();
		String attributeToKeep="";
		String[] attributes;
		
		if (isDownToOneClass()==false) {
			switch (response) {
			case 1:
				attributeToKeep = question.getAttribute1();
				break;
			case 2:
				attributeToKeep = question.getAttribute2();
				break;
			case 3:
				attributeToKeep = question.getAttribute3();
				break;
			}
			while (it.hasNext()) {
				boolean containsAttribute = false;
				attributes = it.next().getAttributes();
				for (int i = 0; i < attributes.length; i++) {
					if (attributes[i] == attributeToKeep) {
						containsAttribute = true;
					}
				}
				if (containsAttribute == false) {
					it.remove();
				}
			}
		}
		//debugTextViewUpdate();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chooser);
		
		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
		
		//test ads, remove before publishing
		/*AdRequest adRequest = new AdRequest.Builder()
	    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)       // Emulator
	    .addTestDevice("749FB28E81F6431365E4D9CC31F30C2C") // My Galaxy Nexus test phone
	    .build();*/
	    adView.loadAd(adRequest);
		
		mClasses= new LinkedHashSet<RpgClass>();
		
		barbarian = new RpgClass("Barbarian",R.string.barbarian_description);
		attributes = new String[]{"close", "physical", "light", "active"};
		barbarian.setAttributes(attributes);
		
		knight = new RpgClass("Knight",R.string.knight_description);
		attributes = new String[]{"close", "physical", "heavy", "active"};
		knight.setAttributes(attributes);
		
		samurai = new RpgClass("Samurai",R.string.samurai_description);
		attributes = new String[]{"close", "both types", "light", "active","weapon"};
		samurai.setAttributes(attributes);
		
		thief = new RpgClass("Thief",R.string.thief_description);
		attributes = new String[]{"close", "physical", "light", "passive"};
		thief.setAttributes(attributes);
		
		spellBlade = new RpgClass("Spell Blade",R.string.spell_blade_description);
		attributes = new String[]{"close", "both types", "heavy", "active"};
		spellBlade.setAttributes(attributes);
		
		monk = new RpgClass("Monk",R.string.monk_description);
		attributes = new String[]{"close", "both types", "light", "active","bare"};
		monk.setAttributes(attributes);
		
		darkKnight = new RpgClass("Dark Knight",R.string.dark_knight_description);
		attributes = new String[]{"both", "both types", "light", "active","dark alignment"};
		darkKnight.setAttributes(attributes);
		
		blueMage = new RpgClass("Blue Mage",R.string.blue_mage_description);
		attributes = new String[]{"both", "magic", "light", "active"};
		blueMage.setAttributes(attributes);
		
		assassin = new RpgClass("Assassin",R.string.assassin_description);
		attributes = new String[]{"both", "physical", "light", "active","stealthy"};
		assassin.setAttributes(attributes);
		
		pirate = new RpgClass("Pirate",R.string.pirate_description);
		attributes = new String[]{"both", "physical", "light", "active","direct","gun"};
		pirate.setAttributes(attributes);
		
		paladin = new RpgClass("Paladin",R.string.paladin_description);
		attributes = new String[]{"both", "both types", "light", "active","light alignment"};
		paladin.setAttributes(attributes);
		
		bowDagger = new RpgClass("Bow and Dagger Ranger",R.string.bow_dagger_description);
		attributes = new String[]{"both", "physical", "light", "active","direct","bow"};
		bowDagger.setAttributes(attributes);
		
		trapper = new RpgClass("Trapper",R.string.trapper_description);
		attributes = new String[]{"both", "physical", "light", "passive","stealthy"};
		trapper.setAttributes(attributes);
		
		blackMage = new RpgClass("Black Mage",R.string.black_mage_description);
		attributes = new String[]{"ranged", "magic","active","elemental"};
		blackMage.setAttributes(attributes);
		
		redMage = new RpgClass("Red Mage",R.string.red_mage_description);
		attributes = new String[]{"ranged", "magic","mixed","more spells"};
		redMage.setAttributes(attributes);
		
		summoner = new RpgClass("Summoner",R.string.summoner_description);
		attributes = new String[]{"ranged", "magic","mixed","summon"};
		summoner.setAttributes(attributes);
		
		illusionist = new RpgClass("Illusionist",R.string.illusionist_description);
		attributes = new String[]{"ranged", "magic", "passive","hinder"};
		illusionist.setAttributes(attributes);
		
		gambler = new RpgClass("Gambler",R.string.gambler_description);
		attributes = new String[]{"ranged", "both types", "light", "active","chance"};
		gambler.setAttributes(attributes);
		
		whiteMage = new RpgClass("White Mage",R.string.white_mage_description);
		attributes = new String[]{"ranged", "magic", "passive","support"};
		whiteMage.setAttributes(attributes);
		
		witch = new RpgClass("Witch",R.string.witch_description);
		attributes = new String[]{"ranged", "magic", "active","nonelemental"};
		witch.setAttributes(attributes);
		
		sniper = new RpgClass("Sniper",R.string.sniper_description);
		attributes = new String[]{"ranged", "physical", "light", "active"};
		sniper.setAttributes(attributes);
		
		beastMaster = new RpgClass("Beast Master",R.string.beast_master_description);
		attributes = new String[]{"ranged", "physical", "light", "passive"};
		beastMaster.setAttributes(attributes);
		
		alchemist = new RpgClass("Alchemist",R.string.alchemist_description);
		attributes = new String[]{"ranged", "both types", "light", "active","nonchance"};
		alchemist.setAttributes(attributes);
		
		bardDancer = new RpgClass("Bard or Dancer",R.string.bard_dancer_description);
		attributes = new String[]{"ranged", "magic","passive","hinder support"};
		bardDancer.setAttributes(attributes);
		
		mClassBank = new RpgClass[]{barbarian, knight, samurai, thief, spellBlade, monk,darkKnight, blueMage,assassin,pirate, paladin, bowDagger, trapper,
				blackMage, redMage, summoner,illusionist,gambler,whiteMage, witch, sniper,beastMaster, alchemist, bardDancer};
		resetClassSet();
		mRangeQuestion = new Question(R.string.range_question,3,R.string.close_button_text,R.string.both_button_text,R.string.afar_button_text,"close","both","ranged",0);
		mDmgType1Question = new Question(R.string.special_dmg_type_question,2,R.string.yes_button_text,R.string.no_button_text,"both types","physical",1);
		mDmgType2Question = new Question(R.string.dmg_type_question,3,R.string.physical_button_text,R.string.both_button_text,R.string.magic_button_text,"physical","both types","magic",2);
		mArmorQuestion = new Question(R.string.armor_question,2,R.string.light_armor_button_text,R.string.heavy_armor_button_text,"light","heavy",3);
		mPlayStyle1Question = new Question(R.string.playstyle_question,2,R.string.active_button_text,R.string.passive_button_text,"active","passive",4);
		mPlayStyle2Question = new Question(R.string.playstyle_question,3,R.string.active_button_text,R.string.mixed_button_text,R.string.passive_button_text,"active","mixed","passive",5);
		mWeaponQuestion = new Question(R.string.special_weapon_question,2,R.string.bare_button_text,R.string.weapon_button_text,"bare","weapon",6);
		mStealthQuestion = new Question(R.string.special_stealth_question,2,R.string.stealth_button_text,R.string.direct_button_text,"stealthy", "direct",7);
		mRangedWeaponQuestion = new Question(R.string.special_ranged_weapon_question,2,R.string.gun_button_text,R.string.bow_button_text,"gun","bow",8);
		mAlignmentQuestion = new Question(R.string.special_alignment_question,2,R.string.yes_button_text,R.string.no_button_text,"dark alignment","light alignment",9);
		mLuckQuestion = new Question(R.string.special_luck_question,2,R.string.yes_button_text,R.string.no_button_text,"chance","nonchance",10);
		mElementalQuestion = new Question(R.string.special_magic_type_question,2,R.string.yes_button_text,R.string.no_button_text,"elemental","nonelemental",11);
		mSummonQuestion = new Question(R.string.special_summoner_question,2,R.string.summon_button_text,R.string.more_spells_button_text,"summon","more spells",12);
		mSupportTypeQuestion = new Question(R.string.special_support_type_question,3,R.string.hinder_button_text,R.string.hinder_support_button_text,R.string.support_button_text,"hinder","hinder support", "support",13);
		
		mQuestionBank = new Question[]{mRangeQuestion, mDmgType1Question, mDmgType2Question, mArmorQuestion, mPlayStyle1Question, mPlayStyle2Question, mWeaponQuestion,
				mStealthQuestion, mRangedWeaponQuestion, mAlignmentQuestion, mLuckQuestion, mElementalQuestion, mSummonQuestion, mSupportTypeQuestion};
		
		mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
		mLeftButton = (Button) findViewById(R.id.left_button);
		mMiddleButton = (Button) findViewById(R.id.middle_button);
		mRightButton = (Button) findViewById(R.id.right_button);
		//mDebugTextView = (TextView) findViewById(R.id.debug_text_view);
		
		typeface = Typeface.createFromAsset(getAssets(), "fonts/Kingthings_Calligraphica_2.ttf");
		mQuestionTextView.setTypeface(typeface);
		mLeftButton.setTypeface(typeface);
		mMiddleButton.setTypeface(typeface);
		mRightButton.setTypeface(typeface);
		if(savedInstanceState !=null){
			id = savedInstanceState.getInt(KEY_QUESTION_ID);
			populateClassSet(savedInstanceState.getStringArrayList(KEY_CLASS_TITLES));
		}else{
			id = 0;
			resetClassSet();
		}
		updateQuestion(mQuestionBank[id]);
		//debugTextViewUpdate();
		mLeftButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					response = 1;
					classElimination(mCurrentQuestion);
					if(isDownToOneClass()==false){
						determineNextQuestion(mCurrentQuestion);
						updateQuestion(mNextQuestion);
					}else{
						showClassResultActivity();
					}
				
			}
		});
		
		mMiddleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					response = 2;
					classElimination(mCurrentQuestion);
					if(isDownToOneClass()==false){
						determineNextQuestion(mCurrentQuestion);
						updateQuestion(mNextQuestion);
					}else{
						showClassResultActivity();
					}
			}
		});
		mRightButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					if(mCurrentQuestion.getNumButtons()==2){
						response =2;
						classElimination(mCurrentQuestion);
						if (isDownToOneClass()==false){
							determineNextQuestion(mCurrentQuestion);
							updateQuestion(mNextQuestion);
						}else{
							showClassResultActivity();
						}
					}else{
						response =3;
						classElimination(mCurrentQuestion);
						if (isDownToOneClass()==false) {
							determineNextQuestion(mCurrentQuestion);
							updateQuestion(mNextQuestion);
						}else{
							showClassResultActivity();
						}
					}
			}
		});
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putInt(KEY_QUESTION_ID, mCurrentQuestion.getQuestionId());
		savedInstanceState.putStringArrayList(KEY_CLASS_TITLES,retainClasses());
	}
	@Override
	public void onResume(){
		super.onResume();
			if (isDownToOneClass()==true) {
				resetClassSet();
				updateQuestion(mRangeQuestion);
				//debugTextViewUpdate();
			}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(Menu.NONE, MENU_RESET,Menu.NONE,"Reset");
		menu.add(Menu.NONE, MENU_ABOUT,Menu.NONE,"About");
		getMenuInflater().inflate(R.menu.chooser, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case MENU_RESET:
			resetClassSet();
			updateQuestion(mRangeQuestion);
			//debugTextViewUpdate();
			return true;
		case MENU_ABOUT:
			
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
