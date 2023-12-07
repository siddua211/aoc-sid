fun main() {

    fun checkSumDigits(line: String): Int {
        var ret = ""

        for (char in line) {
            if (char.isDigit()) {
                ret += char
                break
            }
        }
        for (char in line.reversed()) {
            if (char.isDigit()) {
                ret += char
                break
            }
        }
        return ret.toInt()
    }

    fun part1(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            ret += checkSumDigits(line)
        }
        return ret
    }

    fun checkSumPartTwo(line: String): Int {
        var ret = ""
        val words = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        var i = 0
        while (i < line.length) {
            if (line[i].isDigit()) {
                ret += line[i]
            }
            for (index in words.indices) {
                if ((i + words[index].length <= line.length) && (line.substring(i, i + words[index].length) == words[index])) {
                    ret += (index + 1).toString()
                    break
                }
            }
            i += 1
        }
        val firstChar = ret.first().toString()
        val lastChar = ret.last().toString()
        return "$firstChar$lastChar".toInt()
    }

    fun part2(input: List<String>): Int {
        var ret = 0
        for (line in input) {
            ret += checkSumPartTwo(line)
        }
        return ret
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 142)
    check(part2(testInput) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
