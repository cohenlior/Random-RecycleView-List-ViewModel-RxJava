package assignment.home.com.ehailingapp.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import assignment.home.com.ehailingapp.R;
import assignment.home.com.ehailingapp.UtilityHelper;
import assignment.home.com.ehailingapp.model.MyViewModel;
import assignment.home.com.ehailingapp.model.Taxi;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TaxiListAdapter extends RecyclerView.Adapter<TaxiListAdapter.MyViewHolder> {
    private ArrayList<Taxi> mDataSet = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.taxi_name) TextView mTaxiName;
        @BindView(R.id.arrival_time) TextView mArrivalTime;

        MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaxiListAdapter(MyViewModel viewModel, LifecycleOwner lifecycleOwner) {
        viewModel.getTaxis().observe(lifecycleOwner, taxis -> {
            mDataSet.clear();
            if (taxis != null) {
                mDataSet.addAll(taxis);
            }
            notifyDataSetChanged();
        });
        setHasStableIds(true);
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TaxiListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemi_taxi_list, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Taxi item = mDataSet.get(position);
        holder.mTaxiName.setText(item.getStationName());
        holder.mArrivalTime.setText(UtilityHelper.timeFormatter(item.getArrivalTime()));

    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
