package com.example.ifttw.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifttw.R;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.MyViewHolder> {

    private Context context;
    private List<Routine> listRoutine;
    private OnItemClickCallback onItemClickCallback;
    private OnItemLongClickCallback onItemLongClickCallback;
    private int index;

    public RoutineAdapter(Context context, List<Routine> listRoutine) {
        this.context = context;
        this.listRoutine = listRoutine;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.routine_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Routine routine = listRoutine.get(position);
        holder.tvFunctionality.setText(routine.getFunctionality());
        holder.tvCondition.setText(routine.getCondition());
        holder.tvAction.setText(routine.getAction());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onItemClickCallback.onItemClicked(listRoutine.get(holder.getAdapterPosition()));
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                onItemLongClickCallback.onItemLongClicked(index, listRoutine.get(holder.getAdapterPosition()), view);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRoutine.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFunctionality;
        public TextView tvCondition;
        public TextView tvAction;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFunctionality = itemView.findViewById(R.id.tv_functionality);
            tvCondition = itemView.findViewById(R.id.tv_condition);
            tvAction = itemView.findViewById(R.id.tv_action);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Routine routine);
    }

    public void setOnItemLongClickCallback(OnItemLongClickCallback onItemLongClickCallback, int index){
        this.onItemLongClickCallback = onItemLongClickCallback;
        this.index=index;
    }

    public interface OnItemLongClickCallback{
        void onItemLongClicked(int index, Routine routine, View view);
    }
}
