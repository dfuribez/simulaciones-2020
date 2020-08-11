package com.curso_simulaciones.mitercerexamenapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Pizarra extends View {
    Paint pincel = new Paint();

    // Propiedades de la clase
    private int ancho = 0;  // ancho del widget
    private int alto = 0;  // alto del widget

    private float x_center = 0f;  // centro de la pantalla
    private float y_center = 0f;  // centro de la pantalla

    private float widget_radius = 0f;  // Radio exterior del widget
    private float inner_radius = 0f;  // inner widget radius
    private float inner_thick = 0f;  // grosor de la circunferencia interna


    public Pizarra(Context context) {
        super(context);
    }


    protected void onDraw(Canvas canvas) {
        pincel.setAntiAlias(true);

        calculateSizes();  //  Calcula los tamaños necesarios

        canvas.translate(this.x_center, this.y_center);
        // Dibujamos la circunferencia externa
        pincel.setColor(Color.BLUE);
        canvas.drawCircle(0, 0, this.widget_radius, pincel);

        // Dibuja la circunferencia interior
        pincel.setColor(Color.WHITE);
        pincel.setStrokeWidth(this.inner_thick);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0, this.inner_radius, pincel);
        pincel.setColor(Color.BLUE);
        canvas.drawCircle(0, 0, this.inner_radius - this.inner_thick,
                pincel);

        // Dibuja todas las líneas y circulos interiores
        drawInnerCircles(0f, canvas, pincel);
        drawInnerCircles(90f, canvas, pincel);
        drawInnerCircles(45, canvas, pincel);
        drawInnerCircles(-45, canvas, pincel);

        // Dibuja las circunferencias más internas
        pincel.setColor(Color.WHITE);
        canvas.drawCircle(0, 0, this.inner_radius * 0.4f, pincel);
        pincel.setColor(Color.BLACK);
        canvas.drawCircle(0, 0, this.inner_radius * 0.1f, pincel);
    }

    /**
     * Método que dibuja las líneas y circunferencias internas
     * @param angle angulo de rotación en donde colocar las circunferencias
     * @param canvas canvas en donde dibujar
     * @param pincel paint con las propiedades del dibujo (será modificado)
     */
    private void drawInnerCircles(float angle, Canvas canvas, Paint pincel) {
        pincel.setColor(Color.RED);
        // Rota el canvas por el ángulo dado
        canvas.rotate(angle, 0, 0);
        // Dibuja las circunferencias en lados opuestos
        canvas.drawCircle(0, -this.inner_radius+this.inner_thick * 0.5f,
                this.inner_thick * 0.5f, pincel);
        canvas.drawCircle(0, this.inner_radius-this.inner_thick * 0.5f,
                this.inner_thick * 0.5f, pincel);

        // Dibuja la línea
        pincel.setColor(Color.WHITE);
        pincel.setStrokeWidth(this.inner_thick * 0.4f);
        canvas.drawLine(0, this.inner_radius-this.inner_thick,
                0, -this.inner_radius+this.inner_thick, pincel);

        // Devuelve el canvas a su posición original
        canvas.rotate(-angle, 0, 0);
    }

    /**
     * Método que se encarga de calcular los tamaños más importantes
     */
    private void calculateSizes() {
        this.ancho = this.getWidth();
        this.alto = this.getHeight();

        // Calcula los centros
        this.x_center = (float)(this.ancho * 0.5f);
        this.y_center = (float)(this.alto * 0.5f);

        // El radio del elemento será el 80% de la dimensión menor
        if (this.alto > this.ancho) {
            this.widget_radius = 0.8f * this.ancho * 0.5f;
        } else {
            this.widget_radius = 0.8f * this.alto * 0.5f;
        }

        this.inner_radius = this.widget_radius * 0.7f;  // Radio de la circunferencia interior
        this.inner_thick = this.inner_radius * 0.20f;  // Ancho de la circunferencia interior

    }
}
