fun main() {
    fun part1(input: List<String>): Int {
        val (times, distances) = input.map { line ->
            line.replace("\\s+".toRegex(), " ").split(" ").drop(1).map { it.toInt() }
        }

        return (times.indices.map {
            var chargeTime = 1
            while (chargeTime * (times[it] - chargeTime) < distances[it]) chargeTime++
            times[it] + 1 - 2 * chargeTime
        }.reduce { acc, x -> acc * x })
    }

    fun part2(input: List<String>): Long {
        val (time, distance) = input.map { line ->
            line.replace("\\s+".toRegex(), " ").split(" ").drop(1).joinToString("").toLong()
        }
        var chargeTime = 1
        while (chargeTime * (time - chargeTime) < distance) chargeTime++
        return (time + 1 - 2 * chargeTime)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288)
    check(part2(testInput) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}