package com.zino.mobilization.weatheryamblz.presentation.add_city;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zino.mobilization.weatheryamblz.R;
import com.zino.mobilization.weatheryamblz.business.entity.Suggestion;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Denis Buzmakov on 09.08.17.
 * <buzmakov.da@gmail.com>
 */

class SuggestionsAdapter extends RecyclerView.Adapter<SuggestionsAdapter.ViewHolder> {
    private List<Suggestion> data = new ArrayList<>();
    private OnItemClickListener clickListener;

    SuggestionsAdapter(@NonNull List<Suggestion> data,
                              @Nullable OnItemClickListener clickListener) {
        this.data.addAll(data);
        this.clickListener = clickListener;
    }

    @Override
    public SuggestionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SuggestionsAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void updateData(List<Suggestion> data) {
        Timber.d("update data: %s", data.toString());
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_suggestion_name) TextView txtSuggestionName;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Suggestion suggestion, @Nullable OnItemClickListener clickListener) {
            txtSuggestionName.setText(suggestion.getDescription());
            if(clickListener != null) {
                txtSuggestionName.setOnClickListener(v -> clickListener.onItemClickListener(suggestion));
            }
        }
    }

    interface OnItemClickListener {
        void onItemClickListener(Suggestion suggestion);
    }
}
