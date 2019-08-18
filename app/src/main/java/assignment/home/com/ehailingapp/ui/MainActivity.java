package assignment.home.com.ehailingapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import assignment.home.com.ehailingapp.R;
import assignment.home.com.ehailingapp.adapter.TaxiListAdapter;
import assignment.home.com.ehailingapp.model.MyViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private MyViewModel myViewModel;

    @BindView(R.id.taxi_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        setRecyclerView();
        observeViewModel();
    }

    private void observeViewModel() {
        //Observe and update the taxis list in every interval.
        myViewModel.getTaxis().observe(this, taxis -> {
            runLayoutAnimation(mRecyclerView);
        });
    }

    // Sets all properties need for displaying the taxis list
    private void setRecyclerView() {
        // Using this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // Using a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        TaxiListAdapter mAdapter = new TaxiListAdapter(myViewModel, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    //Animation fall down to the list
    private void runLayoutAnimation(final RecyclerView recyclerView) {
        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }
}
