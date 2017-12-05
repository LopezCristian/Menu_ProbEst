package cristianjlp.menu_probest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by Cristianjlp on 24/10/2017.
 */

public class Ejerciciouno extends AppCompatActivity{

    private EditText etNumeros;
    private TextView tv4, mostrarCadena;
    ArrayList<Double> lista = new ArrayList<>();
    double media,varVarianza;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejerciciouno);

        tv4=(TextView)findViewById(R.id.tv4);
        etNumeros=(EditText)findViewById(R.id.editTextNumeros);
        mostrarCadena=(TextView)findViewById(R.id.tvCadena);
    }

    public void agregar_valores(View view){
        String cadena=etNumeros.getText().toString();
        if (cadena.equals("")){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "INGRESE MINIMO 2 VALORES", Toast.LENGTH_SHORT);

            toast1.show();
        }else {
            double cadena2 = Double.parseDouble(cadena);
            if (cadena != "") {
                lista.add(cadena2);
                mostrarCadena.setText(lista.toString());
                etNumeros.setText("");
            }
        }
    }
    public void media (View view){
        if (lista.size()==0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "no hay numeros", Toast.LENGTH_SHORT);
            toast1.show();
        }else{
            media2();
        }
    }
    public void mediana(View view) {
        if (lista.size()==0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "no hay numeros", Toast.LENGTH_SHORT);

            toast1.show();
        }
        else {
            Collections.sort(lista);
            mostrarCadena.setText(lista.toString());
            int mediana = lista.size() / 2;
            mostrarCadena.setText(lista.toString());
            etNumeros.setText("");
            String cadenaMediana;
            if (lista.size() % 2 == 0) {
                Double mediana3 = (lista.get(mediana) + lista.get(mediana - 1)) / 2;
                cadenaMediana = Double.toString(mediana3);
                tv4.setText(cadenaMediana);
            } else {
                Double mediana3 = lista.get(mediana);
                cadenaMediana = Double.toString(mediana3);
                tv4.setText(cadenaMediana);
            }
        }
    }
    public void varianza(View view){
        if (lista.size()==0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "no hay numeros", Toast.LENGTH_SHORT);
            toast1.show();
        }else {
            varianza2();
        }
    }
    public void desviacion_estandar(View view){
        if (lista.size()==0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "no hay numeros", Toast.LENGTH_SHORT);
            toast1.show();
        }else {
            varianza2();
            Double desEstandar;
            desEstandar = Math.sqrt(varVarianza);
            String imprime = Double.toString(desEstandar);
            tv4.setText(imprime);
        }
    }
    public void moda(View view){
        if (lista.size()==0){
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "no hay numeros", Toast.LENGTH_SHORT);
            toast1.show();
        }else {
            int maximaVecesQueSeRepite = 0;
            double moda = 0;
            for (int i = 0; i < lista.size(); i++) {
                int vecesQueSeRepite = 0;
                for (int j = 0; j < lista.size(); j++) {
                    if (lista.get(i).equals(lista.get(j))) {
                        vecesQueSeRepite++;
                    }
                }
                if (vecesQueSeRepite > maximaVecesQueSeRepite) {
                    moda = lista.get(i);
                    maximaVecesQueSeRepite = vecesQueSeRepite;
                }
            }
            String imprime = Double.toString(moda);
            tv4.setText(imprime);
        }
    }
    public void limpiarArray(View view){
        lista.clear();
        mostrarCadena.setText(lista.toString());
        tv4.setText("Resultado");
    }

    private void varianza2(){
        media2();
        double varianza=0;
        double pre=0;
        Iterator it = lista.iterator();
        while ( it.hasNext() ) {
            double objeto= (double) it.next();
            pre=objeto-media;
            varianza=varianza+(pre*pre);
        }
        varianza=varianza/lista.size();
        varVarianza=varianza;
        String imprime = Double.toString(varianza);
        tv4.setText(imprime);
    }
    private void media2() {

        double suma2=0.0;
        Iterator it = lista.iterator();
        while ( it.hasNext() ) {
            double objeto= (double) it.next();
            suma2=suma2+objeto;
        }
        suma2=suma2/lista.size();
        media=suma2;
        String total2 = Double.toString(suma2);
        tv4.setText(total2);
    }
    public  void  salir(View view){
        finish();
    }

}
