package cristianjlp.menu_probest;


import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.Paint;

import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.text.method.ScrollingMovementMethod;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidplot.Plot;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PanZoom;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepMode;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.ZoomEstimator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Cristianjlp on 28/10/2017.
 */

public class Ejerciciotres extends AppCompatActivity {

    DecimalFormat form = new DecimalFormat("0.000");

    //-----------------VARIABLES-------------------------//
    //varianza
    double sumax=0,sumay=0,msx=0,msy=0;
    //covarianza
    double suma_=0.0,suma_1=0,suma_2=0;
    //media_aritmetrica
    double sum_1=0,sum_2=0;
    //media
    double suma2=0.0;
    //pearson
    double psum_=0, Ex = 0, Ey = 0,tx = 0,ty=0,tx2=0,ty2=0,sxr=0,syr=0, vpx=0, vpy=0,sx=0,sy=0, m=0, b=0,p=0, es=0;
    //-----------------VARIABLES-------------------------//

    private XYPlot myXYPlot;
    private PanZoom panZoom;

    private EditText x, f;
    private TextView tvintervalosx, tvfrecuencia, res_ma_x, res_ma_y, res_cv, tv_v_x, tv_v_y, prueba, recta;
    private Button cal;

    //---------------------------------------Arreglos---------------------------------------------//
    ArrayList<Double> intervalosx = new ArrayList<>();
    ArrayList<Double> all = new ArrayList<>();
    ArrayList<Double> frecuencia = new ArrayList<>();
    ArrayList<Double> result = new ArrayList<>();
    ArrayList<Double> media = new ArrayList<>();
    ArrayList<Double> gy = new ArrayList<>();
    ArrayList<Double> pearsonx = new ArrayList<>();
    ArrayList<Double> pearsony = new ArrayList<>();
    ArrayList<Double> xy  = new ArrayList<>();

    Number[] ptn_ = {0};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejerciciotres);

        tvintervalosx = (TextView) findViewById(R.id.tvintervalosx);
        tvfrecuencia = (TextView) findViewById(R.id.tvfrecuencia);
        res_ma_x = (TextView) findViewById(R.id.res_ma_x);
        res_ma_y = (TextView) findViewById(R.id.res_ma_y);
        res_cv = (TextView) findViewById(R.id.res_cv);
        tv_v_x = (TextView) findViewById(R.id.tv_v_x);
        tv_v_y = (TextView) findViewById(R.id.tv_v_y);
        cal=(Button) findViewById(R.id.cal);
        recta=(TextView) findViewById(R.id.recta);
        prueba = (TextView) findViewById(R.id.prueba);

        tvintervalosx.setMovementMethod(new ScrollingMovementMethod());
        tvfrecuencia.setMovementMethod(new ScrollingMovementMethod());

        x = (EditText) findViewById(R.id.x);
        f = (EditText) findViewById(R.id.f);

        myXYPlot = (XYPlot) findViewById(R.id.myXYPlot);
        myXYPlot.getBackgroundPaint().setColor(Color.WHITE);
        myXYPlot.setBorderStyle(XYPlot.BorderStyle.NONE, null, null);
        myXYPlot.getGraph().getBackgroundPaint().setColor(Color.WHITE);
        myXYPlot.getGraph().getGridBackgroundPaint().setColor(Color.WHITE);

        //--------------------------------------------------------------------------- nuevo

        myXYPlot.getGraph().setLinesPerRangeLabel(2);
        myXYPlot.getGraph().setLinesPerDomainLabel(2);
        myXYPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.LEFT).
                setFormat(new DecimalFormat("#####"));
        myXYPlot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).
                setFormat(new DecimalFormat("#####.#"));

        myXYPlot.setRangeLabel("");
        myXYPlot.setDomainLabel("");

        myXYPlot.setBorderStyle(Plot.BorderStyle.NONE, null, null);

        panZoom = PanZoom.attach(myXYPlot, PanZoom.Pan.BOTH, PanZoom.Zoom.STRETCH_BOTH, PanZoom.ZoomLimit.MIN_TICKS);
        myXYPlot.getOuterLimits().set(-3000, 3000, -1000, 1000);

        myXYPlot.getRegistry().setEstimator(new ZoomEstimator());
        //--------------------------------------------------------------------------- nuevo

        myXYPlot.setUserDomainOrigin(0);
        myXYPlot.setUserRangeOrigin(0);

        myXYPlot.setDomainStep(StepMode.INCREMENT_BY_VAL, 1);
        myXYPlot.setDomainStepValue(0);
        myXYPlot.setDomainStepValue(1);

        myXYPlot.setRangeStep(StepMode.INCREMENT_BY_VAL, 1);
        myXYPlot.setRangeStepValue(0);
        myXYPlot.setRangeStepValue(1);

        myXYPlot.centerOnDomainOrigin(0);
        myXYPlot.centerOnRangeOrigin(0);

        myXYPlot.getGraph().getRangeOriginLinePaint().setColor(Color.rgb(0,120,120));
        myXYPlot.getGraph().getRangeOriginLinePaint().setStrokeWidth(6);

        myXYPlot.setRangeStep(StepMode.SUBDIVIDE, frecuencia.size());
        myXYPlot.setRangeStepValue(0);

        myXYPlot.getGraph().getDomainOriginLinePaint().setColor(Color.rgb(0,120,120));
        myXYPlot.getGraph().getDomainOriginLinePaint().setStrokeWidth(6);
        myXYPlot.setDomainStepValue(0);
        myXYPlot.setDomainStep(StepMode.SUBDIVIDE, intervalosx.size());

        myXYPlot.setDomainStep(StepMode.INCREMENT_BY_VAL,1);
        myXYPlot.setRangeStep(StepMode.INCREMENT_BY_VAL,1);

        myXYPlot.getLinesPerDomainLabel();
        myXYPlot.getLinesPerRangeLabel();

        XYSeries ptn = new SimpleXYSeries(
                Arrays.asList(ptn_), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Central"
        );

        LineAndPointFormatter ptn_Format = new LineAndPointFormatter(

                Color.rgb(0, 0, 0),
                Color.rgb(0, 0, 0),
                0x000000,null);

        myXYPlot.addSeries(ptn,ptn_Format);

        myXYPlot.redraw();

        myXYPlot.getLegend().setVisible(true);

}

    public void add_values_i(View view) {
        String cadenax = x.getText().toString();
        String cadenaf = f.getText().toString();



        if (cadenax.equals("") && cadenaf.equals("")) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "INGRESE UN VALOR", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            if (cadenaf.equals("")) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "INGRESE UN VALOR EN FRECUENCIA", Toast.LENGTH_SHORT);
                toast1.show();
            }else{
                if (cadenax.equals("")) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "INGRESE UN VALOR EN INTERVALO", Toast.LENGTH_SHORT);
                    toast1.show();
                }else {
                    double cadena2 = Double.parseDouble(cadenax);
                    double cadena3 = Double.parseDouble(cadenaf);
                    if (cadenax !="" && cadenaf!=""){
                        intervalosx.add(cadena2);
                        frecuencia.add(cadena3);
                        for (int i = 0; i < (intervalosx.size()-1); i++){
                            for (int j = 0; j < (intervalosx.size()-1); j++){
                                if (intervalosx.get(j) > intervalosx.get(j+1)){
                                    double tmp = intervalosx.get(j+1);
                                    intervalosx.set(j+1,intervalosx.get(j));
                                    intervalosx.set(j,tmp);

                                    double tmp2 = frecuencia.get(j+1);
                                    frecuencia.set(j+1,frecuencia.get(j));
                                    frecuencia.set(j,tmp2);
                                }
                            }
                        }

                        tvintervalosx.setText(intervalosx.toString());
                        tvfrecuencia.setText(frecuencia.toString());

                        x.setText("");
                        f.setText("");

                        cal.setEnabled(true);
                    }
                }
            }
        }
    }
    public void cal(View view){
        //media_();
        //varianza();
        //straight_regression();
        media_arit();
        covarianza();
        mxy();
        msxy();
        mxyp2();
        Sxyp2();
        vpearson();
        pendiente();
        pearson();

        gy.clear();

        //VARIANZA
        tv_v_x.setText("x :"+ form.format(vpx));
        tv_v_y.setText("y :"+form.format(vpy));

        //COVARIANZA
        res_cv.setText(form.format(suma_));

        //MEDIA ARITMETRICA
        res_ma_x.setText("x: "+ form.format(sum_1));
        res_ma_y.setText("y: "+ form.format(sum_2));

        for(int i=0;i < intervalosx.size();i++){
            all.add(intervalosx.get(i));
            all.add(frecuencia.get(i));
        }

        XYSeries series1 = new SimpleXYSeries(
                all, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED,
                "");

//-----------------------------Color de la linea, punto y relleno----------------------------------//
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),
                Color.rgb(0, 100, 0),
                0x000000,null);
        series1Format.getLinePaint().setStrokeWidth(2);
        series1Format.getLinePaint().setStrokeJoin(Paint.Join.ROUND);
//-----------------------------Color de la linea, punto y relleno----------------------------------//
        series1Format.setLegendIconEnabled(false);

        cal.setEnabled(false);

        myXYPlot.addSeries(series1,series1Format);
        myXYPlot.redraw();
    }
    public void limpiar_graf(View view){
        myXYPlot.clear();
        intervalosx.clear();
        all.clear();
        frecuencia.clear();
        result.clear();
        media.clear();
        gy.clear();
        pearsonx.clear();
        pearsony.clear();
        xy.clear();

        suma2=0;
        sum_1=0;
        sum_2=0;
        suma_=0;
        suma_1=0;
        suma_2=0;
        sumax=0;
        sumay=0;
        msx=0;
        msy=0;

        psum_=0;
        Ex=0;
        Ey=0;
        tx=0;
        ty=0;
        tx2=0;
        ty2=0;
        sxr=0;
        syr=0;
        vpx=0;
        vpy=0;
        sx=0;
        sy=0;
        m=0;
        b=0;
        p=0;
        es=0;

        tvintervalosx.setText("intervalo");
        tvfrecuencia.setText("frecuencia");

        res_ma_x.setText("______________");
        res_ma_y.setText("______________");

        tv_v_x.setText("______________");
        tv_v_y.setText("______________");

        res_cv.setText("______________");

        prueba.setText("La correlación es buena, e: EMPTY R: EMPTY");
        recta.setText("Y: EMPTY");

        XYSeries ptn = new SimpleXYSeries(
                Arrays.asList(ptn_), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Central"
        );
        LineAndPointFormatter ptn_Format = new LineAndPointFormatter(
                Color.rgb(0, 0, 0),
                Color.rgb(0, 0, 0),
                0x000000,null);
        myXYPlot.addSeries(ptn,ptn_Format);
        myXYPlot.redraw();
    }
    //-------------------------------------------------------------------------------R.regresión
    public void media_arit(){
        Iterator<Double> itx = intervalosx.iterator();
        Iterator<Double> ity = frecuencia.iterator();

        //---------------------------Media_aritmetrica-------------------------------//
        while ( itx.hasNext() ) {
            double obj1= itx.next();
            sum_1=sum_1+obj1;
        }
        sum_1=sum_1/intervalosx.size();
        while ( ity.hasNext() ) {
            double obj2= ity.next();
            sum_2=sum_2+obj2;
        }
        sum_2=sum_2/frecuencia.size();
        //---------------------------------------------------------------------------//
    }
    public void covarianza(){
        Iterator<Double> itx = intervalosx.iterator();
        Iterator<Double> ity = frecuencia.iterator();

        //-----------------------------Covarianza---------------------------------------//
        while (itx.hasNext() && ity.hasNext()) {
            result.add(new Double(itx.next().doubleValue() * ity.next().doubleValue()));
        }
        Iterator<Double> res = result.iterator();
        while ( res.hasNext() ) {
            double obj2= res.next();
            suma_=suma_+obj2;
        }
        //------------------------------------------------------------------------------//

        suma_=suma_/intervalosx.size();

        Iterator<Double> itx_ = intervalosx.iterator();
        Iterator<Double> ity_ = frecuencia.iterator();

        while ( itx_.hasNext() ) {
            double obj1= itx_.next();
            suma_1=suma_1+obj1;
        }
        suma_1=suma_1/intervalosx.size();

        while ( ity_.hasNext() ) {
            double obj2= ity_.next();
            suma_2=suma_2+obj2;
        }
        suma_2=suma_2/frecuencia.size();

        suma_=suma_-(suma_1 * suma_2);
    }
    private void media_() {
        Iterator it = frecuencia.iterator();
        while ( it.hasNext() ) {
            double objeto= (double) it.next();
            suma2=suma2+objeto;
        }
        suma2=suma2/frecuencia.size();

        for (double i=0; i<=15; i++){

            media.add(i);
            media.add(suma2);

        }

        XYSeries series2 = new SimpleXYSeries(
                media,
                SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED,
                "");

//-----------------------------Color de la linea, punto y relleno----------------------------------//
        LineAndPointFormatter series2Format = new LineAndPointFormatter(
                Color.rgb(0, 200, 0),   //puntos
                0x000000,               //linea
                0x000000,null);         //relleno
//-----------------------------Color de la linea, punto y relleno----------------------------------//
        series2Format.setLegendIconEnabled(false);
        myXYPlot.addSeries(series2,series2Format);
        myXYPlot.redraw();
    }
    public void varianza(){

        Iterator<Double> itx = intervalosx.iterator();
        Iterator<Double> ity = frecuencia.iterator();

        Iterator<Double> mx = intervalosx.iterator();
        Iterator<Double> my = frecuencia.iterator();
        //----------------------intervalosx (x)--------------------------------------//
        while ( itx.hasNext() ) {
            double objeto= Math.pow(itx.next(),2);
            sumax=sumax+objeto;
        }
        sumax=sumax/intervalosx.size();

        while ( mx.hasNext() ) {
            double obj1= mx.next();
            msx=msx+obj1;
        }
        msx=msx/intervalosx.size();

        sumax=sumax-Math.pow(msx,2);
        //----------------------intervalosx (x)--------------------------------------///

        //------------------------frecuencia (y)------------------------------------//

        while ( ity.hasNext() ) {
            double objeto= Math.pow(ity.next(),2);
            sumay=sumay+objeto;
        }
        sumay=sumay/frecuencia.size();
        while ( my.hasNext() ) {
            double obj2= my.next();
            msy=msy+obj2;
        }
        msy=msy/frecuencia.size();

        sumay=sumay-Math.pow(msy,2);
        //------------------------frecuencia (y)------------------------------------//
    }
    public void straight_regression(){
        for (double i=-2; i<=15; i++){
            gy.add(new Double(suma_/sumax)*(i)+(suma_-sum_2));
            gy.add(i);
        }

        XYSeries series1 = new SimpleXYSeries(
                gy, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED,
                "Pearson");

//-----------------------------Color de la linea, punto y relleno----------------------------------//
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(5, 221, 235),
                Color.rgb(0, 150, 159),
                0x000000,null);
        series1Format.getVertexPaint().setStrokeWidth(8);
        series1Format.getLinePaint().setStrokeWidth(2);
        series1Format.getLinePaint().setStrokeJoin(Paint.Join.ROUND);
//-----------------------------Color de la linea, punto y relleno----------------------------------//
        series1Format.setLegendIconEnabled(true);
        myXYPlot.addSeries(series1,series1Format);
        myXYPlot.redraw();

    }
    //-------------------------------------------------------------------------------R.regresión

    //-------------------------------------------------------------------------------Pearson
    public void mxy(){
        Iterator<Double> x = intervalosx.iterator();
        Iterator<Double> y = frecuencia.iterator();

        //---------------------------------------------------------------------------x y-media_arit
        while( x.hasNext() ){
            double obj = x.next();
            pearsonx.add(obj-sum_1);
            sx=sx+obj;
        }
        while( y.hasNext() ){
            double obj = y.next();
            pearsony.add(obj-sum_2);
            sy=sy+obj;
        }
        //---------------------------------------------------------------------------x y-media_arit
    }
    public void mxyp2(){
        Iterator<Double> px = pearsonx.iterator();
        Iterator<Double> py = pearsony.iterator();
        //------------------------------------------------------------------------(x y-media_arit)^2
        while (px.hasNext()){
            double opx = px.next();
            sxr = sxr + Math.pow(opx,2);
        }
        while (py.hasNext()){
            double opy = py.next();
            syr = syr + Math.pow(opy,2);
        }


        //------------------------------------------------------------------------(x y-media_arit)^2
    }
    public void msxy(){
        Iterator<Double> x = intervalosx.iterator();
        Iterator<Double> y = frecuencia.iterator();
        //------------------------------------------------------------------------(x)^2
        while (x.hasNext()){
            double opx = x.next();
            tx = tx + Math.pow(opx,2);
        }
        tx2=Math.pow(sx,2);
        //------------------------------------------------------------------------(x)^2

        while (y.hasNext()){
            double opy = y.next();
            ty = ty + Math.pow(opy,2);
        }
        ty2=Math.pow(sy,2);
    }
    public void Sxyp2(){
        Iterator<Double> x = intervalosx.iterator();
        Iterator<Double> y = frecuencia.iterator();
        //------------------------------------------------------------------------Sumatoria x^2
        while(x.hasNext()){
            double sx = x.next();
            Ex = Ex + sx;
        }
        Ex = Math.pow(Ex,2);
        //------------------------------------------------------------------------Sumatoria x^2
        //------------------------------------------------------------------------Sumatoria y^2
        while(y.hasNext()){
            double sy = y.next();
            Ey = Ey + sy;
        }
        Ey = Math.pow(Ey,2);
        //------------------------------------------------------------------------Sumatoria y^2
    }
    public void vpearson(){
        vpx = Math.sqrt(sxr/intervalosx.size());
        vpy= Math.sqrt(syr/frecuencia.size());
    }
    public void pendiente(){
        Iterator<Double> x = intervalosx.iterator();
        Iterator<Double> y = frecuencia.iterator();
        //------------------------------------------------------------------------sumatoria(x*y)
        while (x.hasNext() && y.hasNext()) {
            xy.add(new Double(x.next().doubleValue() * y.next().doubleValue()));
        }
        //------------------------------------------------------------------------sumatoria(x*y)
        //Sumatoria de X*Y
        Iterator<Double> Sxy = xy.iterator();
        while (Sxy.hasNext()){
            double sum = Sxy.next();
            psum_ = psum_ + sum;
        }
        m = ((intervalosx.size()*psum_)-(sx*sy))/((intervalosx.size()*tx)-tx2);
        b = ((sy*tx)-(sx*psum_))/((intervalosx.size()*tx)-tx2);

    }
    public void pearson(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        p=suma_/(vpx*vpy);

        if(p <= -.9){
            es= (-1)*(p*100);
        }else {
            es= (p*100);
        }

        if(b <= -.1){
            recta.setText("Y= "+form.format(m)+"x "+form.format(b));
        }else {
            recta.setText("Y= "+form.format(m)+"x + "+form.format(b));
        }


        if(es >=70 || es<= -70){
            prueba.setText("La correlación es buena, e: "+form.format(es)+" //R: "+form.format(p));
            builder.setMessage("La correlación es BUENA");
            builder.setTitle("Pearson");
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            prueba.setText("La correlación es mala, e: "+form.format(es)+" //R: "+form.format(p));
            builder.setMessage("La correlación es MALA");
            builder.setTitle("Pearson");
            builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }


        for (double i = intervalosx.get(0); i<=intervalosx.get(intervalosx.size()-1); i++){
            gy.add(i);
            gy.add((m*i)+(b));
        }

        XYSeries series1 = new SimpleXYSeries(
                gy, SimpleXYSeries.ArrayFormat.XY_VALS_INTERLEAVED,
                "Pearson");

//-----------------------------Color de la linea, punto y relleno----------------------------------//
        LineAndPointFormatter series1Format = new LineAndPointFormatter(
                Color.rgb(5, 221, 235),
                Color.rgb(0, 150, 159),
                0x000000,null);
        series1Format.getVertexPaint().setStrokeWidth(8);
        series1Format.getLinePaint().setStrokeWidth(2);
        series1Format.getLinePaint().setStrokeJoin(Paint.Join.ROUND);
//-----------------------------Color de la linea, punto y relleno----------------------------------//
        series1Format.setLegendIconEnabled(true);
        myXYPlot.addSeries(series1,series1Format);
        myXYPlot.redraw();

    }
    //-------------------------------------------------------------------------------Pearson

}