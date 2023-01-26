import com.soywiz.klock.*
import com.soywiz.korge.*
import com.soywiz.korge.scene.*
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korge.view.roundRect
import com.soywiz.korim.color.*
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*
import com.soywiz.korma.geom.vector.*
import com.soywiz.korma.interpolation.*




suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {
	val sceneContainer = sceneContainer();


    //bgField.x = leftIndent;
    //bgField.y = topIndent;

    //addChild(bgField);

	sceneContainer.changeTo({ MyScene() })
    //Test
}

class MyScene : Scene() {

	override suspend fun SContainer.sceneMain() {
		val minDegrees = (-16).degrees
		val maxDegrees = (+16).degrees
        val stage = stage!!

        //val cellSize = views.virtualWidth / 5.0;

        //println("CellSize  $cellSize");
        println("Virtual Width  ${views.virtualWidth}");

        //What? I have no idea what this is for...
        //vl fieldSize = 50.0 * 4 * cellSize;a
        //val leftIndent = (views.virtualWidth - fieldSize) / 2;

        //Works
        val topIndent = 150.0
        val guideLine = 10.0;

        val columnCount = 4;
        val rowCount = 4;

        val gridHeight = stage.height - topIndent;
        val gridWidth = stage.width;

        val cellWidth = (gridWidth - ((columnCount + 1) * guideLine)) / columnCount;
        val cellHeight = (gridHeight - ((rowCount + 1) * guideLine)) / rowCount;




        //Doesn't work
        //val topIndent: Double = 150;
        //New languages are fucking insane.

        /*val bgField = roundRect(fieldSize, fieldSize, 5.0, fill = Colors["#b9aea0"]) {
            x = leftIndent;
            y = topIndent;
        };*/
        fixedSizeContainer(stage.width, stage.height) {
            x = 0.0;
            y = topIndent;
            for (i in 0..3) {
                for (j in 0..3) {
                    roundRect(cellWidth, cellHeight, 5.0, fill = Colors["#b9aea0"]) {
                        x = guideLine + (guideLine + cellWidth) * i;
                        y = guideLine + (guideLine + cellHeight) * j;
                    }
                }
            }
        }

        //val graphicPos = position(leftIndent, topIndent);

        /*graphics {
            it.position(leftIndent, topIndent)
            fill(Colors["#cec0b2"]) {
                for (i in 0..3) {
                    for (j in 0..3) {
                        roundRect(10.0 + (10 + cellSize) * i, 10.0 + (10 + cellSize) * j, cellSize, cellSize, 5.0)
                    }
                }
            }
        }*/

        //graphics.addTo(this);

		/* val image = image(resourcesVfs["korge.png"].readBitmap()) {
			rotation = maxDegrees
			anchor(.5, .5)
			scale(0.8)
			position(256, 256)
		}

		while (true) {
			image.tween(image::rotation[minDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
			image.tween(image::rotation[maxDegrees], time = 1.seconds, easing = Easing.EASE_IN_OUT)
		} */
	}
}
