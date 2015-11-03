package com.cook.simplerealmandroid.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cook.simplerealmandroid.R;
import com.cook.simplerealmandroid.model.University;
import com.cook.simplerealmandroid.presenters.impl.UniversityPresenter;
import com.cook.simplerealmandroid.view.activity.UniversityActivity;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by roma on 20.10.15.
 */
public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {

    private OnItemClickListener onItemClickListener;
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

    public class UniversityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvUniversityName;

        public UniversityViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            tvUniversityName = (TextView) itemView.findViewById(R.id.tv_name_university);
        }

        @Override
        public void onClick(View v) {
            University university = universities.get(getAdapterPosition());
            onItemClickListener.onItemClick(university.getId());
        }
    }

    public interface OnItemClickListener{
        void onItemClick(String id);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
