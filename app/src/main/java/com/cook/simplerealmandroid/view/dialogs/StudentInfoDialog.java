package com.cook.simplerealmandroid.view.dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.cook.simplerealmandroid.R;

/**
 * Created by roma on 04.11.15.
 */
public class StudentInfoDialog extends DialogFragment implements View.OnClickListener {

    private EditText etName, etEmail, etBirthday;
    private Button btAdd;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_student, container);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        etName = (EditText) view.findViewById(R.id.et_name);
        etEmail = (EditText) view.findViewById(R.id.et_email);
        etBirthday = (EditText) view.findViewById(R.id.et_birthday);
    }

    @Override
    public void onClick(View v) {

    }
}
