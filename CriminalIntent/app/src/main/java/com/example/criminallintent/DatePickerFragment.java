package com.example.criminallintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class DatePickerFragment extends androidx.fragment.app.DialogFragment {

    public Dialog onCreateDialog(Bundle saveInstanceState){
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date,null);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}
