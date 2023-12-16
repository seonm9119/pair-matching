package pairmatching
import camp.nextstep.edu.missionutils.Console.readLine

fun main() {

    val inputHandler = InputHandler()
    val pairMatching = PairMatching(inputHandler)

    while (true){

        val inputs = inputHandler.readFeature()

        when(inputs) {
            "1" -> pairMatching.matching()
            "2" -> pairMatching.search()
            "3" -> pairMatching.initiate()
            "Q" -> break
        }
    }



}
