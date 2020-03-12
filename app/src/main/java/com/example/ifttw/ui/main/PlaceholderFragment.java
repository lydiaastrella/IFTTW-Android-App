package com.example.ifttw.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.ifttw.R;
import com.example.ifttw.recyclerview.Routine;
import com.example.ifttw.recyclerview.RoutineAdapter;
import com.example.ifttw.recyclerview.SQLiteDatabaseHandler;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private SQLiteDatabaseHandler db;
    private ArrayList<Routine> listActiveRoutine;
    private ArrayList<Routine> listInactiveRoutine;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    PlaceholderFragment(int index){
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        this.setArguments(bundle);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        swipeRefreshLayout = rootView.findViewById(R.id.fragment_main);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color.holo_orange_dark, android.R.color.holo_blue_dark);

        swipeRefreshLayout.post(new Runnable(){

            @Override
            public void run() {
                //swipeRefreshLayout.setRefreshing(true);
                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_routine);
        recyclerView.setHasFixedSize(true);

        db = new SQLiteDatabaseHandler(getContext());

        listActiveRoutine = new ArrayList<>();
        listInactiveRoutine = new ArrayList<>();
        listActiveRoutine.addAll(db.allRoutines(true));
        listInactiveRoutine.addAll(db.allRoutines(false));
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        RoutineAdapter adapter = new RoutineAdapter(getContext(), null);
        if (index == 1) {
            adapter = new RoutineAdapter(getContext(), listActiveRoutine);
        } else if (index == 2) {
            adapter = new RoutineAdapter(getContext(), listInactiveRoutine);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickCallback(new RoutineAdapter.OnItemClickCallback(){
            @Override
            public void onItemClicked(Routine routine){
                showSelectedRoutine(routine);
            }
        });

        adapter.setOnItemLongClickCallback(new RoutineAdapter.OnItemLongClickCallback(){
            @Override
            public void onItemLongClicked(int index, Routine routine, View view) {
                showLongClickMenu(index, routine, view);
            }
        }, index);
    }

    private  void showSelectedRoutine(Routine routine){
        Log.d("buka halaman ", "silakan ubah jadi buka halaman yang sesuai routine");
    }

    private void showLongClickMenu(final int index, final Routine routine, View v){
        Log.d("waw long click","long click"+index);

        PopupMenu popup = new PopupMenu(getContext(), v);

        MenuInflater inflater = popup.getMenuInflater();
        if(index == 1){
            inflater.inflate(R.menu.long_click_active, popup.getMenu());
        }else{
            inflater.inflate(R.menu.long_click_inactive, popup.getMenu());
        }

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_activate:
                        db.deleteData(routine.getFunctionality(),routine.getCondition(),routine.getAction(),false);
                        db.addRoutine(routine,true);

                        listActiveRoutine.clear();
                        listInactiveRoutine.clear();
                        listActiveRoutine.addAll(db.allRoutines(true));
                        listInactiveRoutine.addAll(db.allRoutines(false));

                        recyclerView.getAdapter().notifyDataSetChanged();

                        Log.d("activate", "activate");
                        return true;
                    case R.id.menu_deactivate:
                        db.deleteData(routine.getFunctionality(),routine.getCondition(),routine.getAction(),true);
                        db.addRoutine(routine,false);

                        listActiveRoutine.clear();
                        listInactiveRoutine.clear();
                        listActiveRoutine.addAll(db.allRoutines(true));
                        listInactiveRoutine.addAll(db.allRoutines(false));

                        recyclerView.getAdapter().notifyDataSetChanged();

                        Log.d("deactivate", "deactivate");
                        return true;
                    case R.id.menu_delete:
                        boolean active;
                        if(index == 1){
                            active=true;
                            db.deleteData(routine.getFunctionality(),routine.getCondition(),routine.getAction(),active);
                            listActiveRoutine.clear();
                            listActiveRoutine.addAll(db.allRoutines(true));
                        }else {
                            active = false;
                            db.deleteData(routine.getFunctionality(), routine.getCondition(), routine.getAction(), active);
                            listInactiveRoutine.clear();
                            listInactiveRoutine.addAll(db.allRoutines(false));

                        }

                        recyclerView.getAdapter().notifyDataSetChanged();

                        Log.d("delete", "delete");
                        return true;
                    default:
                        return false;
                }
            }
        });

        popup.show();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        listActiveRoutine.clear();
        listInactiveRoutine.clear();
        listActiveRoutine.addAll(db.allRoutines(true));
        listInactiveRoutine.addAll(db.allRoutines(false));
        recyclerView.getAdapter().notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }
}