package com.example.jeffgeoplotter;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jeffgeoplotter.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; // Create a tag

    private LineChart[] myCharts = new LineChart[4];  // Import this from the synched Github repository for the line plot/ Use for chart 1-4


    private Thread thread;
    private boolean plotData = true; // Will cntrol the actual frequency of a data


    private AppBarConfiguration appBarConfiguration; // skip for now, I think it a dependency of new android setting
    private ActivityMainBinding binding; // skip for now, I think it a dependency of new android setting


    // In the on create method, just after savedInstanceState and the setcontentview which in this case is dependent on binding
    // Initialize the sensor, then read the sensor, then initialize the chart
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Assign the LineCharts to LineChart items in the activity.xml file
        myCharts[0] = (LineChart) findViewById(R.id.chart1);
        myCharts[1] = (LineChart) findViewById(R.id.chart2);
        myCharts[2] = (LineChart) findViewById(R.id.chart3);
        myCharts[3] = (LineChart) findViewById(R.id.chart4);

        for (int i = 0; i < myCharts.length; i++) {
            LineData data = getData(36, 100); // count = 36, range = 100
            setupChart(myCharts[i], data, myColors[i]);
        }


    }


    private void setupChart(LineChart chart, LineData data, int color) {
        ((LineDataSet) data.getDataSetByIndex(0)).setCircleHoleColor(color);
//                setCircleColorHole(color);

        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);

        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(false);

        chart.setBackgroundColor(color);
        chart.setViewPortOffsets(10, 0, 10, 0);

        chart.setData(data);

    }

    private LineData getData(int count, int range) {
        ArrayList<Entry> yVals = new ArrayList<>();

        for (int i = 0; i<count; i++){
            float val = (float) (Math.random() * range) + 3;
            yVals.add(new Entry(i, val));
        }

        LineDataSet set1 = new LineDataSet(yVals, "Data set");

        set1.setLineWidth(3f);
        set1.setCircleRadius(5f);
        set1.setCircleHoleRadius(2.5f);
        set1.setColor(Color.LTGRAY);
        set1.setCircleColor(Color.WHITE);
        set1.setHighLightColor(Color.WHITE);
        set1.setDrawValues(false);

        LineData data = new LineData(set1);
        return data;

    }

    private int[] myColors = new int[]{
            Color.rgb(137, 230, 30), Color.rgb(89, 199, 250), Color.rgb(250, 104, 119)
};


}

