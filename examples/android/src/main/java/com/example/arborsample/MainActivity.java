package com.example.arborsample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.toxicbakery.logging.Arbor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Arbor.d("Hello from Java");
        Arbor.tag("Hello").i("world");
    }

}
