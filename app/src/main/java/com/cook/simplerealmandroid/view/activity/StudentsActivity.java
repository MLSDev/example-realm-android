package com.cook.simplerealmandroid.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.cook.simplerealmandroid.R;
import com.cook.simplerealmandroid.presenters.IStudentPresenter;
import com.cook.simplerealmandroid.presenters.impl.StudentPresnter;
import com.cook.simplerealmandroid.view.activity.base.BaseActivity;
import com.cook.simplerealmandroid.view.adapters.StudentsAdapter;

/**
 * Created by roma on 03.11.15.
 */
public class StudentsActivity extends BaseActivity implements View.OnClickListener {

    private FloatingActionButton fbAdd;
    private RecyclerView rvStudents;
    private StudentsAdapter adapter;

    private IStudentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        presenter = new StudentPresnter(this);

        initComponents();
    }

    @Override
    protected void initComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.students);
        setSupportActionBar(toolbar);
        fbAdd = (FloatingActionButton) findViewById(R.id.fab_add_university);
        fbAdd.setOnClickListener(this);
        initRecyclerListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribeCallbacks();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribeCallbacks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_add_student:{
                showAddStudentDialog();
                break;
            }
        }
    }

    private void showAddStudentDialog(){

    }

    private void initRecyclerListener(){
        rvStudents = (RecyclerView) findViewById(R.id.rv_universities);
        rvStudents.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvStudents.setItemAnimator(new DefaultItemAnimator());

        ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteStudent(viewHolder.getAdapterPosition());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(rvStudents);
    }
}
