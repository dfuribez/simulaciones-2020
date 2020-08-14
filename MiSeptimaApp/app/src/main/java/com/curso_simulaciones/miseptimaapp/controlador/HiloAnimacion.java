package com.curso_simulaciones.miseptimaapp.controlador;

import com.curso_simulaciones.miseptimaapp.objetos_laboratorio.Polea;
import com.curso_simulaciones.miseptimaapp.vista.Pizarra;

public class HiloAnimacion extends Thread {
    private boolean corriendo;
    private long periodo_muestreo = 50;
    public float timepo;

    private Pizarra pizarra;
    private Polea[] poleas;

    public HiloAnimacion(Pizarra pizarra, Polea[] poleas) {
        this.pizarra = pizarra;
        this.poleas = poleas;
    }

    @Override
    public void run() {
        corriendo = true;

        while (corriendo == true) {
            try {
                Thread.sleep(periodo_muestreo);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timepo += 0.1f;
            cambiarEstadosEscenaPizarra(timepo);
        }
    }

    private void cambiarEstadosEscenaPizarra(float timepo) {
        float posicion[];

        float x_1 = 100f + 20 * timepo;
        float y_1 = 100f;

        float x_2 = 100f;
        float y_2 = 250f;
        float theta_2 = 50f * timepo;

        float x_3 = 100f + 20f * timepo;
        float y_3 = 450f;
        float theta_3 = 50f * timepo;

        poleas[0].moverPolea(x_1, y_1);
        poleas[1].moverPolea(theta_2);
        poleas[2].moverPolea(x_3, y_3, theta_3);

        pizarra.setEstadoEscena(poleas);
    }
}
