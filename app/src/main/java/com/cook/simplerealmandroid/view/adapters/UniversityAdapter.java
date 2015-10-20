package com.cook.simplerealmandroid.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cook.simplerealmandroid.R;
import com.cook.simplerealmandroid.model.University;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by roma on 20.10.15.
 */
public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {

    private RealmResults<University> universities;

    public UniversityAdapter(RealmResults<University> universities) {
        this.universities = universities;
    }

    @Override
    public UniversityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_university, parent, false);
        return new UniversityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UniversityViewHolder holder, int position) {
        holder.tvUniversityName.setText(universities.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return universities.size();
    }

    static class UniversityViewHolder extends RecyclerView.ViewHolder {

        TextView tvUniversityName;

        public UniversityViewHolder(View itemView) {
            super(itemView);
            tvUniversityName = (TextView) itemView.findViewById(R.id.tv_name_university);
        }
    }
}
