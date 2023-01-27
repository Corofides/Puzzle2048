import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korim.font.*

class Block(val number: Number, val cellWidth: Double, val cellHeight: Double, val font : TtfFont) : Container() {
    init {
        roundRect(cellWidth, cellHeight, 5.0, fill = number.color);
        val textColor = when (number) {
            Number.ZERO, Number.ONE -> Colors.BLACK
            else -> Colors.WHITE
        }
        text(number.value.toString(), textSizeFor(number), textColor, font) {
            centerBetween(0.0, 0.0, cellWidth, cellHeight);
        }
    }

    private fun textSizeFor(number: Number) = when (number) {
        Number.ZERO, Number.ONE, Number.TWO, Number.THREE, Number.FOUR, Number.FIVE -> cellHeight / 2
        Number.SIX, Number.SEVEN, Number.EIGHT -> cellHeight * 4 / 9
        Number.NINE, Number.TEN, Number.ELEVEN, Number.TWELVE -> cellHeight * 2 / 5
        Number.THIRTEEN, Number.FOURTEEN, Number.FIFTEEN -> cellHeight * 7 / 20
        Number.SIXTEEN -> cellHeight * 3 / 10
    }

    fun Container.block(number: Number, cellWidth: Double, cellHeight: Double, font: TtfFont) = Block(number, cellWidth, cellHeight, font).addTo(this);
}
