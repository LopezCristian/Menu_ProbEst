package cristianjlp.menu_probest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Cristianjlp on 24/10/2017.
 */

public class Ejerciciodos extends AppCompatActivity {

    private EditText et_b, et_r;
    private TextView tv4dos,tv4tres, mostrarCadena1, mostrarCadena2;
    ArrayList<Double> lista1 = new ArrayList<>();
    ArrayList<Double> lista2 = new ArrayList<>();
    ArrayList<Double> result = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejerciciodos);

        tv4dos = (TextView) findViewById(R.id.tv4dos);
        tv4tres = (TextView) findViewById(R.id.tv4tres);
        et_b = (EditText) findViewById(R.id.editTextNumerosdosB);
        et_r = (EditText) findViewById(R.id.editTextNumerosdosR);
        mostrarCadena1 = (TextView) findViewById(R.id.tvCadenados1);
        mostrarCadena2 = (TextView) findViewById(R.id.tvCadenados2);
    }

    public void agregar_valores(View view) {
        String cadenab = et_b.getText().toString();
        String cadenar = et_r.getText().toString();
        if (cadenab.equals("") && cadenar.equals("")) {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "INGRESE UN VALOR", Toast.LENGTH_SHORT);

            toast1.show();
        } else {
            double cadena2 = Double.parseDouble(cadenab);
            double cadena3 = Double.parseDouble(cadenar);
            if (cadenab != "" && cadenar != "") {
                lista1.add(cadena2);
                lista2.add(cadena3);
                mostrarCadena1.setText(lista1.toString());
                mostrarCadena2.setText(lista2.toString());
                et_b.setText("");
                et_r.setText("");
            }
        }
    }

    public void media_datos_agrupados(View view) {

        Iterator<Double> it = lista1.iterator();
        Iterator<Double> it2 = lista2.iterator();

            while (it.hasNext() && it2.hasNext()) {
                result.add(new Double(it.next().doubleValue() * it2.next().doubleValue()));
            }


        tv4dos.setText(result.toString());
        suma();

        }

    private void suma() {

        double suma2=0.0;
        double suma3=0.0;
        Iterator it = result.iterator();
        while ( it.hasNext() ) {
            double objeto= (double) it.next();
            suma2=suma2+objeto;
            suma3=suma2/lista1.size();
        }
        String total2 = Double.toString(suma3);
        tv4tres.setText(total2);
    }

    public  void  salir(View view){
        finish();
    }

}
