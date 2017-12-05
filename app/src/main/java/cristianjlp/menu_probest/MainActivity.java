package cristianjlp.menu_probest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ejercicio1();
        ejercicio2();
        ejercicio3();
        ejercicio4();

    }

    private void ejercicio1(){
        Button ejercicio1 = (Button) findViewById(R.id.btn_ej1);
        ejercicio1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Ejerciciouno.class));
            }
        });
    }
    private void ejercicio2() {
        Button ejercicio2 = (Button) findViewById(R.id.btn_ej2);
        ejercicio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Ejerciciodos.class));
            }
        });
    }
    private void ejercicio3() {
        Button ejercicio3 = (Button) findViewById(R.id.btn_ej3);
        ejercicio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Ejerciciotres.class));
            }
        });
    }
    private void ejercicio4() {
        Button ejercicio4 = (Button) findViewById(R.id.btn_ej4);
        ejercicio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Ejerciciocuatro.class));
            }
        });
    }
}
