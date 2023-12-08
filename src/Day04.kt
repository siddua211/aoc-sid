import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        var res = 0
        for (line in input) {
            val split = line.split(" ")
            var power = -1
            var j = 0
            val set = HashSet<String>()
            while (split[j] != "|") {
                if (split[j] != "") set.add(split[j])
                j++
            }
            j++
            while (j < split.size) {
                if (set.contains(split[j])) {
                    power++
                }
                j++
            }
            if (power != -1) res += (2.0.pow(power.toDouble())).toInt()
        }
        return res
    }

    fun part2(input: List<String>): Int {
        val dp = ArrayList<Int>(input.size).apply {
            for (i in input.indices) {
                add(1)
            }
        }
        for (i in input.indices) {
            var numW = 0
            val split = input[i].split(" ")
            var j = 0
            val set = HashSet<String>()
            while (split[j] != "|") {
                if (split[j] != "") set.add(split[j])
                j++
            }
            j++
            while (j < split.size) {
                if (set.contains(split[j])) {
                    numW++
                }
                j++
            }
            for (d in 0 until numW) {
                dp[i + d + 1] += dp[i]
            }
        }
        return dp.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}