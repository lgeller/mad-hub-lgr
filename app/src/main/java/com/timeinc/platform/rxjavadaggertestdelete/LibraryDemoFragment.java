package com.timeinc.platform.rxjavadaggertestdelete;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.timeinc.platform.rxjavadaggertestdelete.component.DaggerVehicleComponent;
import com.timeinc.platform.rxjavadaggertestdelete.component.DaggerWebComponent;
import com.timeinc.platform.rxjavadaggertestdelete.component.VehicleComponent;
import com.timeinc.platform.rxjavadaggertestdelete.component.WebComponent;
import com.timeinc.platform.rxjavadaggertestdelete.model.Vehicle;
import com.timeinc.platform.rxjavadaggertestdelete.model.WebsiteService;

import javax.inject.Inject;

import rx.Observable;
import rx.android.app.AppObservable;
import rx.functions.Action1;

/**
 * A placeholder fragment containing a simple view.
 */
public class LibraryDemoFragment extends Fragment {

    Vehicle vehicle;
    WebsiteService websiteService;

    @Inject
    String helloWorldString;

    TextView helloWorldTextView;
    TextView retrofitRxJavaTextView;
    TextView rxJavaObservableTextView;


    public LibraryDemoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        helloWorldTextView = (TextView) v.findViewById(R.id.text);
        retrofitRxJavaTextView = (TextView) v.findViewById(R.id.retrofitRxJavaText);
        rxJavaObservableTextView = (TextView) v.findViewById(R.id.rxJavaObservableText);

        /*
        ===============================================
        DAGGER DEMO
        ===============================================
         */

        MainApplication.getApplicationComponent(getActivity().getApplication()).inject(this);

        helloWorldTextView.setText(helloWorldString);

//      Can also use (the following would be useful in supplying params to a module):
//      VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        VehicleComponent component = DaggerVehicleComponent.create();
        this.vehicle = component.provideVehicle();



        /*
        ===============================================
        RXJAVA DEMO
        ===============================================
         */


        Observable<String> obsv = Observable.just("Hello world from Observable!");

        //Can also use: LifecycleObservable.bindActivityLifecycle()
        AppObservable.bindSupportFragment(this, obsv).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                rxJavaObservableTextView.setText(s);
            }
        });

        /*
        ===============================================
        DAGGER + RXJAVA + RETROFIT DEMO
        ===============================================
         */

        WebComponent webComponent = DaggerWebComponent.create();
        this.websiteService = webComponent.provideWebsiteAPI();
        WebsiteService.GitHub github = this.websiteService.getGithub();
//        github.

//      TODO: Fix this
//        github.contributors("square", "retrofit")
//                .forEach(new Action1<List<WebsiteService.Contributor>>() {
//                    @Override
//                    public void call(List<WebsiteService.Contributor> contributors) {
//                        for (WebsiteService.Contributor contributor : contributors) {
//                            retrofitRxJavaTextView.setText(contributor.login + " (" + contributor.contributions + ")");
//                        }
//                    }
//                });


        return v;
    }
}
