import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.roundRect
import com.soywiz.korim.color.*
import com.soywiz.korim.font.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korio.stream.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.geom.vector.*
import com.soywiz.korma.interpolation.*

// Remember we are using: https://lospec.com/palette-list/maseclas-basic


suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer();

	sceneContainer.changeTo({ MyScene() })

}

class MyScene : Scene() {

    val leftIndent: Double = 0.0;
    val topIndent: Double = 150.0
    val guideLine: Double = 10.0
    val cellWidth: Double = 0.0;
    val cellHeight: Double = 0.0;

    fun columnX(number: Int) = leftIndent + guideLine + (cellWidth + guideLine) * number;
    fun rowY(number: Int) = topIndent + guideLine + (cellHeight + guideLine) * number;

    /*fun Container.createNewBlockWithId(id: Int, number: Number, position: Position) {
        blocks[id] = block(number).position(columnX(position.x), rowY(position.y));
    }*/

	override suspend fun SContainer.sceneMain() {
		val minDegrees = (-16).degrees
		val maxDegrees = (+16).degrees
        val stage = stage!!

        val blocks = mutableMapOf<Int, Block>()
        val freeId = 0;

        //Works
        val topIndent = 150.0
        val guideLine = 10.0;

        val columnCount = 4;
        val rowCount = 4;

        val gridHeight = stage.height - topIndent;
        val gridWidth = stage.width;

        val cellWidth = (gridWidth - ((columnCount + 1) * guideLine)) / columnCount;
        val cellHeight = (gridHeight - ((rowCount + 1) * guideLine)) / rowCount;
        val bgColour = Colors["212339"];

        //val font = resourcesVfs["Roboto_font2bitmap.png"].readBitmapFont();

        val font = TtfFont(resourcesVfs["Roboto-Regular.ttf"].readAll()).toBitmapFont(20.0);

        println(font);
        fixedSizeContainer(stage.width, topIndent) {
            x = 0.0;
            y = 0.0;
            roundRect(stage.width, topIndent, 4.0, fill = bgColour) {
                x = 0.0;
                y = 0.0;
            };
            roundRect(cellWidth * 2 + guideLine, topIndent - (guideLine * 1), 5.0, fill = Colors["#82c9b9"]) {
                x = guideLine;
                y = guideLine;
                text("2048", cellHeight * 0.5, bgColour, font).centerOn(this);
            }
            for (i in 0..1) {
                roundRect(cellWidth * 2 + guideLine, (topIndent / 2) - guideLine, 5.0, fill = Colors["#82c9b9"]) {
                    x = guideLine + ((guideLine + cellWidth) * 2);
                    y = guideLine + ((topIndent / 2) * i);
                    if (i == 0) {
                        text("BEST: 0", cellHeight * 0.5, bgColour, font).centerOn(this)
                    } else {
                        text("CURRENT: 0", (topIndent / 2) * 0.5, bgColour, font).centerOn(this)
                    }
                }
            }
        }


        fixedSizeContainer(gridWidth, gridHeight) {
            x = 0.0;
            y = topIndent;
            roundRect(gridWidth, gridHeight, 4.0, fill = Colors["#212339"]) {
                x = 0.0;
                y = 0.0;
            }
            for (i in 0..3) {
                for (j in 0..3) {
                    roundRect(cellWidth, cellHeight, 5.0, fill = Colors["#82c9b9"]) {
                        x = guideLine + (guideLine + cellWidth) * i;
                        y = guideLine + (guideLine + cellHeight) * j;
                    }
                }
            }
        }

	}
}
