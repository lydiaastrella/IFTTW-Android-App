package com.example.ifttw.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifttw.R;

import java.util.List;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.MyViewHolder> {

    private Context context;
    private List<Routine> listRoutine;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Routine routine = listRoutine.get(position);
        holder.tvFunctionality.setText(routine.getFunctionality());
        holder.tvCondition.setText(routine.getCondition());
        holder.tvAction.setText(routine.getAction());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(context, routine.getFunctionality(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listRoutine.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
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
}
