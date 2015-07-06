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

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    Vehicle vehicle;
    WebsiteService websiteService;

    @Inject
    String helloWorldString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainApplication.getApplicationComponent(getApplication()).inject(this);

        setContentView(R.layout.activity_main);
        TextView textView = (TextView) getSupportFragmentManager().findFragmentById(R.id.fragment).getView().findViewById(R.id.text);
        textView.setText(helloWorldString);

//        VehicleComponent component = DaggerVehicleComponent.builder().vehicleModule(new VehicleModule()).build();
        VehicleComponent component = DaggerVehicleComponent.create();
        this.vehicle = component.provideVehicle();

        WebComponent webComponent = DaggerWebComponent.create();
        this.websiteService = webComponent.provideWebsiteAPI();
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
