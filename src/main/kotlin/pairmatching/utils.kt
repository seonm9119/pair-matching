package pairmatching
import camp.nextstep.edu.missionutils.Console.readLine
import java.nio.file.Files
import java.nio.file.Paths
import camp.nextstep.edu.missionutils.Randoms

data class MatchingInformation(
    val course: String,
    val level: String,
    val mission: String,
    val groupedPairs: MutableList<List<String>>
)

enum class Level(val levelName: String, val missions: List<String>) {
    LEVEL1("레벨1", listOf("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", listOf("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", emptyList()),
    LEVEL4("레벨4", listOf("성능개선", "배포")),
    LEVEL5("레벨5", emptyList())
}


val matchingData = mutableMapOf<String, MatchingInformation>()
val inputHandler = InputHandler()
fun readMarkdownFile(filePath: String): List<String> {
    val lines = mutableListOf<String>()

    try {
        Files.lines(Paths.get(filePath)).use { fileLines ->
            lines.addAll(fileLines.toList())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return lines
}

fun convertKey(inputs: List<String>): String = "${inputs[0]}&${inputs[1]}&${inputs[2]}"

fun validateMatching(inputs: List<String>): Boolean = matchingData.containsKey(convertKey(inputs))

fun areAllSublistDifferent(a: List<List<String>>, b: MutableList<List<String>>): Boolean {
    return b.none { bSubset -> a.any { it.sorted() == bSubset.sorted()  } }

}


fun runMatching(inputs: List<String>){

    val (course, level, mission) = inputs
    val key = convertKey(inputs)


    val filepath = when (course){
        "백엔드" -> "src/main/kotlin/resources/backend-crew.md"
        "프론트엔드" -> "src/main/kotlin/resources/frontend-crew.md"
        else -> ""
    }

    val crewNames = readMarkdownFile(filepath)


    for (i in 0 until 3) {

        val shuffledCrew = Randoms.shuffle(crewNames)
        val groupedPairs = shuffledCrew.chunked(2).toMutableList()
        if (shuffledCrew.size % 2 != 0) {
            groupedPairs[groupedPairs.size - 2] = groupedPairs[groupedPairs.size - 2] + groupedPairs.last()
            groupedPairs.removeAt(groupedPairs.lastIndex)
        }

        var checkCount = 0
        var missionSize = 0
        enumValues<Level>().forEach { levels ->
            if (levels.levelName == level) {
                missionSize = levels.missions.size
                levels.missions.forEach { mission ->
                    val checkKey = "${course}&${level}&${mission}"
                    val result = areAllSublistDifferent(groupedPairs, matchingData[checkKey]?.groupedPairs ?: mutableListOf())
                    if (result) checkCount +=1
                }
            }
        }

        if (checkCount == missionSize){
            println("\n페어 매칭 결과입니다.")
            matchingData.put(key, MatchingInformation(course, level, mission, groupedPairs))
            matchingData[key]?.groupedPairs?.forEach { pair ->
                println(pair.joinToString(" : "))}
            println()
            break
        } else if(i==2){
            println("[ERROR] 경우의 수가 없습니다")
        }

    }

}
fun reMatching(inputs: List<String>){

    print("\n매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n" +
            "네 | 아니오\n")

    when (readLine()){
        "아니오" -> {
            print("\n과정, 레벨, 미션을 선택하세요.\n" +
                    "ex) 백엔드, 레벨1, 자동차경주\n")
            val reInputs = inputHandler.readInputs()

            if (validateMatching(reInputs)){
                reMatching(reInputs)
            } else runMatching(inputs)
        }
        "네" -> runMatching(inputs)

    }
}



fun matching(){

    print("\n#############################################\n" +
            "과정: 백엔드 | 프론트엔드\n" +
            "미션:\n" +
            "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
            "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
            "  - 레벨3: \n" +
            "  - 레벨4: 성능개선 | 배포\n" +
            "  - 레벨5: \n" +
            "############################################\n" +
            "과정, 레벨, 미션을 선택하세요.\n" +
            "ex) 백엔드, 레벨1, 자동차경주\n")


    val inputs = inputHandler.readInputs()

    if (validateMatching(inputs)){
        reMatching(inputs)
    } else runMatching(inputs)


}

fun search(){

    print("\n#############################################\n" +
            "과정: 백엔드 | 프론트엔드\n" +
            "미션:\n" +
            "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n" +
            "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n" +
            "  - 레벨3: \n" +
            "  - 레벨4: 성능개선 | 배포\n" +
            "  - 레벨5: \n" +
            "############################################\n" +
            "과정, 레벨, 미션을 선택하세요.\n" +
            "ex) 백엔드, 레벨1, 자동차경주\n")

    val inputs = readLine().split(',').map{ it.trim()}

    if (validateMatching(inputs)) {
        println("\n페어 매칭 결과입니다.")
        matchingData[convertKey(inputs)]?.groupedPairs?.forEach { pair ->
            println(pair.joinToString(" : "))}
        println()
    }
    else println("[ERROR] 매칭 이력이 없습니다.")


}

fun init(){
    matchingData.clear()
    print("\n초기화 되었습니다.\n\n")
}