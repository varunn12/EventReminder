package in.fieldassist.eventreminder;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reweyou on 9/7/2017.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.MyViewHolder> {
    private Context context;
    List<AlarmClass> alarm;
    private int itemResource;
    Presenter presenter;

    public AlarmAdapter(Context context, int itemResource, List<AlarmClass> alarm){
        this.context=context;
        this.itemResource=itemResource;
        this.alarm=alarm;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding= DataBindingUtil.inflate(layoutInflater,R.layout.alarm_list_item,parent,false);

        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final AlarmClass alarmClass=alarm.get(position);
        final Presenter clicker=new Presenter(context, holder.getAdapterPosition());
        holder.bind(alarmClass,clicker);
    }

    @Override
    public int getItemCount() {
        return alarm.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder  {

        private final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

            public void bind(Object alarmclass, Object presenter){

            binding.setVariable(BR.alarmclass,alarmclass);
            binding.setVariable(BR.presenter,presenter);
            binding.executePendingBindings();
        }

    }




    public void deleteItem(int position) {
        alarm.remove(position);

        notifyDataSetChanged();
    }

}
