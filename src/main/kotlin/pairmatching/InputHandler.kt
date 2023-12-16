package pairmatching
import camp.nextstep.edu.missionutils.Console.readLine

class InputHandler {

    private fun checkInputs(): List<String>{
        val inputs = readLine().split(',').map{ it.trim()}
        val (course, level, mission) = inputs

        require (course in listOf("백엔드", "프론트엔드")) {"[ERROR] 없는 과정입니다."}
        require (Level.entries.find { it.levelName == level } != null) {"[ERROR] 없는 레벨입니다."}
        require (mission in Level.entries.find { it.levelName == level }!!.missions) {"[ERROR] 없는 미션입니다."}

        return inputs
    }

    fun readInputs(): List<String>{

        while (true){
            try {
                return checkInputs()
            } catch (e: NumberFormatException) {
                println("[ERROR] 유효하지 않은 형식입니다")
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }


}