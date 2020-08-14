package com.curso_simulaciones.miseptimaapp.objetos_laboratorio;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Polea {
    private float radio;
    private float posicionX, posicionY, posicionAngular;
    private int color = Color.rgb(255, 0, 0);

    public Polea() {
        this.radio = 20f;
    }

    public Polea(float posicionX, float posicionY, float radio) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.radio = radio;
    }

    public void setRadioPolea(float radio) {
        this.radio = radio;
    }

    public float getRadioPolea() {
        return radio;
    }

    public void setColorPolea(int color) {
        this.color = color;
    }

    public int getColorPolea() {
        return color;
    }

    public void moverPolea(float posicionX, float posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public void moverPolea(float posicionAngular) {
        this.posicionAngular = posicionAngular;
    }

    public void moverPolea(float posicionX, float posicionY, float posicionAngular) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.posicionAngular = posicionAngular;
    }

    public void dibujese(Canvas canvas, Paint pincel) {
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(2f);
        pincel.setColor(color);
        canvas.save();

        canvas.rotate(posicionAngular, posicionX, posicionY);
        canvas.drawCircle(posicionX, posicionY, radio, pincel);

        for (int i=0; i < 12; i++) {
            canvas.rotate(i * 36f, posicionX, posicionY);
            canvas.drawLine(posicionX, posicionY, posicionX, posicionY - radio,
                    pincel);
            canvas.rotate(-i * 36f, posicionX, posicionY);
        }

        canvas.restore();
    }
}
