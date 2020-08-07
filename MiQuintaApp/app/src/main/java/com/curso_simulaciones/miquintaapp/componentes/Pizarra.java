package com.curso_simulaciones.miquintaapp.componentes;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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

        // Circunferencia externa

        RectF rect = new RectF(
                esquinaIzquierda,
                esquinaArriba,
                esquinaDerecha,
                esquinaAbajo);

        float externalWidth = 10f;

        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(externalWidth);
        canvas.drawArc(rect, 0, 360, true, pincel);


        float radio = (float)(0.5 * largo - (externalWidth / 2.0));
        pincel.setColor(Color.YELLOW);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(Color.BLACK);

        // Dibujamos primer arco
        // Angulo inicio: 130, final 280

        float offset = 40f; // offset para las circunferencias interiores

        RectF rectangulo_interno = new RectF(
                esquinaIzquierda + offset,
                esquinaArriba + offset,
                esquinaDerecha - offset,
                esquinaAbajo - offset);

        canvas.rotate(120,0,0);
        dibujarArco(
                0,
                120,
                Color.BLACK,
                rectangulo_interno,
                pincel,
                canvas);

        dibujarArco(
                20,
                80,
                Color.RED,
                rectangulo_interno,
                pincel,
                canvas);

        dibujarArco(
                80,
                120,
                Color.rgb(116,116,116),
                rectangulo_interno,
                pincel,
                canvas);
        //canvas.rotate(-120, 0, 0);
        pincel.setColor(Color.YELLOW);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, radio - 70, pincel);
        pincel.setColor(Color.BLACK);

        float indent = (float)(0.2 * largo);
        float posicionY = (float)(0.5 * largo);



        int[] array_colores = {Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLACK, Color.BLACK};
        int[] array_values = {0, 20, 40, 60, 80, 100, 120};

        for (int i = 0; i < array_values.length; i++) {
            dibujarTick(array_values[i], pincel, canvas, -posicionY, -posicionY+indent, array_colores[i]);
        }

        pincel.setStrokeWidth(20f);
        pincel.setStyle(Paint.Style.FILL_AND_STROKE);
        pincel.setColor(Color.GRAY);
        canvas.drawCircle(0, 0, 10, pincel);
        float angle = valueToAngle(70) + 90f;
        canvas.rotate(angle);
        canvas.drawLine(0f, -200f, 0f, 0f, pincel);
        canvas.rotate(-angle);
/*
        float medida = 36;
        float angulo_medida = 240 + 36f * (240f / 100);
        pincel.setStrokeWidth(5);
        pincel.setColor(Color.RED);
        canvas.rotate(angulo_medida, 0, 0);
        canvas.drawLine(0, -posicionY, 0, 0, pincel);
        canvas.rotate(-angulo_medida, 0, 0);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,(float)(0.1 * indent),pincel);
 */
    }



    private void dibujarArco(int valorInicial,
                             int valorFinal,
                             int color,
                             RectF rectangulo,
                             Paint pincel,
                             Canvas canvas) {

        float anguloInicio = valueToAngle(valorInicial);
        float anguloFinal = valueToAngle(valorFinal) - anguloInicio;

        pincel.setColor(color);
        pincel.setStyle(Paint.Style.FILL);

        canvas.drawArc(
                rectangulo,
                anguloInicio,
                anguloFinal,
                true,
                pincel);

    }

    private float valueToAngle(int value) {
        float angle = 0f;
        if (value <= 20) {
            angle = (float)(value * 3);
        } else if (value > 20 && value <= 100) {
            angle = (float)(60 + (value - 20) * 2.25);
        } else if (value > 100) {
            angle = (float)(20 * 3 + 80 * 2.25 + (value - 100) * 3);
        }

        return angle;
    }

    private void dibujarTick(int valor, Paint pincel, Canvas canvas, float y0, float y1, int color) {
        float angle = valueToAngle(valor) + 90;
        canvas.rotate(angle, 0, 0);
        pincel.setColor(color);
        pincel.setStrokeWidth(1f);
        canvas.drawLine(0, y0, 0, y1, pincel);

        String tick_text = String.format("%s", valor);
        float ancho_texto = -0.5f * pincel.measureText(tick_text);
        pincel.setTextSize(25f);
        canvas.drawText(tick_text, ancho_texto, y1 + (y1 * -0.2f), pincel);

        canvas.rotate(-angle);
    }

}