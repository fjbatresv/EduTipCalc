package edu.galileo.android.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.galileo.android.tipcalc.R;
import edu.galileo.android.tipcalc.beans.TipRecord;

/**
 * Created by javie on 1/06/2016.
 */
public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private Context context;
    private List<TipRecord> dataset;
    private OnItemClickListener onItemClickListener;

    public  TipAdapter(Context context, OnItemClickListener onItemClickListener){
        this.dataset = new ArrayList<TipRecord>();
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Creamos la vista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lineal_layout, parent, false);
        return new ViewHolder(view);
    }

    public void add(TipRecord record){
        dataset.add(0, record);
        notifyDataSetChanged();
    }

    public void clear(){
        dataset.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Cargamos el contenido
        TipRecord element = dataset.get(position);
        String strTip = String.format(context.getString(R.string.global_message_tip), element.getTip());
        holder.txtContent.setText(strTip);
        holder.txtTimestampList.setText(element.getDateFormatted());
        holder.setOnItemClickListener(element, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.txtContent)
        TextView txtContent;
        @Bind(R.id.txtTimestampList)
        TextView txtTimestampList;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });
        }
    }
}

