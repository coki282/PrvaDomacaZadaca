package coki.prvadomacazadaca;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public class GraphicDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_display);
        Complex result = (Complex) getIntent().getExtras().get(MainActivity.RESULTCOMPLEX);
        Complex a = (Complex) getIntent().getExtras().get(MainActivity.ACOMPLEX);
        Complex b = (Complex) getIntent().getExtras().get(MainActivity.BCOMPLEX);

        XYSeries xseries = new XYSeries("x");
        xseries.add(0,0);
        xseries.add(a.re(), a.im());
        XYSeries yseries = new XYSeries("y");
        yseries.add(0,0);
        yseries.add(b.re(), b.im());
        XYSeries rseries = new XYSeries("rezultat");
        rseries.add(0,0);
        rseries.add(result.re(), result.im());

        XYSeriesRenderer arenderer = new XYSeriesRenderer();
        arenderer.setLineWidth(2);
        arenderer.setColor(Color.RED);
        arenderer.setDisplayBoundingPoints(true);
        arenderer.setPointStyle(PointStyle.CIRCLE);
        arenderer.setPointStrokeWidth(3);

        XYSeriesRenderer brenderer = new XYSeriesRenderer();
        brenderer.setLineWidth(2);
        brenderer.setColor(Color.BLUE);
        brenderer.setDisplayBoundingPoints(true);
        brenderer.setPointStyle(PointStyle.CIRCLE);
        brenderer.setPointStrokeWidth(3);

        XYSeriesRenderer rrenderer = new XYSeriesRenderer();
        rrenderer.setLineWidth(2);
        rrenderer.setColor(Color.GREEN);
        rrenderer.setDisplayBoundingPoints(true);
        rrenderer.setPointStyle(PointStyle.CIRCLE);
        rrenderer.setPointStrokeWidth(3);

        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        mRenderer.addSeriesRenderer(arenderer);
        mRenderer.addSeriesRenderer(brenderer);
        mRenderer.addSeriesRenderer(rrenderer);
        mRenderer.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00));
        mRenderer.setPanEnabled(false, false);
        mRenderer.setShowGrid(true);

        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        dataset.addSeries(xseries);
        dataset.addSeries(yseries);
        dataset.addSeries(rseries);

        GraphicalView chartView = ChartFactory.
                getLineChartView(this, dataset, mRenderer);

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.graphic_display);
        myLayout.addView(chartView);
    }
}
