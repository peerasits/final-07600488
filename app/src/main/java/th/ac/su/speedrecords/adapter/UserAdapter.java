package th.ac.su.speedrecords.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import th.ac.su.speedrecords.R;
import th.ac.su.speedrecords.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private User[] mUsers;

    public UserAdapter(Context context, User[] users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.component, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mUsers[position];

        holder.speed.setText(String.valueOf(user.speed)+" KM/H");
        holder.distanceAndDuration.setText(String.valueOf(user.distance)+"METERS, "+String.valueOf(user.time)+" SECONDS");
        if(!user.over)
            holder.overImg.setImageResource(0);
        else
            holder.overImg.setImageResource(R.drawable.red_cow);
    }

    @Override
    public int getItemCount() {
        return mUsers.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView speed;
        TextView distanceAndDuration;
        ImageView overImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.speed = itemView.findViewById(R.id.speed_text);
            this.distanceAndDuration = itemView.findViewById(R.id.duration_time_text);
            this.overImg = itemView.findViewById(R.id.over_img);
        }
    }
}

