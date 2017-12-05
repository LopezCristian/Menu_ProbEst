package cristianjlp.menu_probest;

import android.content.DialogInterface;

import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Cristianjlp on 19/11/2017.
 */

public class Ejerciciocuatro extends AppCompatActivity {

    DecimalFormat form = new DecimalFormat("0.000");

    private EditText add_get_half, add_get_fashion;
    private TextView res_half, res_fashion, res_median,tv_total;
    private TextView result_k, result_moment_one,result_moment_two,result_moment_tree;
    private TextView bias_2;
    private Button cal_sesgo,delete_, btn_bias_k;

    double sum_half=0,fx_half,r_half;                               //Media
    double p;                                                       //Moda
    double total_bias, s, b_bias,total_bias_2,total_bias_3, k, s_2; //sesgo y apuntamiento (CURTOSIS)
    double moment_one, moment_two, moment_tree;

    ArrayList<Double> a_half = new ArrayList<>();
    ArrayList<Double> a_x_r_half = new ArrayList<>();
    ArrayList<Double> x_r_half_cuadro = new ArrayList<>();
    ArrayList<Double> x_r_half_cubica = new ArrayList<>();
    ArrayList<Double> x_r_half_cuatro = new ArrayList<>();
    ArrayList<Double> r_bias = new ArrayList<>();
    ArrayList<Double> r_bias_cubica = new ArrayList<>();
    ArrayList<Double> r_bias_cuatro = new ArrayList<>();
    ArrayList<Double> x = new ArrayList<>();
    ArrayList<Double> y = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejerciciocuatro);

        cal_sesgo = (Button) findViewById(R.id.cal_sesgo);
        delete_ = (Button) findViewById(R.id.delete_);
        btn_bias_k = (Button) findViewById(R.id.btn_bias_k);

        res_half = (TextView) findViewById(R.id.res_half);
        res_fashion = (TextView) findViewById(R.id.res_fashion);
        res_median = (TextView) findViewById(R.id.res_median);
        tv_total = (TextView) findViewById(R.id.tv_total);

        result_moment_one = (TextView) findViewById(R.id.result_moment_one);
        result_moment_two = (TextView) findViewById(R.id.result_moment_two);
        result_moment_tree = (TextView) findViewById(R.id.result_moment_tree);
        result_k = (TextView) findViewById(R.id.result_k);
        bias_2 = (TextView) findViewById(R.id.bias_2);

        add_get_half = (EditText) findViewById(R.id.add_get_half);
        add_get_fashion = (EditText) findViewById(R.id.add_get_fashion);

    }
    public void add_bias(View view){
        String c_h = add_get_half.getText().toString();
        String c_f = add_get_fashion.getText().toString();

        if (c_h.equals("") && c_f.equals("")) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "INGRESE VALORES EN X y Y", Toast.LENGTH_SHORT);
            toast1.show();
        } else {
            if (c_h.equals("")) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "INGRESE UN VALOR EN X", Toast.LENGTH_SHORT);
                toast1.show();
            }else{
                if (c_f.equals("")) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "INGRESE UN VALOR EN Y", Toast.LENGTH_SHORT);
                    toast1.show();
                }else {
                        double cadena1 = Double.parseDouble(c_h);
                        double cadena2 = Double.parseDouble(c_f);

                        if (c_h !="" && c_f!=""){
                            x.add(cadena1);
                            y.add(cadena2);

                            add_get_half.setText("");
                            add_get_fashion.setText("");

                            cal_sesgo.setEnabled(true);
                            delete_.setEnabled(true);
                            btn_bias_k.setEnabled(true);

                        }
                    }
                }
            }
    }
    public void half(View view){

        fashion();
        median();

        Iterator<Double> x_ = x.iterator();
        Iterator<Double> y_1 = y.iterator();
        while (x_.hasNext() && y_1.hasNext()){
            a_half.add(new Double(x_.next().doubleValue() * y_1.next().doubleValue())); // fx por partes
        }

        Iterator<Double> a_h = a_half.iterator();//Suma de fx
        while (a_h.hasNext()){
            double obj = a_h.next();
            fx_half=fx_half+obj; //variable de la suma total de fx
        }
        Iterator<Double> y_2 = y.iterator();//suma de y
        while (y_2.hasNext()){
            double obj = y_2.next();
            sum_half=sum_half+obj;//suma total de y
        }

        r_half=fx_half/sum_half;//media aritmetrica
        res_half.setText(form.format(r_half));

        Iterator<Double> x_2 = x.iterator();
        while (x_2.hasNext()){
            a_x_r_half.add(new Double(x_2.next().doubleValue() - r_half)); // x - M
        }

        Iterator<Double> r_ = a_x_r_half.iterator();
        while (r_.hasNext()){
            x_r_half_cuadro.add(new Double(Math.pow(r_.next(),2)));//(x-m)^2 por partes
        }

        Iterator<Double> q_ = x_r_half_cuadro.iterator();
        Iterator<Double> y_3 = y.iterator();
        while (q_.hasNext() && y_3.hasNext()){
            r_bias.add(new Double(q_.next().doubleValue() * y_3.next().doubleValue()));//f*(x-m)^2
        }
        Iterator<Double> b_ = r_bias.iterator();
        while (b_.hasNext()){
            double obj = b_.next();
            total_bias=total_bias+obj;//suma total de f*(x-m)^2
        }

        s = Math.sqrt(total_bias/sum_half);//S
        b_bias = ((r_half - p)/s);//Sesgo

        tv_total.setText(form.format(b_bias));

        //CURTOSIS //NUEVO
        Iterator<Double> r_3 = a_x_r_half.iterator();
        while (r_3.hasNext()){
            x_r_half_cubica.add(new Double(Math.pow(r_3.next(),3)));//(x-m)^3 por partes
        }

        Iterator<Double> q2_ = x_r_half_cubica.iterator();
        Iterator<Double> y_cubica = y.iterator();
        while (q2_.hasNext() && y_cubica.hasNext()){
            r_bias_cubica.add(new Double(q2_.next().doubleValue() * y_cubica.next().doubleValue()));//f*(x-m)^3
        }
        Iterator<Double> sum_f_cubica = r_bias_cubica.iterator();
        while (sum_f_cubica.hasNext()){
            double obj = sum_f_cubica.next();
            total_bias_2=total_bias_2+obj;//suma total de f*(x-m)^3
        }

        Iterator<Double> r_4 = a_x_r_half.iterator();
        while (r_4.hasNext()){
            x_r_half_cuatro.add(new Double(Math.pow(r_4.next(),4)));//(x-m)^4 por partes
        }

        Iterator<Double> q3_ = x_r_half_cuatro.iterator();
        Iterator<Double> y_cuatro = y.iterator();
        while (q3_.hasNext() && y_cuatro.hasNext()){
            r_bias_cuatro.add(new Double(q3_.next().doubleValue() * y_cuatro.next().doubleValue()));//f*(x-m)^4
        }
        Iterator<Double> sum_f_cuatro = r_bias_cuatro.iterator();
        while (sum_f_cuatro.hasNext()){
            double obj = sum_f_cuatro.next();
            total_bias_3=total_bias_3+obj;//suma total de f*(x-m)^4
        }

                //MOMENTOS
        moment_one=total_bias/sum_half;
        result_moment_one.setText(form.format(moment_one));
        moment_two=total_bias_2/sum_half;
        result_moment_two.setText(form.format(moment_two));
        moment_tree=total_bias_3/sum_half;
        result_moment_tree.setText(form.format(moment_tree));

        double a1,a2;

        a1= Math.pow(moment_one,3);
        a2= Math.sqrt(a1);

        //Sesgo con la formula de CURTOSIS
        s_2=((moment_two)/(a2));
        bias_2.setText(form.format(s_2));

                //K resultado
        k=(moment_tree/(Math.pow(moment_one,2)))-3;
        result_k.setText(form.format(k));

        cal_sesgo.setEnabled(false);

    }
    public void fashion() {

        for (int i = 0; i < y.size()-1; i++) {
                for (int j = 0; j < y.size()-1; j++) {
                    if (y.get(j) > y.get(j+1)) {
                        double tmp = x.get(j+1);
                        x.set(j+1,x.get(j));
                        x.set(j,tmp);

                        double tmp2 = y.get(j+1);
                        y.set(j+1,y.get(j));
                        y.set(j,tmp2);
                    }
                }
                res_fashion.setText(x.get(x.size()-1).toString());
            p = x.get(x.size()-1);
            }
    }
    public void median(){

        Collections.sort(y);
        int mediana = y.size() / 2;
        String cadenaMediana;
        if (y.size() % 2 == 0) {
            Double mediana3 = (y.get(mediana) + y.get(mediana - 1)) / 2;
            cadenaMediana = Double.toString(mediana3);
            res_median.setText(cadenaMediana);
        } else {
            Double mediana3 = y.get(mediana);
            cadenaMediana = Double.toString(mediana3);
            res_median.setText(cadenaMediana);
        }

    }
    public void vaciar(View view){
        sum_half=0;
        fx_half=0;
        r_half=0;
        p=0;

        total_bias=0;
        total_bias_2=0;
        total_bias_3=0;
        k=0;
        moment_one=0;
        moment_two=0;
        moment_tree=0;
        s=0;
        b_bias=0;
        a_half.clear();
        a_x_r_half.clear();
        x_r_half_cuadro.clear();
        x_r_half_cubica.clear();
        x_r_half_cuatro.clear();
        r_bias.clear();
        r_bias_cubica.clear();
        r_bias_cuatro.clear();
        x.clear();
        y.clear();


        res_half.setText("");
        res_fashion.setText("");
        res_median.setText("");
        tv_total.setText("");

        delete_.setEnabled(true);
        btn_bias_k.setEnabled(false);
    }
    public void msj(View view){

        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);

        if(s_2 > 0){
            builder2.setTitle("Resultados:");
            builder2.setMessage("El sesgo es Asimétrico Positivo");
            builder2.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder2.create();
            dialog.show();
        }else {
            if (s_2 < 0){
                builder2.setTitle("Resultados:");
                builder2.setMessage("El sesgo es Asimétrico Negativo");
                builder2.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder2.create();
                dialog.show();
            }else{
                if (s_2 == 0){
                    builder2.setTitle("Resultados:");
                    builder2.setMessage("El sesgo es Simétrico");
                    builder2.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder2.create();
                    dialog.show();
                }
            }
        }

        if(k > 0){
            builder3.setTitle("Resultados:");
            builder3.setMessage("El apuntamiento es Leptocúrtico");
            builder3.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder3.create();
            dialog.show();
        }else {
            if (k < 0){
                builder3.setTitle("Resultados:");
                builder3.setMessage("El apuntamiento es Platicúrtico");
                builder3.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder3.create();
                dialog.show();
            }else{
                if (k == 0){
                    builder3.setTitle("Resultados:");
                    builder3.setMessage("El apuntamiento es Mesocúrtico");
                    builder3.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder3.create();
                    dialog.show();
                }
            }
        }
    }
}
