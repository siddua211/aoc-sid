fun main() {

    fun validateGame(line: String): Int {
        val split = line.split(" ")
        for (index in split.indices) {
            if (split[index].contains("blue") && split[index - 1].toInt() > 14) {
                return 0
            }
            if (split[index].contains("green") && split[index - 1].toInt() > 13) {
                return 0
            }
            if (split[index].contains("red") && split[index - 1].toInt() > 12) {
                return 0
            }
        }
        return split[1].dropLast(1).toInt()
    }

    fun part1(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            sum += validateGame(line)
        }
        return sum
    }

    fun validateGamePower(line: String): Int {
        var minR = 0
        var minG = 0
        var minB = 0
        val split = line.split(" ")
        for (index in split.indices) {
            if (split[index].contains("blue") && split[index - 1].toInt() > minB) {
                minB = split[index - 1].toInt()
            }
            if (split[index].contains("green") && split[index - 1].toInt() > minG) {
                minG = split[index - 1].toInt()
            }
            if (split[index].contains("red") && split[index - 1].toInt() > minR) {
                minR = split[index - 1].toInt()
            }
        }
        return minR * minG * minB
    }

    fun part2(input: List<String>): Int {
        var sum = 0
        for (line in input) {
            sum += validateGamePower(line)
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

}
