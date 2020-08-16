package com.curso_simulaciones.migauge;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.curso_simulaciones.migauge.componenetes.Gauge;

public class ActividadPrincipal extends Activity {

    private Gauge gauge_izquierda, gauge_derecha;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crearElementosGui();

        ViewGroup.LayoutParams parametro_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);

        this.setContentView(crearGui(), parametro_principal);
    }


    private void crearElementosGui() {
        gauge_derecha = new Gauge(this);
        gauge_izquierda = new Gauge(this);
    }


    private LinearLayout crearGui() {
        LinearLayout linear_principal = new LinearLayout(this);
        linear_principal.setOrientation(LinearLayout.HORIZONTAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(0, 0, 0));
        linear_principal.setWeightSum(2f);

        LinearLayout linear_izquierdo = new LinearLayout(this);
        linear_izquierdo.setOrientation(LinearLayout.VERTICAL);
        linear_izquierdo.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_izquierdo.setGravity(Gravity.FILL);
        linear_izquierdo.setBackgroundColor(Color.BLACK);
        linear_izquierdo.setWeightSum(1f);

        LinearLayout linear_derecho = new LinearLayout(this);
        linear_derecho.setOrientation(LinearLayout.VERTICAL);
        linear_derecho.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_derecho.setGravity(Gravity.FILL);
        linear_derecho.setBackgroundColor(Color.BLACK);
        linear_derecho.setWeightSum(1f);

        LinearLayout.LayoutParams parametros_pegada = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0);

        parametros_pegada.setMargins(20, 20, 20, 20);
        parametros_pegada.weight = 1f;

        linear_derecho.addView(gauge_derecha, parametros_pegada);
        linear_izquierdo.addView(gauge_izquierda, parametros_pegada);

        LinearLayout.LayoutParams parametros_pegada_principal = new
                LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        parametros_pegada_principal.weight = 1f;
        linear_principal.addView(linear_izquierdo, parametros_pegada_principal);
        linear_principal.addView(linear_derecho, parametros_pegada_principal);

        usarMetodosGauge();
        return linear_principal;
    }

    private void usarMetodosGauge() {
        // Cambia el color de fondo
        gauge_derecha.setBackgroundColor(Color.rgb(60, 179, 113));
        // Cambia el color de las secciones circulares
        gauge_derecha.setBarColors(Color.BLACK, Color.RED, Color.DKGRAY);
        // Tamaño del gauge 50% el tamaño del layout
        gauge_derecha.setRadiusPercentage(0.5f);
        // Dibuja 5 ticks equidistantes
        gauge_derecha.setTotalTicks(5);
        // Coloca la aguja en la posicón 60
        gauge_derecha.setValue(60);

        // Gauge con rango 0 a 90
        gauge_izquierda.setRange(0, 90);
        // Dibuja 10 ticks equidistantes
        gauge_izquierda.setTotalTicks(10);
        // Coloca la aguja en el valor 70
        gauge_izquierda.setValue(70);
        // Cambia las unidades del gauge a psi
        gauge_izquierda.changeUnits("psi");
        // Cambia el rango de  las secciones circulares
        gauge_izquierda.setBarRanges(0, 30, 30, 60, 60, 90);
    }
}
