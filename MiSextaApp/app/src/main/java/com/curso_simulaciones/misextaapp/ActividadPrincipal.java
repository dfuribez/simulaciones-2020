package com.curso_simulaciones.misextaapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.misextaapp.objetos_laboratorio.Polea;
import com.curso_simulaciones.misextaapp.vista.Pizarra;

public class ActividadPrincipal extends Activity {
    Pizarra pizarra;
    private Polea polea_1, polea_2, polea_3;

    public Polea poleas[] = new Polea[3];


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crearElementosGui();

        ViewGroup.LayoutParams parametro_layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        this.setContentView(crearGui(), parametro_layout_principal);
        crearObjetosLaboratorio();
    }

    private void crearElementosGui() {
        pizarra = new Pizarra(this);
        pizarra.setBackgroundColor(Color.BLACK);
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
        parametros_pegada.weight = 1.0f;

        linear_principal.addView(pizarra, parametros_pegada);

        return linear_principal;
    }

    private void crearObjetosLaboratorio() {
        polea_1 = new Polea();
        polea_2 = new Polea(600f, 200f, 150f);
        polea_3 = new Polea(600f, 600f, 150f);

        polea_3.setColorPolea(Color.GREEN);

        poleas[0] = polea_1;
        poleas[1] = polea_2;
        poleas[2] = polea_3;

        pizarra.setEstadoEscena(poleas);
    }


}
