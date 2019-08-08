package misc

import java.io.FileReader

fun extractData(
    fileName: String = "src/main/resources/annual_csv.csv",
    category: String = "GISTEMP"
) = FileReader(fileName).useLines { lines ->
    // Sequence<String>
    lines.drop(3)                                // skip header lines
        .map { line -> line.split(",") } // Sequence<String[]>
        .filter { it[0] == category }
        .sortedBy { it[1] }                        // sort by year
        .map { it[1].toInt() to it[2].toDouble() } // Sequence<Pair<Int,Double>>
        .toMap()
}

fun getChunkedAverages(data: Map<Int, Double>, size: Int) =
    data.entries
        .chunked(size)
        .map { list ->
            "%.2f".format(list.map { it.key }.average()) to
                    "%.2f".format(list.map { it.value }.average())
        }


fun getWindowedAverages(data: Map<Int, Double>, size: Int, step: Int = 1) =
    data.entries
        .windowed(size, step, true)
        .map { list ->
            "%.1f".format(list.map { it.key }.average()) to
                    "%.2f".format(list.map { it.value }.average())
        }


fun main() {
    getChunkedAverages(extractData(), 9).forEach { println(it) }
    getWindowedAverages(extractData(), 9, 5).forEach { println(it) }
}
