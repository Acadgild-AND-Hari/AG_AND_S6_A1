package com.hari.aag.simpleaddition;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdditionFragment extends Fragment
    implements View.OnClickListener{

    @BindView(R.id.id_num) EditText numET;
    @BindView(R.id.id_submit) Button submitBtn;
    @BindView(R.id.id_num_info) TextView numInfoTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addition, container, false);
        ButterKnife.bind(this, rootView);
        submitBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_submit:
                numInfoTV.setText(String.format(
                        getResources().getString(R.string.str_number_info),
                        numET.getText().toString()));
                break;
        }
    }
}
