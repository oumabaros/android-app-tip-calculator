package com.cfca.oyaba;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class TipCalculatorActivity extends Activity {
	
	private static final NumberFormat currencyFormat=NumberFormat.getCurrencyInstance();
	private static final NumberFormat percentFormat=NumberFormat.getPercentInstance();
	
	private double billAmount=0.0;
	private double customPercent=0.18;
	private TextView amountDisplayTextView;
	private TextView percentCustomTextView;
	private TextView tip15TextView;
	private TextView total15TextView;
	private TextView tipCustomTextView;
	private TextView totalCustomTextView;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        
        amountDisplayTextView=(TextView) findViewById(R.id.amountDisplayTextView);
        percentCustomTextView=(TextView) findViewById(R.id.percentCustomTextView);
        tip15TextView=(TextView) findViewById(R.id.tip15TextView);
        total15TextView=(TextView) findViewById(R.id.total15TextView);
        tipCustomTextView=(TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView=(TextView) findViewById(R.id.totalCustomTextView);
        
        amountDisplayTextView.setText(currencyFormat.format(billAmount));
        updateStandard();
        updateCustom();
        
        EditText amountEditText=(EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        
        SeekBar customeTipSeekBar=(SeekBar) findViewById(R.id.customTipSeekBar);
        customeTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }
    
    private void updateStandard(){
    	double fifteenPercentTip=billAmount*0.15;
    	double fifteenPercentTotal=billAmount+fifteenPercentTip;
    	
    	tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
    	total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
    }
    
    private void updateCustom(){
    	double customTip=billAmount*customPercent;
    	double customTotal=billAmount+customTip;
    	
    	tipCustomTextView.setText(currencyFormat.format(customTip));
    	totalCustomTextView.setText(currencyFormat.format(customTotal));
    }
    
    private OnSeekBarChangeListener customSeekBarListener=new OnSeekBarChangeListener(){
    	@Override
    	public void onProgressChanged(SeekBar seekBar,int progress,boolean fromUser){
    		customPercent=progress/100.0;
    		updateCustom();
    	}
    	
    	@Override
    	public void onStartTrackingTouch(SeekBar seekBar){
    		
    	}
    	
    	@Override
    	public void onStopTrackingTouch(SeekBar seekBar){
    		
    	}
    };
    
    private TextWatcher amountEditTextWatcher=new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
			try{
				billAmount=Double.parseDouble(s.toString())/100.0;
			}
			catch(NumberFormatException e){
				billAmount=0.0;
			}
			
			amountDisplayTextView.setText(currencyFormat.format(billAmount));
			updateStandard();
			updateCustom();
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
	};

    
}
