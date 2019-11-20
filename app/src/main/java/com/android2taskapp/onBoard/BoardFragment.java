package com.android2taskapp.onBoard;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.ColorRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2taskapp.MainActivity;
import com.android2taskapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {


    public BoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_board, container, false);
        int pos = getArguments().getInt("pos");
        TextView textView = view.findViewById(R.id.textViewPager);
        ImageView imageView = view.findViewById(R.id.imageViewPager);
        Button buttonStart = view.findViewById(R.id.buttonStart);

        switch (pos) {
            case 0:
                textView.setText("Привет!");
                imageView.setImageResource(R.drawable.grass_page1);
                buttonStart.setVisibility(View.INVISIBLE);
                view.setBackgroundColor(getResources().getColor(R.color.colorBackground3));
                break;
            case 1:
                textView.setText("Как дела?");
                imageView.setImageResource(R.drawable.water_page2);
                buttonStart.setVisibility(View.INVISIBLE);
                view.setBackgroundColor(getResources().getColor(R.color.colorBackground1));

                break;
            case 2:
                textView.setText("Готов начать?");
                imageView.setImageResource(R.drawable.fire_page3);
                view.setBackgroundColor(getResources().getColor(R.color.colorBackground2));

                break;
        }
        buttonStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (getActivity() != null ) {
                    getActivity().finish();
                    SharedPreferences preferences = getActivity().getSharedPreferences
                            ("settings", Context.MODE_PRIVATE);
                    preferences.edit().putBoolean("isShown", true).apply();

                    startActivity(new Intent(getContext(), MainActivity.class));


                }
            }
        });
//        textView.setText("Привет");

        return view;

    }

}
