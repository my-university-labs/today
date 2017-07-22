package pers.dongchangzhang.todayisbeautiful;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.entity.TodayWeatherEntity;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;
import pers.dongchangzhang.todayisbeautiful.utils.GetHttpInfo;

import static pers.dongchangzhang.todayisbeautiful.Config.CHINA_PROVINCE;
import static pers.dongchangzhang.todayisbeautiful.Config.which_city;

public class MainActivity extends AppCompatActivity {
//    fragment item
    private CityPage cityPage;
    private PlanPage planPage;
    private WeatherPage weatherPage;
    private FragmentManager fManager;
    private FragmentTransaction fTransaction;
    private List<CityPage> backup = new ArrayList<>();
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
        place.setText(which_city);
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
                TextView place = (TextView)findViewById(R.id.titles);
                switch(item.getItemId()) {
                    case R.id.nav_weather:
                        place.setText("哈尔滨");
                        removeTmpCityPage(fTransaction);
                        if (weatherPage == null) {
                            weatherPage = new WeatherPage();
                            weatherPage.setContext(MainActivity.this);
                            fTransaction.add(R.id.ly_content, weatherPage);
                        } else {
                            fTransaction.show(weatherPage);
                        }
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_change_city:

                        place.setText("选择城市");
                        removeTmpCityPage(fTransaction);

                        if (cityPage == null) {
                            cityPage = new CityPage();
                            Bundle bundle = new Bundle();
                            bundle.putString("code", "china");
                            bundle.putString("url", CHINA_PROVINCE);
                            cityPage.setArguments(bundle);
                            fTransaction.add(R.id.ly_content, cityPage);
                        } else {
                            fTransaction.show(cityPage);
                        }
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_plan:
                        place.setText("出行计划");
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_alert:
                        drawer_layout.closeDrawers();
                        break;
                }
                fTransaction.commit();
                return true;
            }
        });
        navView.setCheckedItem(R.id.nav_weather);
    }

    public void changeToCity(String url, String code, String city) {
        final NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_weather);
        which_city = city;
        TextView place = (TextView)findViewById(R.id.titles);
        place.setText(city);
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        CityPage cityPage = new CityPage();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        bundle.putString("url", url);
        bundle.putString("city", city);
        cityPage.setArguments(bundle);
        backup.add(cityPage);
        fTransaction.add(R.id.ly_content, cityPage);
        fTransaction.commit();
    }
    public void jumpToWeatherPage(String city) {
        Toast.makeText(this, city, Toast.LENGTH_SHORT).show();
        fTransaction = fManager.beginTransaction();
        removeTmpCityPage(fTransaction);
        hideAllFragment(fTransaction);
        weatherPage.onRefresh(city);
        fTransaction.show(weatherPage);


        fTransaction.commit();

    }
    private void removeTmpCityPage(FragmentTransaction  fTransaction ) {
        for (CityPage cp : backup) {
            try {
                fTransaction.remove(cp);
            }catch (Exception e) {
                ;
            }
        }
        backup.clear();
    }

}
