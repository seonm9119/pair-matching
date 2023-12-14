package pairmatching
import camp.nextstep.edu.missionutils.Console.readLine

fun main() {

    while (true){

        print("기능을 선택하세요.\n" +
                "1. 페어 매칭\n" +
                "2. 페어 조회\n" +
                "3. 페어 초기화\n" +
                "Q. 종료\n")

        val inputs = readLine()

        when(inputs) {
            "1" -> matching()
            "2" -> search()
            "3" -> init()
            "Q" -> break
            else -> throw IllegalArgumentException("[ERROR] 잘못된 값을 입력하였습니다.")

        }
    }



}
