package pers.dongchangzhang.todayisbeautiful.todayisbeautiful;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_page);

        Toolbar toobar = (Toolbar) findViewById(R.id.toolbar);
        toobar.setTitle("");

        TextView place = (TextView)findViewById(R.id.titles);
        place.setText("哈尔滨");
        setSupportActionBar(toobar);
        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.menu);
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_change_city);
        navView.setCheckedItem(R.id.nav_plan);
        navView.setCheckedItem(R.id.nav_alert);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.nav_change_city:
                        Toast.makeText(MainActivity.this, "city", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_alert:
                        break;
                    case R.id.nav_plan:
                        break;
                }
                return true;

            }
        });


        ImageView imageView = (ImageView)findViewById(R.id.image);
        Glide
                .with(this)
                .load(R.drawable.w0)
                .placeholder(R.drawable.w0)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;  // Return true to expand action view
            }
        };
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(this, "open", Toast.LENGTH_SHORT).show();
                drawer_layout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                break;
            case R.id.settings:
                break;
            case R.id.exit:
                this.finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
