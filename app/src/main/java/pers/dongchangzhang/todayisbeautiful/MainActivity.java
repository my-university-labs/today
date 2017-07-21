package pers.dongchangzhang.todayisbeautiful;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.GetWeatherInfo;

public class MainActivity extends AppCompatActivity {
//    fragment item
    private CityPage cityPage;
    private PlanPage planPage;
    private WeatherPage weatherPage;
    private FragmentManager fManager;
    private FragmentTransaction fTransaction;
// slide menu
    private DrawerLayout drawer_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // toolbar
        Toolbar toobar = (Toolbar) findViewById(R.id.toolbar);
        toobar.setTitle("");
        // which city_page now
        TextView place = (TextView)findViewById(R.id.titles);
        place.setText("哈尔滨");
        setSupportActionBar(toobar);
       init_fragment();
        // slide menu
        init_slide_menu();
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        weatherPage = new WeatherPage();
        weatherPage.setContext(MainActivity.this);
        fTransaction.add(R.id.ly_content, weatherPage);
        fTransaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        MenuItem.OnActionExpandListener expandListener = new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        };
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
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
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(weatherPage != null)fragmentTransaction.hide(weatherPage);
        if(cityPage != null)fragmentTransaction.hide(cityPage);
//        if(planPage != null)fragmentTransaction.hide(planPage);
    }
    private void init_fragment() {
        fManager = getFragmentManager();
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
    }
    private void init_slide_menu() {
        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.menu);
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
        final NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                fTransaction = fManager.beginTransaction();
                hideAllFragment(fTransaction);
                switch(item.getItemId()) {
                    case R.id.nav_weather:
                        if (weatherPage == null) {
                            weatherPage = new WeatherPage();
                            weatherPage.setContext(MainActivity.this);
                            fTransaction.add(R.id.ly_content, weatherPage);
//                            fTransaction.add(R.id.future_day,weatherPage);
                        } else {
                            fTransaction.show(weatherPage);
                        }
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_change_city:
                        if (cityPage == null) {
                            cityPage = new CityPage();
                            fTransaction.add(R.id.ly_content, cityPage);
                        } else {
                            fTransaction.show(cityPage);
                        }
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_alert:
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_plan:
                        drawer_layout.closeDrawers();
                        break;
                }
                fTransaction.commit();
                return true;
            }
        });
        navView.setCheckedItem(R.id.nav_weather);
    }

}
