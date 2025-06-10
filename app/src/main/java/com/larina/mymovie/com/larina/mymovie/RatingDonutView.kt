package com.larina.mymovie

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.larina.mymovie.R  // Import your R file

class RatingDonutView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    // Овал для рисования сегментов прогресс бара
    private val oval = RectF()

    // Координаты центра View, а также Radius
    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    // Толщина линии прогресса
    private var stroke = 10f

    // Значение прогресса от 0 - 100
    var progress: Int = 50
        set(value) {
            field = value.coerceIn(0, 100) // Ensure progress is within 0-100
            updatePaintColors() // Update paint colors when progress changes
            invalidate() // Redraw the view
        }

    // Значения размера текста внутри кольца
    private var scaleSize = 60f

    // Краски для наших фигур
    private var strokePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG) // Initialize with default value
    private var digitPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)  // Initialize with default value
    private var circlePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG) // Initialize with default value

    init {
        // Получаем атрибуты и устанавливаем их в соответствующие поля
        val a = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.RatingDonutView, // Use your R.styleable
            0,
            0
        )
        try {
            stroke = a.getFloat(R.styleable.RatingDonutView_stroke, stroke)
            progress = a.getInt(R.styleable.RatingDonutView_progress, progress)
        } finally {
            a.recycle()
        }

        // Инициализируем первоначальные краски
        initPaint()
    }

    private fun initPaint() {
        // Краска для колец
        strokePaint.apply {
            style = Paint.Style.STROKE
            // Сюда кладем значение из поля класса, потому как у нас краски будут видоизменяться
            strokeWidth = stroke
            // Цвет мы тоже будем получать в специальном методе, потому что в зависимости от рейтинга
            // мы будем менять цвет нашего кольца
            color = getPaintColor(progress)
            strokeCap = Paint.Cap.ROUND // Add rounded corners to the stroke
        }
        // Краска для цифр
        digitPaint.apply {
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 2f // Consider making this configurable
            setShadowLayer(5f, 0f, 0f, Color.DKGRAY)
            textSize = scaleSize
            typeface = Typeface.SANS_SERIF
            textAlign = Paint.Align.CENTER // Center the text horizontally
            color = getPaintColor(progress)
        }
        // Краска для заднего фона
        circlePaint.apply {
            style = Paint.Style.FILL
            color = Color.DKGRAY
        }
    }

    // Определение цвета в зависимости от прогресса
    private fun getPaintColor(progress: Int): Int = when (progress) {
        in 0..25 -> Color.parseColor("#4B0082")
        in 26..50 -> Color.parseColor("#9400D3")
        in 51..75 -> Color.parseColor("#9ACD32")
        else -> Color.parseColor("#00FF00")
    }

    private fun updatePaintColors() {
        val color = getPaintColor(progress)
        strokePaint.color = color
        digitPaint.color = color
    }

    // Конвертирую прогресс (0–100) в градусы (0–360)
    private fun convertProgressToDegrees(progress: Int): Float = progress * 3.6f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = if (w > h) {
            h.div(2f)
        } else {
            w.div(2f)
        }
        centerX = w / 2f
        centerY = h / 2f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val chosenWidth = chooseDimension(widthMode, widthSize)
        val chosenHeight = chooseDimension(heightMode, heightSize)

        val minSide = Math.min(chosenWidth, chosenHeight)
        centerX = minSide.div(2f)
        centerY = minSide.div(2f)

        setMeasuredDimension(minSide, minSide)
    }

    private fun chooseDimension(mode: Int, size: Int) =
        when (mode) {
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
            else -> 300
        }

    private fun drawRating(canvas: Canvas) {
        // Здесь мы можем регулировать размер нашего кольца
        val scale = radius * 0.8f
        // Сохраняем канвас
        canvas.save()
        // Перемещаем нулевые координаты канваса в центр, вы помните, так проще рисовать все круглое
        canvas.translate(centerX, centerY)
        // Устанавливаем размеры под наш овал
        oval.set(0f - scale, 0f - scale, scale, scale)
        // Рисуем задний фон(Желательно его отрисовать один раз в bitmap, так как он статичный)
        canvas.drawCircle(0f, 0f, radius, circlePaint)
        // Рисуем "арки", из них и будет состоять наше кольцо + у нас тут специальный метод
        canvas.drawArc(oval, -90f, convertProgressToDegrees(progress), false, strokePaint)
        // Восстанавливаем канвас
        canvas.restore()
    }

    private fun drawText(canvas: Canvas) {
        // Форматируем текст, чтобы мы выводили дробное число с одной цифрой после точки
        val message = String.format("%.1f", progress / 10f)
        // Получаем ширину и высоту текста, чтобы компенсировать их при отрисовке, чтобы текст был
        // точно в центре
        val widths = FloatArray(message.length)
        digitPaint.getTextWidths(message, widths)
        var advance = 0f
        for (width in widths) advance += width
        // Рисуем наш текст
        canvas.drawText(message, 0f - advance / 2, 0f + digitPaint.textSize / 4, digitPaint)
    }

    override fun onDraw(canvas: Canvas) {
        // Рисуем кольцо и задний фон
        drawRating(canvas)
        // Рисуем цифры
        drawText(canvas)
    }
}
