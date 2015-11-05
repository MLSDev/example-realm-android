package com.cook.simplerealmandroid.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.cook.simplerealmandroid.R;
import com.cook.simplerealmandroid.model.Student;
import com.cook.simplerealmandroid.presenters.IStudentPresenter;
import com.cook.simplerealmandroid.presenters.impl.StudentPresenter;
import com.cook.simplerealmandroid.realm.table.RealmTable;
import com.cook.simplerealmandroid.view.activity.base.BaseActivity;
import com.cook.simplerealmandroid.view.adapters.StudentsAdapter;
import com.cook.simplerealmandroid.view.dialogs.AddStudentDialog;

import io.realm.RealmList;

/**
 * Created by roma on 03.11.15.
 */
public class StudentsActivity extends BaseActivity implements View.OnClickListener {

    private IStudentPresenter presenter;

    private FloatingActionButton fbAdd;
    private RecyclerView rvStudents;
    private StudentsAdapter adapter;

    private RealmList<Student> students;
    private String universityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        presenter = new StudentPresenter(this);
        universityId = getIntent().getStringExtra(RealmTable.ID);
        initComponents();
    }

    @Override
    protected void initComponents() {
        fbAdd = (FloatingActionButton) findViewById(R.id.fab_add_student);
        fbAdd.setOnClickListener(this);
        getSupportActionBar().setTitle(getString(R.string.students));
        initRecyclerListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.subscribeCallbacks();
        presenter.getAllStudentsByUniversityId(universityId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribeCallbacks();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_add_student: {
                showAddStudentDialog();
                break;
            }
        }
    }

    private void initRecyclerListener() {
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
                presenter.deleteStudentById(students.get(viewHolder.getAdapterPosition()).getId());
                adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });
        swipeToDismissTouchHelper.attachToRecyclerView(rvStudents);
    }

    private void showAddStudentDialog() {
        final AddStudentDialog dialog = new AddStudentDialog();
        dialog.show(getSupportFragmentManager(), dialog.getClass().getName());
        dialog.setListener(new AddStudentDialog.OnAddStudentClickListener() {
            @Override
            public void onAddStudentClickListener(Student student) {
                dialog.dismiss();
                presenter.addStudentByUniversityId(student, universityId);
                presenter.getAllStudentsByUniversityId(universityId);
            }
        });
    }

    public void showStudents(RealmList<Student> students) {
        this.students = students;
        adapter = new StudentsAdapter(students);
        rvStudents.setAdapter(adapter);

    }
}
