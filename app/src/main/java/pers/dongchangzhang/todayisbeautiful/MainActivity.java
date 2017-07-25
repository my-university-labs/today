package pers.dongchangzhang.todayisbeautiful;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import pers.dongchangzhang.todayisbeautiful.dao.AssetsDatabaseManager;
import pers.dongchangzhang.todayisbeautiful.entity.Event;
import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

import static pers.dongchangzhang.todayisbeautiful.Config.FALSE;
import static pers.dongchangzhang.todayisbeautiful.Config.TRUE;
import static pers.dongchangzhang.todayisbeautiful.Config.first_time_in;
import static pers.dongchangzhang.todayisbeautiful.Config.weather_information;
import static pers.dongchangzhang.todayisbeautiful.Config.which_city;

public class MainActivity extends AppCompatActivity implements Receiver.Message  {
    private static final String TAG = "DB";
    private CityPage cityPage;
    private PlanPage planPage;
    private WeatherPage weatherPage;
    private FragmentManager fManager;
    private NewPlanPage newPlanPage;
    private CalendarPage calendarPage;
    private FragmentTransaction fTransaction;
    private List<CityPage> backup = new ArrayList<>();
    // slide menu
    private DrawerLayout drawer_layout;

    Receiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("default_city", Context.MODE_PRIVATE);
        which_city = sharedPreferences.getString("cityName", which_city);
        // toolbar
        Toolbar toobar = (Toolbar) findViewById(R.id.toolbar);
        toobar.setTitle("");
        // which city_page now
        TextView place = (TextView) findViewById(R.id.titles);
        place.setText(which_city);
        setSupportActionBar(toobar);
        init_fragment();
        // slide menu
        init_slide_menu();
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        weatherPage = new WeatherPage();
        fTransaction.add(R.id.ly_content, weatherPage);
        fTransaction.commit();


        AssetsDatabaseManager.initManager(getApplication());

        Intent service_intent = new Intent(this, MainService.class);
        startService(service_intent);

        myReceiver = new Receiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("pers.dongchangzhang.broadcasereceiver.MYRECEIVER");
        registerReceiver(myReceiver, intentFilter);

        myReceiver.setMessage(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
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
            case R.id.share:
                sendSMS(weather_information);
                break;
            case R.id.confirm:
                saveDataIntoPreferences("default_city", which_city);
                Toast.makeText(this, "成功设置" + which_city + "为默认地点", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                this.finish();
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (weatherPage != null) fragmentTransaction.hide(weatherPage);
        if (cityPage != null) fragmentTransaction.hide(cityPage);
        if (planPage != null) fragmentTransaction.hide(planPage);
        if (newPlanPage != null) fragmentTransaction.hide(newPlanPage);
        if (calendarPage != null) fragmentTransaction.hide(calendarPage);

        for (CityPage cp : backup) {
            try {
                fragmentTransaction.hide(cp);
            } catch (Exception e) {
                ;
            }
        }
    }

    private void init_fragment() {
        fManager = getFragmentManager();
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
    }

    private void init_slide_menu() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.menu);
        } else {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
        final NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                fTransaction = fManager.beginTransaction();

                TextView place = (TextView) findViewById(R.id.titles);
                SwipeRefreshLayout swipFresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
                swipFresh.setRefreshing(false);
                switch (item.getItemId()) {
                    case R.id.nav_weather:
                        hideAllFragment(fTransaction);
                        place.setText(which_city);
                        removeTmpCityPage(fTransaction);
                        if (weatherPage == null) {
                            weatherPage = new WeatherPage();
                            fTransaction.add(R.id.ly_content, weatherPage);
                        } else {
                            fTransaction.show(weatherPage);
                        }
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_change_city:
                        hideAllFragment(fTransaction);
                        place.setText("选择城市");
                        removeTmpCityPage(fTransaction);

                        if (cityPage == null) {
                            cityPage = new CityPage();
                            Bundle bundle = new Bundle();
                            bundle.putString("id", "0");
                            bundle.putString("parent_id", "0");
                            bundle.putString("city", which_city);
                            cityPage.setArguments(bundle);
                            fTransaction.add(R.id.ly_content, cityPage);
                        } else {
                            fTransaction.show(cityPage);
                        }
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_plan:
                        hideAllFragment(fTransaction);
                        place.setText("出行计划");
                        removeTmpCityPage(fTransaction);
                        if (planPage == null) {
                            planPage = new PlanPage();
                            fTransaction.add(R.id.ly_content, planPage);
                        } else {
                            fTransaction.show(planPage);
                        }
                        drawer_layout.closeDrawers();
                        break;
                    case R.id.nav_alert:
                        hideAllFragment(fTransaction);
                        place.setText("日历提醒");
                        removeTmpCityPage(fTransaction);
                        if (calendarPage == null) {
                            calendarPage = new CalendarPage();
                            fTransaction.add(R.id.ly_content, calendarPage);
                        } else {
                            fTransaction.remove(calendarPage);
                            calendarPage = new CalendarPage();
                            fTransaction.add(R.id.ly_content, calendarPage);
                        }
                        drawer_layout.closeDrawers();
                        break;

                }
                fTransaction.commit();
                return true;
            }
        });
        navView.setCheckedItem(R.id.nav_weather);
    }

    public void changeToCity(String id, String parent_id, String city) {
        final NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_weather);
        which_city = city;
        TextView place = (TextView) findViewById(R.id.titles);
        place.setText(city);
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        CityPage cityPage = new CityPage();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("parent_id", parent_id);
        bundle.putString("city", city);
        cityPage.setArguments(bundle);
        backup.add(cityPage);
        fTransaction.add(R.id.ly_content, cityPage);
        fTransaction.commit();
    }

    public void jumpToWeatherPage(String city) {
        fTransaction = fManager.beginTransaction();
        removeTmpCityPage(fTransaction);
        hideAllFragment(fTransaction);
        weatherPage.onRefresh(city);
        fTransaction.show(weatherPage);


        fTransaction.commit();

    }

    public void newAPlan() {
        TextView place = (TextView) findViewById(R.id.titles);
        place.setText("新的计划");
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);

        if (newPlanPage != null) {
           fTransaction.remove(newPlanPage);
        }

        newPlanPage = new NewPlanPage();
        Bundle bundle = new Bundle();
        bundle.putString("isEdit", FALSE);
        newPlanPage.setArguments(bundle);
        fTransaction.add(R.id.ly_content, newPlanPage);

        fTransaction.commit();
    }

    public void cancelNewPlan() {
        TextView place = (TextView) findViewById(R.id.titles);
        place.setText("出行计划");
        fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        if (planPage == null) {
            planPage = new PlanPage();
            fTransaction.add(R.id.ly_content, planPage);
        } else {
            fTransaction.show(planPage);
        }
        fTransaction.commit();
    }

    public void commitNewPlan() throws ParseException {
        planPage.reRefresh();
        cancelNewPlan();
    }

    private void removeTmpCityPage(FragmentTransaction fTransaction) {
        for (CityPage cp : backup) {
            try {
                fTransaction.remove(cp);
            } catch (Exception e) {
                ;
            }
        }
        backup.clear();
    }

    private boolean saveDataIntoPreferences(String fileName, String cityName) {
        boolean saveSuccessfully = false;
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("cityName", cityName);
        saveSuccessfully = edit.commit();
        return saveSuccessfully;
    }

    private void sendSMS(String smsBody)

    {
        Uri smsToUri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        intent.putExtra("sms_body", smsBody);
        startActivity(intent);

    }
    public void editPlan(String id) {
        TextView place = (TextView) findViewById(R.id.titles);
        place.setText("编辑计划");
        fTransaction = fManager.beginTransaction();

        hideAllFragment(fTransaction);
        if (newPlanPage != null) {
            fTransaction.remove(newPlanPage);
        }
        newPlanPage = new NewPlanPage();
        Bundle bundle = new Bundle();
        bundle.putString("isEdit", TRUE);
        bundle.putString("id", id);
        newPlanPage.setArguments(bundle);

        fTransaction.add(R.id.ly_content, newPlanPage);
        fTransaction.commit();
    }

    @Override
    public void getMsg(String str) {
        Log.e(TAG, "getMsg: EEEEEE----------------------" );
        if (first_time_in) {
            first_time_in = false;
        } else {
            Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
            weatherPage.onRefresh(which_city);
        }
    }
}
