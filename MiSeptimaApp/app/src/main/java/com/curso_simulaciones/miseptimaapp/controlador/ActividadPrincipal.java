package com.curso_simulaciones.miseptimaapp.controlador;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.miseptimaapp.objetos_laboratorio.Polea;
import com.curso_simulaciones.miseptimaapp.vista.Pizarra;

public class ActividadPrincipal extends Activity {
    Pizarra pizarra;

    private Polea polea_1, polea_2, polea_3;
    public Polea[] poleas = new Polea[10];

    private HiloAnimacion hilo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crearElementosGui();

        ViewGroup.LayoutParams parametro_layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        this.setContentView(crearGui(), parametro_layout_principal);

        crearObjetosLaboratorio();

        hilo = new HiloAnimacion(pizarra, poleas);
        hilo.start();
    }

    private void crearElementosGui() {
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.WHITE);
    }

    private LinearLayout crearGui() {
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(250, 150, 50));

        LinearLayout.LayoutParams parametros_pegada = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

        parametros_pegada.setMargins(50, 50, 50, 50);
        parametros_pegada.weight = 1f;

        linear_principal.addView(pizarra, parametros_pegada);
        return linear_principal;
    }

    private void crearObjetosLaboratorio() {
        polea_1 = new Polea(100f, 100f, 50f);
        polea_2 = new Polea(100f, 250f, 100f);
        polea_3 = new Polea(100f, 450f, 50f);

        polea_2.setColorPolea(Color.BLACK);
        polea_3.setColorPolea(Color.MAGENTA);

        poleas[0] = polea_1;
        poleas[1] = polea_2;
        poleas[2] = polea_3;

    }
}
