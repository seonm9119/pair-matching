package pairmatching
import camp.nextstep.edu.missionutils.Console.readLine
import java.nio.file.Files
import java.nio.file.Paths



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

fun areAllSublistDifferent(a: List<List<String>>, b: MutableList<List<String>>): Boolean {
    return b.none { bSubset -> a.any { it.sorted() == bSubset.sorted()  } }

}


