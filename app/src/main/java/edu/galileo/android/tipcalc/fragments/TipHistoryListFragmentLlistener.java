package edu.galileo.android.tipcalc.fragments;

import edu.galileo.android.tipcalc.beans.TipRecord;

/**
 * Created by javie on 1/06/2016.
 */
public interface TipHistoryListFragmentLlistener {
    void addTolist(TipRecord record);
    void clearList();
}
