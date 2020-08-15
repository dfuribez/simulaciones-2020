package com.curso_simulaciones.migauge.componenetes;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;

import java.util.Locale;

public class Gauge extends View {

    private Paint pincel = new Paint();

    RectF bars_rect;

    private int background_color = Color.rgb(0, 0, 0);

    // Colores por defecto para las barras
    private int color_bar_1 = Color.RED;
    private int color_bar_2 = Color.BLUE;
    private int color_bar_3 = Color.GRAY;

    // Parametros del layout
    private float ancho;
    private float alto;

    private float center_x;
    private float center_y;

    // Rangos del gauge
    private float min_range = 0;
    private float max_range = 120;

    private float radius_percentage = 1f;
    private float radius;

    // Posición por defecto de las barras interiores
    private float bar_1_start = 0f, bar_1_end = 20f;
    private float bar_2_start = 30f, bar_2_end = 60f;
    private float bar_3_start = 100f, bar_3_end = 120f;

    private float inner_margin_top;
    private float inner_margin_bottom;

    private int total_ticks = 4;

    public Gauge(Context context) {
        super(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
    }

    public void setBackgroundColor(int color) {
        this.background_color = color;
    }


    /**
     * Permite cabiar el tamaño en un porcentaje al tamaño del layout
     * @param percentage valor en el intervalo (0, 1]
     */
    public void setRadiusPercentage(float percentage) {
        this.radius_percentage = percentage;
    }

    public void setTotalTicks(int total) {
        this.total_ticks = total;
    }

    public void setRange(float min_range, float max_range) {
        this.min_range = min_range;
        this.max_range = max_range;
    }

    public void setBarColors(int color_1, int color_2, int color_3) {
        this.color_bar_1 = color_1;
        this.color_bar_2 = color_2;
        this.color_bar_3 = color_3;
    }

    public void setBarRanges(float start_1, float end_1, float start_2, float end_2,
                             float start_3, float end_3) {
        this.bar_1_start = start_1;
        this.bar_2_start = start_2;
        this.bar_3_start = start_3;

        this.bar_1_end = end_1;
        this.bar_2_end = end_2;
        this.bar_3_end = end_3;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        calculateSizes();

        pincel.setColor(this.background_color);
        this.pincel.setAntiAlias(true);

        canvas.translate(this.center_x, this.center_y);
        canvas.save();

        // Cuerpo del Gauge
        canvas.drawCircle(0, 0, this.radius, pincel);

        // Dibuja la primer barra
        drawBar(this.bar_1_start, this.bar_1_end, this.color_bar_1, canvas);
        drawBar(this.bar_2_start, this.bar_2_end, this.color_bar_2, canvas);
        drawBar(this.bar_3_start, this.bar_3_end, this.color_bar_3, canvas);

        // Fondo del gauge
        this.pincel.setColor(this.background_color);
        canvas.drawCircle(0, 0, this.radius - this.inner_margin_bottom, pincel);

        drawTicks(canvas);
        drawNeedle(this.max_range / 3.5f, canvas);

        this.pincel.setTypeface(Typeface.MONOSPACE);
    }

    /**
     * Calcula ciertos tamños importantes
     */
    private void calculateSizes() {
        this.ancho = this.getWidth();
        this.alto = this.getHeight();

        this.center_x = 0.5f * this.ancho;
        this.center_y = 0.5f * this.alto;

        if (this.ancho > this.alto) {
            this.radius = this.radius_percentage * this.alto;
        } else {
            this.radius = this.radius_percentage * this.ancho;
        }

        this.radius *= 0.5f;

        this.inner_margin_top = this.radius * 0.1f;
        this.inner_margin_bottom = this.radius * 0.25f;

    }

    /**
     * Calcula el ángulo correspondiente a un valor
     * @param value valor al acual calcular el ángulo correspondiente
     * @return el ángulo correspondiente
     */
    private float valueToAngle(float value) {
        float factor = 270f / (this.max_range - this.min_range);
        return value * factor;
    }

    /**
     * Dibuja las barras interiores
     * @param start valor de inicio
     * @param end valor final
     * @param color color de la barra
     * @param canvas canvas en donde dibujar
     */
    private void drawBar(float start, float end, int color, Canvas canvas) {
        canvas.rotate(135f);
        float startAngle = valueToAngle(start);
        float endAngle = valueToAngle(end) - startAngle;

        this.pincel.setColor(color);
        this.pincel.setStrokeWidth(this.radius * 0.05f);
        this.pincel.setStyle(Paint.Style.FILL);

        float right_bottom = this.radius - this.inner_margin_top;
        float left_top = -this.radius + this.inner_margin_top;

        RectF rect_bars = new RectF(left_top, left_top, right_bottom, right_bottom);

        canvas.drawArc(rect_bars, startAngle, endAngle, true, this.pincel);
        canvas.rotate(-135f);
    }

    private void drawTicks(Canvas canvas) {
        this.total_ticks -= 1;
        int interval = (int)(this.max_range - this.min_range) / this.total_ticks;

        this.pincel.setColor(Color.WHITE);
        this.pincel.setStrokeWidth(2f);

        float text_size = this.radius * 0.15f;
        canvas.rotate(225f);
        for (int tick=0; tick <= this.total_ticks; tick++) {
            float angle = valueToAngle(tick * interval);

            String value = String.format(Locale.US, "%d", tick * interval);
            float value_width = -0.5f * this.pincel.measureText(value);
            this.pincel.setTextSize(text_size);

            canvas.rotate(angle, 0, 0);
            canvas.drawLine(0, -this.radius+this.inner_margin_top,
                    0, -this.radius+this.inner_margin_bottom*1.15f, this.pincel);

            canvas.drawText(value, value_width,
                    -this.radius+this.inner_margin_bottom * 1.7f,
                    this.pincel);
            canvas.rotate(-angle, 0, 0);
        }
        canvas.rotate(-225f);
    }

    private void drawNeedle(float value, Canvas canvas) {
        float angle = valueToAngle(value) + 225f;
        canvas.rotate(angle);

        this.pincel.setColor(Color.GREEN);
        this.pincel.setStrokeWidth(this.radius * 0.025f);

//        this.pincel.setfa

        canvas.drawLine(0f, -this.radius+this.inner_margin_bottom*1.50f, 0f,
                this.radius*0.1f, this.pincel);

        canvas.drawCircle(0, 0, this.radius * 0.025f * 2f, this.pincel);
        canvas.rotate(-angle);
        canvas.drawText("text", 0, 0, this.pincel);
    }

}
