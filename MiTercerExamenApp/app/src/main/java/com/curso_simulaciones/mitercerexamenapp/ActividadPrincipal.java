package com.curso_simulaciones.mitercerexamenapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.mitercerexamenapp.componentes.Pizarra;

public class ActividadPrincipal extends Activity {

    private Pizarra lienzo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crearElementosGui();
        ViewGroup.LayoutParams parametro_principal = new
                ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
        this.setContentView(crearGui(), parametro_principal);
    }

    private void crearElementosGui() {
        lienzo = new Pizarra(this);
        lienzo.setBackgroundColor(Color.BLACK);
    }

    private LinearLayout crearGui() {
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);

        linear_principal.setBackgroundColor(Color.BLACK);

        LinearLayout.LayoutParams parametros_pegada_lienzo = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

        parametros_pegada_lienzo.setMargins(20, 20, 20, 20);
        parametros_pegada_lienzo.weight = 1.0f;

        linear_principal.addView(lienzo, parametros_pegada_lienzo);
        return linear_principal;
    }
}
