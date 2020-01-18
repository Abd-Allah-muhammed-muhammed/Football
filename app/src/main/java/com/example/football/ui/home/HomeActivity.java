package com.example.football.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.football.R;
import com.example.football.helper.PrefManager;
import com.example.football.helper.ViewDialog;
import com.example.football.ui.MYFavoriteFragment;

import static com.example.football.helper.HelperMethods.fullScreen;
import static com.example.football.helper.HelperMethods.isNetworkAvailable;
import static com.example.football.helper.HelperMethods.replace;

public class HomeActivity extends AppCompatActivity {

    PrefManager prefManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(this);
        setContentView(R.layout.activity_home);
        prefManager = new PrefManager(this);
        if (prefManager.isFirstTimeLaunch()) {


            if (!isNetworkAvailable(this)){

                ViewDialog alert = new ViewDialog();
                alert.showDialog(this, "you should open internet for the first time to save what you select");

            }else {

                prefManager.setFirstTimeLaunch(false);
                replace(new HomeFragment(),R.id.container_home,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_home));

            }


        }else {
            replace(new HomeFragment(),R.id.container_home,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_home));


        }
        
       
        
        


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_favorite_menu, menu);
        return true;
    }

    public void myFavorite(MenuItem item) {

        replace(new MYFavoriteFragment(),R.id.container_home,getSupportFragmentManager().beginTransaction(),getString(R.string.tag_favorit));

    }
}
