package edu.galileo.android.tipcalc.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.tipcalc.R;
import edu.galileo.android.tipcalc.activity.TipDetailActivity;
import edu.galileo.android.tipcalc.adapters.OnItemClickListener;
import edu.galileo.android.tipcalc.adapters.TipAdapter;
import edu.galileo.android.tipcalc.beans.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentLlistener, OnItemClickListener{
    @Bind(R.id.recyclerView)
    RecyclerView recylerView;
    private TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this, view);
        initAdapter();
        initRecyclerView();
        // Inflate the layout for this fragment
        return view;
    }

    private void initRecyclerView() {
        recylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerView.setAdapter(adapter);
    }

    private void initAdapter() {
        if(adapter == null){
            adapter = new TipAdapter(getActivity().getApplicationContext(), this);
        }
    }


    @Override
    public void addTolist(TipRecord record) {
        adapter.add(record);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord record) {
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.billTotalKey, record.getBill());
        intent.putExtra(TipDetailActivity.tipKey, record.getTip());
        intent.putExtra(TipDetailActivity.dateKey, record.getDateFormatted());
        startActivity(intent);
    }
}
