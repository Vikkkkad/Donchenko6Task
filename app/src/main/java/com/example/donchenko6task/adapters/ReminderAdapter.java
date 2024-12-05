package com.example.donchenko6task.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donchenko6task.R;
import com.example.donchenko6task.models.Reminder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(Reminder reminder);
    }

    private List<Reminder> reminderList;
    private OnDeleteClickListener deleteClickListener;

    public ReminderAdapter(List<Reminder> reminderList, OnDeleteClickListener deleteClickListener) {
        this.reminderList = reminderList;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminder reminder = reminderList.get(position);
        holder.bind(reminder);
    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvText, tvDateTime;
        ImageButton btnDelete;

        ReminderViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvReminderTitle);
            tvText = itemView.findViewById(R.id.tvReminderText);
            tvDateTime = itemView.findViewById(R.id.tvReminderDateTime);
            btnDelete = itemView.findViewById(R.id.btnDeleteReminder);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && deleteClickListener != null) {
                        deleteClickListener.onDeleteClick(reminderList.get(position));
                    }
                }
            });
        }

        void bind(Reminder reminder) {
            tvTitle.setText(reminder.getTitle());
            tvText.setText(reminder.getText());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            tvDateTime.setText(sdf.format(reminder.getDateTime()));
        }
    }

    public void updateReminders(List<Reminder> newReminders) {
        this.reminderList.clear();
        this.reminderList.addAll(newReminders);
        notifyDataSetChanged();
    }
}