package com.curso_simulaciones.misextaapp.vista;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.curso_simulaciones.misextaapp.objetos_laboratorio.Polea;

public class Pizarra extends View {
    private Polea poleas[];


    public Pizarra(Context context) {
        super(context);
    }

    public void setEstadoEscena(Polea[] poleas) {
        this.poleas = poleas;
    }

    private void dibujarEscena(Canvas canvas, Paint pincel) {
        for (int i=0; i<poleas.length; i++) {
            if (poleas[i] != null) {
                poleas[i].dibujese(canvas, pincel);
            }
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint pincel = new Paint();
        pincel.setAntiAlias(true);

        if (poleas != null) {
            dibujarEscena(canvas, pincel);
        }
        invalidate();
    }

}
