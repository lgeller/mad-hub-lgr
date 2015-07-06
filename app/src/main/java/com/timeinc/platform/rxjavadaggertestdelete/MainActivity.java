package com.timeinc.platform.rxjavadaggertestdelete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.timeinc.platform.rxjavadaggertestdelete.component.DaggerVehicleComponent;
import com.timeinc.platform.rxjavadaggertestdelete.component.DaggerWebComponent;
import com.timeinc.platform.rxjavadaggertestdelete.component.VehicleComponent;
import com.timeinc.platform.rxjavadaggertestdelete.component.WebComponent;
import com.timeinc.platform.rxjavadaggertestdelete.model.Vehicle;
import com.timeinc.platform.rxjavadaggertestdelete.model.WebsiteService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.app.AppObservable;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {


    Vehicle vehicle;
    WebsiteService websiteService;

    @Inject
    String helloWorldString;

    TextView helloWorldTextView;
    TextView retrofitRxJavaTextView;
    TextView rxJavaObservableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainApplication.getApplicationComponent(getApplication()).inject(this);

        setContentView(R.layout.activity_main);

        MainActivityFragment fragment = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        helloWorldTextView = (TextView) fragment.getView().findViewById(R.id.text);
        retrofitRxJavaTextView = (TextView) fragment.getView().findViewById(R.id.retrofitRxJavaText);
        rxJavaObservableTextView = (TextView) fragment.getView().findViewById(R.id.rxJavaObservableText);

        helloWorldTextView.setText(helloWorldString);

//      Could also call this:
//      VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        VehicleComponent component = DaggerVehicleComponent.create();
        this.vehicle = component.provideVehicle();

        WebComponent webComponent = DaggerWebComponent.create();
        this.websiteService = webComponent.provideWebsiteAPI();

        WebsiteService.GitHub github = this.websiteService.getGithub();


        AppObservable.bindActivity(this,)
        Observable<String> obsv = Observable.just("Hello world from Observable!");

        AppObservable.bindActivity(this, obsv).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                rxJavaObservableTextView.setText(s);
            }
        });


        //See: https://github.com/ReactiveX/RxJava/wiki/Transforming-Observables
//        github.contributors("square", "retrofit").flatMap(new Func1<List<WebsiteService.Contributor>, Observable<?>>() {
//            @Override
//            public Observable<?> call(List<WebsiteService.Contributor> contributors) {
//                return null;
//            }
//        }).subscribe()

        // Create a call instance for looking up Retrofit contributors.

        github.contributors("square", "retrofit")
                .forEach(new Action1<List<WebsiteService.Contributor>>() {
                    @Override
                    public void call(List<WebsiteService.Contributor> contributors) {
                        for (WebsiteService.Contributor contributor : contributors) {
                            retrofitRxJavaTextView.setText(contributor.login + " (" + contributor.contributions + ")");
                        }
                    }
                });


//        Observable<List<WebsiteService.Contributor>> call = github.contributors("square", "retrofit");
//        call.subscribe(new Action1<List<WebsiteService.Contributor>>() {
//            @Override
//            public void call(List<WebsiteService.Contributor> contributors) {
//                for (WebsiteService.Contributor contributor : contributors) {
//                    retrofitRxJavaTextView.setText(contributor.login + " (" + contributor.contributions + ")");
//                }
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
