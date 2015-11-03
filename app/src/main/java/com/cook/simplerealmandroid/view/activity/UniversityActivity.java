package com.cook.simplerealmandroid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cook.simplerealmandroid.R;
import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.presenters.IUniversityPresenter;
import com.cook.simplerealmandroid.presenters.impl.UniversityPresenter;
import com.cook.simplerealmandroid.realm.table.RealmTable;
import com.cook.simplerealmandroid.view.activity.base.BaseActivity;
import com.cook.simplerealmandroid.view.adapters.UniversityAdapter;

import io.realm.RealmResults;

public class UniversityActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton fbAdd;
    private RecyclerView rvUniversities;
    private UniversityAdapter adapter;

    private IUniversityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universities);

        presenter = new UniversityPresenter(this);

        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribeCallbacks();
        presenter.getAllUniversities();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribeCallbacks();
    }

    @Override
    protected void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.universities);
        setSupportActionBar(toolbar);
        fbAdd = (FloatingActionButton) findViewById(R.id.fab_add_university);
        fbAdd.setOnClickListener(this);
        initRecyclerListener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_university: {
                showAddUniversityDialog();
                break;
            }
        }
    }

    private void initRecyclerListener() {
        rvUniversities = (RecyclerView) findViewById(R.id.rv_universities);
        rvUniversities.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvUniversities.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteUniversity(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(rvUniversities);
    }

    public void showUniversities(RealmResults<University> universities) {
        adapter = new UniversityAdapter(universities);
        adapter.setOnItemClickListener(new UniversityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
                Intent intent = new Intent(getApplicationContext(), StudentsActivity.class);
                intent.putExtra(RealmTable.ID, id);
                startActivity(intent);
            }
        });
        rvUniversities.setAdapter(adapter);
    }

    private void showAddUniversityDialog() {
        new MaterialDialog.Builder(this)
                .title(R.string.add_a_university)
                .content(R.string.input_name_of_university)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .input(R.string.name, R.string.pre_fill, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        presenter.addUniversity(input.toString());
                    }
                }).show();
    }

}
