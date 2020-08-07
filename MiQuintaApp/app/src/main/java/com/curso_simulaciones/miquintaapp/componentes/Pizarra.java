package com.curso_simulaciones.miquintaapp.componentes;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class Pizarra  extends View {
    Paint pincel = new Paint();
    RectF oval_rect1 = new RectF(-40, 80, 40, 120);

    public Pizarra(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
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
        RectF rect = new RectF(esquinaIzquierda, esquinaArriba, esquinaDerecha,
                esquinaAbajo);

        float externalWidth = 6f;
        pincel.setColor(Color.WHITE);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(externalWidth);
        canvas.drawArc(rect, 0, 360, true, pincel);

        // Fondo del widget
        float radio = (float)(0.5 * largo - (externalWidth / 2.0));
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, radio, pincel);

        float offset = 20f; // offset para las circunferencias interiores

        RectF rectangulo_interno = new RectF(esquinaIzquierda + offset,
                esquinaArriba + offset, esquinaDerecha - offset,
                esquinaAbajo - offset);

        // colores circunferencias exteriores
        int color_first_segment = Color.rgb(255,99,71);
        int color_second_segment = Color.rgb(60,179,113);
        int color_third_segment = Color.rgb(143,188,143);

        canvas.rotate(135,0,0);
        dibujarArco(0, 20,
                color_first_segment, rectangulo_interno,
                pincel, canvas);

        dibujarArco(20, 80,
                color_second_segment, rectangulo_interno,
                pincel, canvas);

        dibujarArco(80, 120,
                color_third_segment, rectangulo_interno,
                pincel, canvas);

        // Circunferencia interior
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, radio - 45, pincel);
        pincel.setColor(Color.BLACK);

        float indent = (float)(0.2 * largo);
        float posicionY = (float)(0.5 * largo);

        // Arrays con posiciones y colores para los ticks
        int[] array_colores = {color_first_segment, color_first_segment,
                color_second_segment, color_second_segment,
                Color.rgb(220,20,60),
                color_third_segment, color_third_segment};

        int[] array_values = {0, 20, 40, 60, 80, 100, 120};

        for (int i = 0; i < array_values.length; i++) {
            dibujarTick(array_values[i], pincel, canvas, -posicionY+offset,
                    -posicionY+indent, array_colores[i]);
        }

        // Aguja
        pincel.setStrokeWidth(5f);
        pincel.setStyle(Paint.Style.FILL_AND_STROKE);
        pincel.setColor(Color.rgb(220,20,60));
        canvas.drawCircle(0, 0, 10, pincel);
        float angle = valueToAngle(70) + 90f;
        canvas.rotate(angle);
        canvas.drawLine(0f, -(posicionY - offset * 3f), 0f, 30f, pincel);
        canvas.rotate(-angle, 0, 0);
        canvas.rotate(-135, 0, 0);

        // Texto inferior
        pincel.setTextAlign(Paint.Align.CENTER);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(2.5f);
        canvas.drawRoundRect(oval_rect1, 5f, 5f, pincel);

        pincel.setStrokeWidth(2f);
        pincel.setColor(Color.rgb(220,20,60));
        pincel.setStyle(Paint.Style.FILL);

        float text_offset = pincel.getStrokeWidth() * 4;

        canvas.drawText("70.0", oval_rect1.centerX(),
                oval_rect1.centerY() + text_offset, pincel);

        canvas.drawText("mm de Hg", oval_rect1.centerX(),
                oval_rect1.centerY() + 60, pincel);

    }


    private void dibujarArco(int valorInicial, int valorFinal,  int color,
                             RectF rectangulo, Paint pincel, Canvas canvas) {

        float anguloInicio = valueToAngle(valorInicial);
        float anguloFinal = valueToAngle(valorFinal) - anguloInicio;

        pincel.setColor(color);
        pincel.setStyle(Paint.Style.STROKE);

        canvas.drawArc(rectangulo, anguloInicio, anguloFinal, true,
                pincel);
    }


    private float valueToAngle(int value) {
        float angle = (float)(value * 2.25);
        return angle;
    }

    
    private void dibujarTick(int valor, Paint pincel, Canvas canvas, float y0,
                             float y1, int color) {
        float angle = valueToAngle(valor) + 90;
        canvas.rotate(angle, 0, 0);
        pincel.setColor(color);
        pincel.setStrokeWidth(7f);
        canvas.drawLine(0, y0-3f, 0, y0+30f, pincel);

        String tick_text = String.format("%s", valor);
        float ancho_texto = -0.5f * pincel.measureText(tick_text);
        pincel.setTextSize(25f);
        pincel.setColor(color);
        canvas.drawText(tick_text, ancho_texto, y0 + 60f, pincel);

        canvas.rotate(-angle);
    }

}