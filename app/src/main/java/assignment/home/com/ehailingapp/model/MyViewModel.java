package assignment.home.com.ehailingapp.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import assignment.home.com.ehailingapp.R;
import assignment.home.com.ehailingapp.UtilityHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static assignment.home.com.ehailingapp.UtilityHelper.INITIAL_DELAY;
import static assignment.home.com.ehailingapp.UtilityHelper.REFRESH_TIME;
import static assignment.home.com.ehailingapp.UtilityHelper.TIME_END;
import static assignment.home.com.ehailingapp.UtilityHelper.TIME_START;

public class MyViewModel extends AndroidViewModel {

    private Resources resources;
    private MutableLiveData<ArrayList<Taxi>> taxis = new MutableLiveData<>();

    // This constructor receives the Application object in order to get system resources.
    public MyViewModel(@NonNull Application application) {
        super(application);
        resources = application.getResources();
        UpdateArrival();
    }

    //Expose data to our view, e.g. MainActivity.
    public LiveData<ArrayList<Taxi>> getTaxis() {
        return taxis;
    }

    //Creates ArrayList of taxis
    public ArrayList<Taxi> getSortedTaxisList() {
        final ArrayList<Taxi> taxis = new ArrayList<>();
        taxis.add(new Taxi(resources.getString(R.string.castle), UtilityHelper.getRandomETA(TIME_START, TIME_END)));
        taxis.add(new Taxi(resources.getString(R.string.shekem), UtilityHelper.getRandomETA(TIME_START, TIME_END)));
        taxis.add(new Taxi(resources.getString(R.string.habima), UtilityHelper.getRandomETA(TIME_START, TIME_END)));
        taxis.add(new Taxi(resources.getString(R.string.gordon), UtilityHelper.getRandomETA(TIME_START, TIME_END)));
        taxis.add(new Taxi(resources.getString(R.string.azrieli), UtilityHelper.getRandomETA(TIME_START, TIME_END)));
        taxis.add(new Taxi(resources.getString(R.string.hadera), UtilityHelper.getRandomETA(TIME_START, TIME_END)));

        //Sorting the list in ascending order
        Collections.sort(taxis, (t1, t2) -> t1.getArrivalTime() - t2.getArrivalTime());

        return taxis;
    }

    //  Subscribe on I/O thread and observe on UI thread with a particular time interval.
    @SuppressLint("CheckResult")
    private void UpdateArrival() {
        Observable.interval(INITIAL_DELAY,REFRESH_TIME, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext -> taxis.setValue(getSortedTaxisList()));
    }
}
