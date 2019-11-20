package com.android2taskapp.ui.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android2taskapp.App;
import com.android2taskapp.FormActivity;
import com.android2taskapp.R;
import com.android2taskapp.Task;
import com.android2taskapp.TaskAdapter;
import com.android2taskapp.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private static TaskAdapter adapter;
    private static List<Task> list;

    private static List<Task> sortedList;
    private static List<Task> notSortedList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        initList();
        return root;
    }

    private void initList() {
        list = new ArrayList<>();
//        list.add(new Task("Jood", ""));
//        list.add(new Task("Dast", ""));
//        list.add(new Task("Raha", ""));
//        list.add(new Task("Dani", ""));



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        adapter= new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("task", list.get(position));
                startActivity(intent);
//                Toast.makeText(getContext(), "pos = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Внимание");
                builder.setMessage("Вы точно хотите удалить запись");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Task task = list.get(position);

                        App.getDatabase().taskDao().delete(task);
                    }
                });
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        App.getDatabase().taskDao().getAll().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
               notSortedList = tasks;

                list.clear();
                list.addAll(tasks);
                adapter.notifyDataSetChanged();
            }
        });

        App.getDatabase().taskDao().getSortedList().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                sortedList = tasks;

            }
        });

    }

    public static void setNotSortedList(){
     list.clear();
     list.addAll(notSortedList);
     adapter.notifyDataSetChanged();
    }

    public static void setSortedList(){
        list.clear();
        list.addAll(sortedList);
        adapter.notifyDataSetChanged();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
//            Task task = (Task) data.getSerializableExtra("task");
//            Log.e("TAG", "title: " + task.getTitle());
//            list.add(0, task);
//            adapter.notifyDataSetChanged();
//        }
//    }
}

