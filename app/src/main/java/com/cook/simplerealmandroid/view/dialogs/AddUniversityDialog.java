package com.cook.simplerealmandroid.view.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cook.simplerealmandroid.R;

/**
 * Created by roma on 05.11.15.
 */
public class AddUniversityDialog extends DialogFragment implements View.OnClickListener {

    private EditText etUniversityName;
    private Button btAddUniversity;

    private OnAddUniversityClickListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AlertDialogStyle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_university, container);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        etUniversityName = (EditText) view.findViewById(R.id.et_university_name);
        btAddUniversity = (Button) view.findViewById(R.id.bt_add_university);
        btAddUniversity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add_university: {
                if (isUniversityInfoValid())
                    listener.onAddUniversityClickListener(etUniversityName.getText().toString());
                break;
            }
        }
    }

    private boolean isUniversityInfoValid() {
        return !etUniversityName.getText().toString().isEmpty();
    }

    public void setListener(OnAddUniversityClickListener listener) {
        this.listener = listener;
    }

    public interface OnAddUniversityClickListener {
        void onAddUniversityClickListener(String universityName);
    }
}
