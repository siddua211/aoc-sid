import java.math.BigInteger

fun main() {
    fun createRanges(input: List<String>): ArrayList<ArrayList<Triple<BigInteger, BigInteger, BigInteger>>> {
        var index = 2
        val rangeList = ArrayList<ArrayList<Triple<BigInteger, BigInteger, BigInteger>>>()
        while (index < input.size) {
            if (input[index].contains("map:")) {
                index++
                val list = ArrayList<Triple<BigInteger, BigInteger, BigInteger>>()
                while (index < input.size && input[index].isNotBlank()) {
                    val temp = input[index].split(" ")
                    list.add(Triple(temp[0].toBigInteger(), temp[1].toBigInteger(), temp[1].toBigInteger() + temp[2].toBigInteger() - 1.toBigInteger()))
                    index++
                }
                rangeList.add(list)
            } else {
                index ++
            }
        }
        return rangeList
    }

    fun getOutput(input: ArrayList<ArrayList<Triple<BigInteger, BigInteger, BigInteger>>>, seed: BigInteger): BigInteger {
        var curVal = seed
        for (map in input) {
            for (j in map.indices) {
                if (curVal in map[j].second..map[j].third) {
                    curVal = curVal + map[j].first - map[j].second
                    break
                }
            }
        }
        return curVal
    }

    fun part1(input: List<String>): BigInteger {
        val seeds = input[0].split(" ")
        seeds.removeFirst()
        val ranges = createRanges(input)
        var min = Int.MAX_VALUE.toBigInteger()
        for (seed in seeds) {
            min = minOf(min, getOutput(ranges, seed.toBigInteger()))
        }

        return min
    }

    fun getInput(input: ArrayList<ArrayList<Triple<BigInteger, BigInteger, BigInteger>>>, output: BigInteger): BigInteger {
        var curVal = output
        for (i in input.size - 1 downTo 0) {
            for (j in input[i].indices) {
                if (curVal in input[i][j].first .. input[i][j].third - input[i][j].second + input[i][j].first) {
                    curVal = curVal + input[i][j].second - input[i][j].first
                    break
                }
            }
        }
        return curVal
    }

    fun part2(input: List<String>): BigInteger {
        val seeds = input[0].split(" ")
        seeds.removeFirst()
        var output = BigInteger("0")
        val ranges = createRanges(input)
        while (true) {
            val end = getInput(ranges, output)
            for (index in seeds.indices step 2) {
                if (end in seeds[index].toBigInteger() .. (seeds[index + 1].toBigInteger()) + seeds[index].toBigInteger() - 1.toBigInteger()) {
                    return output
                }
            }
            output++
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35.toBigInteger())
    check(part2(testInput) == 46.toBigInteger())

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}