package android.events.meugeninua.androidevents.ui.activities.main;

import android.events.meugeninua.androidevents.R;
import android.events.meugeninua.androidevents.app.services.simpleevents.SimpleEventsService;
import android.events.meugeninua.androidevents.ui.activities.base.BaseActivity;
import android.os.Bundle;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleEventsService.start(this);
    }


}
