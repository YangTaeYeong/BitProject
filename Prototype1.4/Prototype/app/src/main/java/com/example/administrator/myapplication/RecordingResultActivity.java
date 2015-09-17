package com.example.administrator.myapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

public class RecordingResultActivity extends Activity {

    private XYPlot plot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_plot_chart);
        plot = (XYPlot) findViewById(R.id.xyPlot);

        List s1 = getSeries(20, 10);
        XYSeries series1 = new SimpleXYSeries(s1,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 1");

        List s2 = getSeries(20, 10);
        XYSeries series2 = new SimpleXYSeries(s2,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 2");

        LineAndPointFormatter s1Format = new LineAndPointFormatter();
        s1Format.setPointLabelFormatter(new PointLabelFormatter());
        s1Format.configure(getApplicationContext(),
                R.xml.lpf1);
        plot.addSeries(series1, s1Format);

        LineAndPointFormatter s2Format = new LineAndPointFormatter();
        s2Format.setPointLabelFormatter(new PointLabelFormatter());
        s2Format.configure(getApplicationContext(),
                R.xml.lpf2);
        plot.addSeries(series2, s2Format);

        plot.setTicksPerRangeLabel(1);
        plot.getGraphWidget().setDomainLabelOrientation(-45);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recording_result, menu);
        return true;
    }

    private List getSeries(int count, int max) {
        List series = new ArrayList();
        Random rand = new Random();
        for (int i = 1; i <= count; i++) {
            int value = rand.nextInt(max);
            series.add(rand.nextInt(max));
        }
        return series;
    }
}