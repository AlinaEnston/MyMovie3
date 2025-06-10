package com.larina.mymovie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

class RatingDonutView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    // Овал для рисования дуг прогресс-бара
    private val oval = RectF()
    // Координаты центра и радиус кольца
    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f
    // Толщина линии прогресса
    private var stroke = 10f

    // Прогресс 0..100
    var progress: Int = 50  // Keep the default value
        set(value) {
            field = value.coerceIn(0, 100)
            updatePaintColors()
            invalidate()
        }

    // Размер текста
    private var scaleSize = 60f

    // Краски для кольца, текста и заднего фона
    private var strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = stroke
        strokeCap = Paint.Cap.ROUND
    }

    private var digitPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        textSize = scaleSize
        typeface = Typeface.SANS_SERIF
        textAlign = Paint.Align.CENTER
        setShadowLayer(5f, 0f, 0f, Color.DKGRAY)
    }

    private var circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.DKGRAY
    }

    private fun updatePaintColors() {
        val color = getPaintColor(progress)
        strokePaint.color = color
        digitPaint.color = color
    }

    // Определение цвета в зависимости от прогресса
    private fun getPaintColor(progress: Int): Int = when (progress) {
        in 0..25 -> Color.parseColor("#e84258") // Красный
        in 26..50 -> Color.parseColor("#fd8060") // Оранжевый
        in 51..75 -> Color.parseColor("#fee191") // Желтый
        else -> Color.parseColor("#b0d8a4") // Зеленый
    }

    // Конвертирую прогресс (0–100) в градусы (0–360)
    private fun convertProgressToDegrees(progress: Int): Float = progress * 3.6f

    // Метод рисует кольцо с прогрессом
    private fun drawRating(canvas: Canvas) {
        val scale = radius * 0.8f
        canvas.save()
        canvas.translate(centerX, centerY)
        oval.set(-scale, -scale, scale, scale)
        canvas.drawCircle(0f, 0f, radius, circlePaint)
        canvas.drawArc(oval, -90f, convertProgressToDegrees(progress), false, strokePaint)
        canvas.restore()
    }

    // Метод рисует прогресс числом с 1 знаком после запятой
    private fun drawText(canvas: Canvas) {
        val message = String.format("%.1f", progress / 10f)
        val widths = FloatArray(message.length)
        digitPaint.getTextWidths(message, widths)
        var advance = 0f
        for (width in widths) advance += width
        canvas.drawText(message, centerX - advance / 2, centerY + advance / 4, digitPaint)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = if (w > h) h / 2f else w / 2f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val chosenWidth = chooseDimension(widthMode, widthSize)
        val chosenHeight = chooseDimension(heightMode, heightSize)

        val minSide = minOf(chosenWidth, chosenHeight)
        centerX = minSide / 2f
        centerY = minSide / 2f

        setMeasuredDimension(minSide, minSide)
    }

    private fun chooseDimension(mode: Int, size: Int) =
        when (mode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
            else -> 300
        }

    override fun onDraw(canvas: Canvas) {
        // Рисуем кольцо и задний фон
        drawRating(canvas)
        // Рисуем цифры с процентом
        drawText(canvas)
    }
}
