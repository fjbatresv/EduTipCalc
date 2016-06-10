package edu.galileo.android.tipcalc.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.galileo.android.tipcalc.R;
import edu.galileo.android.tipcalc.TipCalcApp;
import edu.galileo.android.tipcalc.beans.TipRecord;
import edu.galileo.android.tipcalc.fragments.TipHistoryListFragment;
import edu.galileo.android.tipcalc.fragments.TipHistoryListFragmentLlistener;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentLlistener fragmentListener;
    private final static int tipStepChange = 1;
    private final static int defaultTipPercentage = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        TipHistoryListFragment fragment =
                (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentLlistener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.actionAbout){
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit(){
        hideKeyBoard();
        String strInputTotal = inputBill.getText().toString().trim();
        if(!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            TipRecord record = new TipRecord(total, tipPercentage, new Date());
            String strTip = String.format(getString(R.string.global_message_tip), record.getTip());
            fragmentListener.addTolist(record);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        hideKeyBoard();
        handleTipChange(tipStepChange);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        hideKeyBoard();
        handleTipChange(-tipStepChange);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear(){
        fragmentListener.clearList();
    }

    private void handleTipChange(int change) {
        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if(tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    private void hideKeyBoard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try{
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }catch(NullPointerException ex){
            Log.e(getLocalClassName(), Log.getStackTraceString(ex));
        }
    }

    private void about(){
        TipCalcApp app = (TipCalcApp)getApplication();
        String stUrl = app.getAboutUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(stUrl));
        startActivity(intent);
    }

    public int getTipPercentage() {
        int tipPercentage = defaultTipPercentage;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        if(!strInputTipPercentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }else{
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }
}
