package edu.galileo.android.tipcalc.activity;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.tipcalc.R;

public class TipDetailActivity extends AppCompatActivity {
    @Bind(R.id.txtBillTotal)
    TextView txtBillTotal;
    @Bind(R.id.txtTip)
    TextView txtTip;
    @Bind(R.id.txtTimestamp)
    TextView txtTimestamp;

    public final static String tipKey = "tip";
    public final static String dateKey = "timestamp";
    public final static String billTotalKey = "total";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_tip_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String strTotal = String.format(getString(R.string.tipdetail_message_total), intent.getDoubleExtra(billTotalKey, 0d));
        String strTip = String.format(getString(R.string.global_message_tip), intent.getDoubleExtra(tipKey, 0d));
        txtTimestamp.setText(intent.getStringExtra(dateKey));
        txtBillTotal.setText(strTotal);
        txtTip.setText(strTip);
       // ActionBar actionBar = getSupportActionBar();
        //actionBar.setHomeButtonEnabled(true);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/


}
