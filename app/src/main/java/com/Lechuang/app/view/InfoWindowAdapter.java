package com.Lechuang.app.view;

import android.view.View;

import com.amap.api.maps2d.model.Marker;

/**
 * Created by Administrator on 2017/8/11.
 */

public interface InfoWindowAdapter {
    View getInfoWindow(Marker marker);
    View getInfoContents(Marker marker);
}
