package com.example.data_display;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ViewGroup container = findViewById(R.id.container);

        TextView test = findViewById(R.id.test);
        test.setText(R.string.menu_tools);
        test.setTextColor(getResources().getColor(R.color.orange));
        test.setOnClickListener(this);

//        TextView textView = new TextView(this);
//        textView.setText("empty");
//        textView.setGravity(Gravity.CENTER);
//        textView.setHeight(200);
//        textView.setWidth(60);
//        container.addView(textView);

        SharedPreferences pref = getSharedPreferences("settings", MODE_PRIVATE);
        pref.edit()
                .putInt("rating", 5)
                .apply();
    }

    public String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    @Override
    public void onClick(View clickedView) {
        if (clickedView.getId() == R.id.test) {

        }
    }
}
