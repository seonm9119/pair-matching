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

    private fun checkYesOrNo(): String{
        val inputs = readLine()
        require (inputs in listOf("네", "아니오")) {"[ERROR] 잘못된 입력입니다. 다시 입력하십시오."}
        return inputs
    }

    private fun checkFeature(): String{
        val inputs = readLine()
        require (inputs in listOf("1", "2", "3", "Q")) {"[ERROR] 잘못된 입력입니다. 다시 입력하십시오."}
        return inputs
    }

    fun readInputs(): List<String>{

        while (true){
            try {
                return checkInputs()
            } catch (e: NumberFormatException) {
                println("[ERROR] 유효하지 않은 형식입니다. 다시 입력하십시오.")
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun readYesOrNo(): String{

        while (true){
            try {
                return checkYesOrNo()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun readFeature(): String{

        print("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료\n")
        while (true){
            try {
                return checkFeature()
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }




}