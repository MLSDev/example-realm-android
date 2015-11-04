package com.cook.simplerealmandroid.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cook.simplerealmandroid.R;
import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.presenters.IStudentPresenter;
import com.cook.simplerealmandroid.presenters.impl.StudentPresnter;
import com.cook.simplerealmandroid.realm.table.RealmTable;
import com.cook.simplerealmandroid.view.activity.base.BaseActivity;
import com.cook.simplerealmandroid.view.adapters.StudentsAdapter;
import com.cook.simplerealmandroid.view.dialogs.StudentInfoDialog;

import io.realm.RealmList;
import io.realm.RealmResults;

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

        String id = getIntent().getStringExtra(RealmTable.ID);
        presenter.getAllStudentsByUniversityId(id);

        initComponents();
    }

    @Override
    protected void initComponents() {
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setTitle(R.string.students);
//        setSupportActionBar(toolbar);
        fbAdd = (FloatingActionButton) findViewById(R.id.fab_add_student);
        fbAdd.setOnClickListener(this);
        initRecyclerListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribeCallbacks();
        presenter.getAllStudents();
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

    private void initRecyclerListener(){
        rvStudents = (RecyclerView) findViewById(R.id.rv_students);
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

    private void showAddStudentDialog(){
        StudentInfoDialog dialog = new StudentInfoDialog();
        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
    }

    public void showStudents(RealmList<Student> students){
        adapter = new StudentsAdapter(students);
        rvStudents.setAdapter(adapter);
    }
}
