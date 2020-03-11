package com.example.ifttw.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ifttw.R;
import com.example.ifttw.recyclerview.Routine;
import com.example.ifttw.recyclerview.RoutineAdapter;
import com.example.ifttw.recyclerview.SQLiteDatabaseHandler;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private SQLiteDatabaseHandler db;
    private ArrayList<Routine> listActiveRoutine;
    private ArrayList<Routine> listInactiveRoutine;
    private RecyclerView recyclerView;
    //private RoutineAdapter adapter;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
//        int index = 1;
//        if (getArguments() != null) {
//            index = getArguments().getInt(ARG_SECTION_NUMBER);
//        }
//        pageViewModel.setIndex(index);
//    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
//        View root = inflater.inflate(R.layout.fragment_main, container, false);
//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
//        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_routine);
        recyclerView.setHasFixedSize(true);

        db = new SQLiteDatabaseHandler(getContext());

//        Routine routine1 = new Routine("4 Steps", "When target (6000) achieved", "Notify me");
//        Routine routine2 = new Routine("5 Phone Automation", "At 00:00", "Mute Phone");
//        Routine routine3 = new Routine("6 Battery Low Automation", "When battery less than 15%", "Turn off wifi");
//        Routine routine4 = new Routine("2 Reminder", "At 01.00", "Notify me that this is an inactive routine");
//        db.addRoutine(routine1, true);
//        db.addRoutine(routine2, true);
//        db.addRoutine(routine3, true);
//        db.addRoutine(routine4, false);

        listActiveRoutine = new ArrayList<>();
        listInactiveRoutine = new ArrayList<>();
//        listActiveRoutine.add(routine1);
//        listActiveRoutine.add(routine2);
//        listActiveRoutine.add(routine3);
//        listInactiveRoutine.add(routine4);
        listActiveRoutine.addAll(db.allRoutines(true));
        listInactiveRoutine.addAll(db.allRoutines(false));
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        int index = getArguments().getInt(ARG_SECTION_NUMBER);
        RoutineAdapter adapter = new RoutineAdapter(getContext(),null);
        if(index== 1){
            adapter = new RoutineAdapter(getContext(),listActiveRoutine);
        }else if(index == 2){
            adapter = new RoutineAdapter(getContext(), listInactiveRoutine);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

//        adapter.setOnItemClickCallback(new adapter.OnItemClickCallback() {
//            @Override
//            public void onItemClicked(Routine data) {
//                showSelectedRoutine(data);
//            }
//        });
    }
//
//    private void showSelectedRoutine(Routine routine) {
//        Intent intent = new Intent(getActivity(), DetailActivity.class);
//        intent.putExtra(DetailActivity.EXTRA_FILM, film);
//        startActivity(intent);
//    }
}