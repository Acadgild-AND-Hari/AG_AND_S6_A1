package com.hari.aag.simpleaddition;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

public class AdditionFragment extends Fragment
    implements View.OnClickListener{

    private final String LOG_TAG = AdditionFragment.class.getSimpleName();
    private final String PREFS_NAME = AdditionFragment.class.getSimpleName();

    private final String NUM_INPUT = "numInput";

    private String numStr = "";

    @BindView(R.id.id_num) EditText numET;
    @BindView(R.id.id_submit) Button submitBtn;
    @BindView(R.id.id_num_info) TextView numInfoTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "Inside - onCreate");
        readValuesFromPrefs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_addition, container, false);
        ButterKnife.bind(this, rootView);
        submitBtn.setOnClickListener(this);
        Log.d(LOG_TAG, "Inside - onCreateView");
        updateValueToUI();
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "Inside - onPause");
        saveValuesToPrefs();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_submit:
                String numStr1 = numET.getText().toString();
                if (numStr1.isEmpty()){
                    Toast.makeText(getContext(), "You have not entered any number.", Toast.LENGTH_SHORT).show();
                    Log.d(LOG_TAG, "You have not entered any number.");
                    break;
                }

                if (numStr.equals(numStr1)){
                    Toast.makeText(getContext(), "Numbers are same", Toast.LENGTH_SHORT).show();
                    Log.d(LOG_TAG, "Skipped - Numbers are same");
                    break;
                }

                numStr = numStr1;
                updateValueToUI();
                break;
        }
    }

    private void updateValueToUI(){
        if (!numStr.isEmpty()) {
            numInfoTV.setText(String.format(
                    getResources().getString(R.string.str_number_info), numStr));
        }
    }

    private void readValuesFromPrefs(){
        SharedPreferences mySharedPrefs = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        numStr = mySharedPrefs.getString(NUM_INPUT, "");

        Log.d(LOG_TAG, "Values Read from Prefs.");
        dumpPrefValues();
    }

    private void saveValuesToPrefs(){
        SharedPreferences.Editor prefsEditor = getActivity().getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();

        prefsEditor.putString(NUM_INPUT, numStr);
        prefsEditor.commit();

        Log.d(LOG_TAG, "Values Saved to Prefs.");
        dumpPrefValues();
    }

    private void dumpPrefValues(){
        Log.d(LOG_TAG, NUM_INPUT + " - " + numStr);
    }
}
