package com.curso_simulaciones.micuartaapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class Pizarra  extends View {

    public Pizarra(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        Paint pincel = new Paint();

        pincel.setAntiAlias(true);
        int anchoPizarra = this.getWidth();
        int altoPizzarra = this.getHeight();

        float centroXPizarra = (float)(0.5 * anchoPizarra);
        float centroYPizarra = (float)(0.5 * altoPizzarra);

        float largo = 400f;
        float alto = 400f;

        float esquinaIzquierda = -(float)(0.5 * largo);
        float esquinaArriba = -(float)(0.5 * alto);
        float esquinaDerecha = (float)(0.5 * largo);
        float esquinaAbajo = (float)(0.5 * alto);

        canvas.translate(centroXPizarra, centroYPizarra);

        RectF rect = new RectF(
                esquinaIzquierda,
                esquinaArriba,
                esquinaDerecha,
                esquinaAbajo);

        pincel.setColor(Color.YELLOW);
        canvas.drawArc(rect, 150, 240, true, pincel);

        float radio = (float)(0.4 * largo);
        pincel.setColor(Color.WHITE);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(0, 0, radio, pincel);

        pincel.setStyle(Paint.Style.FILL);
        pincel.setStrokeWidth(2);

        float indent = (float)(0.2 * largo);
        float posicionY = (float)(0.5 * largo);

        for (int i=0; i < 6; i++) {
            float anguloRotacion = 240 + 48 * i;

            canvas.rotate(anguloRotacion, 0, 0);
            canvas.drawLine(0, -posicionY, 0, -posicionY + indent, pincel);

            int valorMarca = 20 * i;
            String numero = "" + valorMarca;
            float anchoCadenaNumero = pincel.measureText(numero);
            float posicionXNumero = -0.5f * anchoCadenaNumero;
            float posicionYNumero = (float)(-posicionY + 1.2 * indent);

            canvas.drawText(numero, posicionXNumero, posicionYNumero, pincel);

            canvas.rotate(-anguloRotacion, 0, 0);
        }

        for (int i=0; i < 21; i++) {
            float angulo_rotacion = 240 + 12 * i;
            canvas.rotate(angulo_rotacion,0,0);
            canvas.drawLine(
                    0,
                    -posicionY,
                    0,
                    -posicionY + (float)(0.2 * indent),
                    pincel);
            canvas.rotate(-angulo_rotacion, 0, 0);
        }

        float medida = 36;
        float angulo_medida = 240 + 36f * (240f / 100);
        pincel.setStrokeWidth(5);
        pincel.setColor(Color.RED);
        canvas.rotate(angulo_medida, 0, 0);
        canvas.drawLine(0, -posicionY, 0, 0, pincel);
        canvas.rotate(-angulo_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,(float)(0.1 * indent),pincel);
    }
}