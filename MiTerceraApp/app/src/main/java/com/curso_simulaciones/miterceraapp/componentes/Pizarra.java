package com.curso_simulaciones.miterceraapp.componentes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;

public class Pizarra  extends View {

    public Pizarra(Context context) {
        super(context);
    }

    protected void onDraw(Canvas canvas) {
        Paint pincel = new Paint();
        pincel.setAntiAlias(true);

        pincel.setColor(Color.RED);
        pincel.setTextSize(40f);

        canvas.drawText(
                "Hola jóvenes, bienvenidos a sus primeros dibujos",
                20f,
                50f,
                pincel);

        pincel.setStrokeWidth(5);
        pincel.setColor(Color.BLACK);
        canvas.drawLine(30, 100, 200, 180, pincel);

        pincel.setStrokeWidth(10);
        pincel.setStyle(Paint.Style.STROKE);
        pincel.setColor(Color.RED);
        canvas.drawCircle(600, 350, 150, pincel);

        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.argb(100, 0,250, 0));
        canvas.drawRect(400, 150, 800, 550, pincel);

        pincel.setStyle(Paint.Style.FILL);
        pincel.setColor(Color.rgb(255, 0, 50));
        canvas.drawRect(350, 250, 850, 450, pincel);

        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(2);
        pincel.setColor(Color.BLACK);
        canvas.drawRect(550, 100, 700,200, pincel);

        RectF rectF = new RectF(50, 200, 300, 450);
        pincel.setColor(Color.MAGENTA);
        canvas.drawArc(rectF, 90, 135, false, pincel);

        RectF rectF_1 = new RectF(50, 100, 300, 350);
        pincel.setStrokeWidth(0.5f);
        pincel.setColor(Color.BLACK);
        canvas.drawArc(rectF_1, 90, 135, true, pincel);

        RectF rectF_2 = new RectF(450, 100, 600, 350);
        pincel.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF_2, 90, 135, true, pincel);

        pincel.setStyle(Paint.Style.STROKE);
        pincel.setStrokeWidth(2);
        pincel.setColor(Color.BLUE);
        Point a = new Point(600, 400);
        Point b = new Point(300, 650);
        Point c = new Point(700, 500);
        Path path = new Path();
        path.moveTo(a.x, a.y);
        path.lineTo(b.x, b.y);
        path.moveTo(b.x, b.y);
        path.lineTo(c.x, c.y);
        path.moveTo(c.x, c.y);
        path.lineTo(a.x, a.y);
        path.close();
        canvas.drawPath(path, pincel);

        Path trazo = new Path();
        trazo.addCircle(800, 400, 100, Path.Direction.CCW);
        pincel.setColor(Color.CYAN);
        pincel.setStrokeWidth(8);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawPath(trazo, pincel);
        pincel.setStrokeWidth(1);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setTextSize(20);
        pincel.setTypeface(Typeface.SANS_SERIF);
        pincel.setColor(Color.BLACK);
        canvas.drawTextOnPath("Escuela de Física Universidad Nacional de Colombia - Medellín-", trazo, 10, 40, pincel);
    }
}
