package com.example.criminallintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    public Dialog onCreateDialog(Bundle saveInstanceState){
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}
