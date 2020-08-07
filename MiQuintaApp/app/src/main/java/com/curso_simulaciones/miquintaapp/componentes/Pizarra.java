package com.curso_simulaciones.miquintaapp.componentes;


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

        // Circunferencia externa

        RectF rect = new RectF(esquinaIzquierda, esquinaArriba, esquinaDerecha,
                esquinaAbajo);

        float externalWidth = 6f;

        pincel.setColor(Color.WHITE);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(externalWidth);
        canvas.drawArc(rect, 0, 360, true, pincel);


        float radio = (float)(0.5 * largo - (externalWidth / 2.0));
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, radio, pincel);
        pincel.setColor(Color.BLACK);

        // Dibujamos primer arco
        // Angulo inicio: 130, final 280

        float offset = 20f; // offset para las circunferencias interiores

        RectF rectangulo_interno = new RectF(esquinaIzquierda + offset,
                esquinaArriba + offset, esquinaDerecha - offset,
                esquinaAbajo - offset);

        canvas.rotate(135,0,0);
        dibujarArco(0, 120,
                Color.rgb(128,0,0), rectangulo_interno,
                pincel, canvas);

        dibujarArco(20, 80,
                Color.rgb(51, 102, 0), rectangulo_interno,
                pincel, canvas);

        dibujarArco(80, 120,
                Color.rgb(127,255,0), rectangulo_interno,
                pincel, canvas);

        //canvas.rotate(-120, 0, 0);
        pincel.setColor(Color.BLACK);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, radio - 45, pincel);
        pincel.setColor(Color.BLACK);

        float indent = (float)(0.2 * largo);
        float posicionY = (float)(0.5 * largo);


        int[] array_colores = {Color.GRAY,
                Color.GRAY, Color.GRAY, Color.GRAY,
                Color.rgb(148, 0, 0), Color.GRAY, Color.GRAY};
        int[] array_values = {0, 20, 40, 60, 80, 100, 120};

        for (int i = 0; i < array_values.length; i++) {
            dibujarTick(array_values[i], pincel, canvas, -posicionY+offset,
                    -posicionY+indent, array_colores[i]);
        }

        // Aguja
        pincel.setStrokeWidth(5f);
        pincel.setStyle(Paint.Style.FILL_AND_STROKE);
        pincel.setColor(Color.rgb(255,0,0));
        canvas.drawCircle(0, 0, 10, pincel);
        float angle = valueToAngle(70) + 90f;
        canvas.rotate(angle);
        canvas.drawLine(0f, -(posicionY - offset * 3f), 0f, 20f, pincel);
        canvas.rotate(-angle, 0, 0);
        canvas.rotate(-135, 0, 0);


        // Texto inferior
        RectF oval_rect1 = new RectF(-40, 80, 40, 120);
        RectF oval_rect2 = new RectF(-38, 82, 38, 118);

        pincel.setTextAlign(Paint.Align.CENTER);
        canvas.drawRoundRect(oval_rect1, 5f, 5f, pincel);

        pincel.setColor(Color.BLACK);
        canvas.drawRoundRect(oval_rect2, 5f, 5f, pincel);

        pincel.setColor(Color.RED);
        pincel.setStrokeWidth(2f);
        pincel.setStyle(Paint.Style.FILL);
        float text_offset = pincel.getStrokeWidth() * 4;
        canvas.drawText("70.0", oval_rect2.centerX(),
                oval_rect2.centerY() + text_offset, pincel);
        pincel.setColor(Color.WHITE);
        canvas.drawText("mm de HG", oval_rect1.centerX(),
                oval_rect1.centerY() + 60, pincel);

    }

    private void dibujarArco(int valorInicial, int valorFinal,  int color,
                             RectF rectangulo, Paint pincel, Canvas canvas) {

        float anguloInicio = valueToAngle(valorInicial);
        float anguloFinal = valueToAngle(valorFinal) - anguloInicio;

        pincel.setColor(color);
        pincel.setStyle(Paint.Style.FILL);

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
        pincel.setStrokeWidth(2f);
        canvas.drawLine(0, y0, 0, y1, pincel);

        String tick_text = String.format("%s", valor);
        float ancho_texto = -0.5f * pincel.measureText(tick_text);
        pincel.setTextSize(25f);
        pincel.setColor(color);
        canvas.drawText(tick_text, ancho_texto, y1 + (y1 * -0.2f), pincel);

        canvas.rotate(-angle);
    }

}