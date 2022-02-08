package org.microdegree.com.app.exp.ui.course.coursedetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.microdegree.com.app.exp.R;

import java.util.List;

public class PreRequisitesAdapter extends RecyclerView.Adapter<PreRequisitesAdapter.ViewHolder> {

private final List<String> list;

public PreRequisitesAdapter(List<String> list) {
        this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prerequisite_layout, parent, false);
                return new ViewHolder(view);
                }
        
        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
                final String data = list.get(position);

                holder.txt_title.setText(data);
            }
        
        @Override
        public int getItemCount() {
                return list.size();
                }
            
        public static class ViewHolder extends RecyclerView.ViewHolder{

            private final TextView txt_title;
            public ViewHolder(View itemView) {
                super(itemView);
                txt_title = itemView.findViewById(R.id.txt_title);
            }
        }
}
