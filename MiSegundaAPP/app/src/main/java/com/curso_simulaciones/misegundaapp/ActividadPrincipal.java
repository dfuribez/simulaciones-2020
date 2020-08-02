package com.curso_simulaciones.misegundaapp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActividadPrincipal extends Activity {
    private TextView resultados;
    private String cadena;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // llama al método que crea los elementos de la GUI
        crearElementosGui();

        // Indica como se adaṕta la GUI a la pantalla
        ViewGroup.LayoutParams parametro_layout_principal = new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        this.setContentView(crearGui(), parametro_layout_principal);

        calculoOverflowByte();
        calculoOverflowShort();
        calculoOverflowInt();
        calculoOverflowLong();
        calculoOverflowFloat();
        calculoOverflowDouble();

        calculoUnderflowFloat();
        calculoUnderflowDouble();

        calculoEpsilonFloat();
        calculoEpsilonDouble();

        desplegarResultado();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void crearElementosGui() {
        resultados = new TextView(this);
        resultados.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        resultados.setTextColor(Color.YELLOW);
        resultados.setBackgroundColor(Color.BLACK);
    }

    private LinearLayout crearGui() {
        LinearLayout linear_principal = new LinearLayout(this);

        linear_principal.setOrientation(LinearLayout.VERTICAL);
        linear_principal.setGravity(Gravity.CENTER_HORIZONTAL);
        linear_principal.setGravity(Gravity.FILL);
        linear_principal.setBackgroundColor(Color.rgb(250, 150, 50));

        LinearLayout.LayoutParams parametrosPegada = new
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

        parametrosPegada.setMargins(50, 50, 50, 50);
        parametrosPegada.weight = 1.0f;

        linear_principal.addView(resultados, parametrosPegada);

        return linear_principal;
    }

    private void calculoOverflowByte() {
        byte x = 127;
        byte y = 1;
        byte z = (byte)(x + y);

        String resultado = String.format(" Resultados\n Overflow en byte: %d + %d = %d\n", x, y, z);
        cadena = resultado;
    }

    private void calculoOverflowShort() {
        short x = 32767;
        short y = 1;
        short z = (short) (x + y);

        String resultado = String.format(" Overflow en short %d + %d = %d\n", x, y, z);
        cadena += resultado;
    }

    private void calculoOverflowInt() {
        int x = 2147483647;
        int y = 1;
        int z = (int)(x + y);

        String resultado = String.format(" Overflow en int %d + %d = %d\n", x, y, z);
        cadena += resultado;
    }

    private void calculoOverflowLong() {
        long x = (long)(Math.pow(2, 63) - 1);
        long y = 1;
        long z = x + y;

        String resultado = String.format(" Overflow en long: %d + %d = %d\n", x, y, z);
        cadena += resultado;
    }

    private void calculoOverflowFloat() {
        float x = (float)(3.40282347 * Math.pow(10, 38));
        float y = (float)(0.0000001 * x);
        float z = x + y;

        String resultado = String.format(" Overflow en float: %6.7e + %6.7e = %6.7e\n", x, y, z);
        cadena += resultado;
    }

    private void calculoOverflowDouble() {
        double x = 1.7976931348623157 * Math.pow(10, 308);
        double y = 0.0000000000000001 * x;
        double z= x + y;

        String resultado = String.format(" Overflow en double: %6.7e + %6.7e = %6.7e\n", x, y, z);
        cadena += resultado;
    }

    private void calculoUnderflowFloat() {
        float x = (float)Math.pow(2, -149);
        float y = 2f;
        float z = x / y;

        String resultado = String.format(" Underflow en float: %6.3e / %f = %f\n", x, y, z);
        cadena += resultado;
    }

    private void calculoUnderflowDouble() {
        double x = Math.pow(2, -1074);
        double y = 2d;
        double z = x / y;

        String resultado = String.format(" Underflow en doble: %6.3e / %f = %f\n", x, y, z);
        cadena += resultado;
    }

    private void calculoEpsilonDouble() {
        float epsilonTeoricoDouble = 0.5f * (float)(Math.pow(2, 1-52));
        float epsilonCalculadoDouble = 1f;
        do {
            epsilonCalculadoDouble = epsilonCalculadoDouble / 2;
        } while(epsilonCalculadoDouble + 1.0d > 1.0d);

        epsilonCalculadoDouble = 2 * epsilonCalculadoDouble;
        cadena += String.format(
                " Epsilon double teórico %6.7e, epsilon double computacional %6.7e\n",
                epsilonTeoricoDouble,
                epsilonCalculadoDouble);
    }

    private void calculoEpsilonFloat(){
        float epsilonTeoricoFloat = 0.5f * (float)(Math.pow(2, 1-23));
        float epsilonCalculadoFloat = 1f;
        do {
            epsilonCalculadoFloat = epsilonCalculadoFloat / 2;
        } while(epsilonCalculadoFloat + 1.0f > 1.0f);
        epsilonCalculadoFloat = 2 * epsilonCalculadoFloat;
        cadena += String.format(
                " Epsilon float teórico %6.7e, epsilon float computacional %6.7e\n",
                epsilonTeoricoFloat,
                epsilonCalculadoFloat);
    }

    private void desplegarResultado() {
        resultados.setText(cadena);
    }

}
