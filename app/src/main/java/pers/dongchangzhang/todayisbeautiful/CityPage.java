package pers.dongchangzhang.todayisbeautiful;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pers.dongchangzhang.todayisbeautiful.todayisbeautiful.R;

/**
 * Created by cc on 17-7-20.
 */

public class CityPage extends Fragment  {
    public CityPage() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_page,container,false);
        // future
        return view;
    }
}
