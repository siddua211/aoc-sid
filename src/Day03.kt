fun main() {

    fun part1(input: List<String>): Int {
        var res = 0
        for (i in input.indices) {
            var j = 0
            while (j < input[i].length) {
                if (!input[i][j].isDigit()) {
                    j += 1
                    continue
                }
                var open = j
                var num = ""
                while (open < input[i].length && input[i][open].isDigit()) {
                    num += input[i][open++]
                }
                var valid = false
                for(index in maxOf(0, j-1) until minOf(input[i].length, open+1)) {
                    if (i-1 >= 0 && !input[i-1][index].isDigit() && input[i-1][index] != '.') {
                        valid = true
                    }
                    if (i+1 < input.size && !input[i+1][index].isDigit() && input[i+1][index] != '.') {
                        valid = true
                    }
                    if (!input[i][index].isDigit() && input[i][index] != '.') {
                        valid = true
                    }
                }
                if (valid) res += num.toInt()
                j = open
            }
        }
        return res
    }

    fun part2(input: List<String>): Int {
        var res = 0
        val map = mutableMapOf<Pair<Int, Int>, ArrayList<String>>()
        for (i in input.indices) {
            var j = 0
            while (j < input[i].length) {
                if (!input[i][j].isDigit()) {
                    j += 1
                    continue
                }
                var open = j
                var num = ""
                while (open < input[i].length && input[i][open].isDigit()) {
                    num += input[i][open++]
                }
                for(index in maxOf(0, j-1) until minOf(input[i].length, open+1)) {
                    if (i-1 >= 0 && input[i-1][index] == '*') {
                        if (!map.contains(Pair(i-1, index))) {
                            map[Pair(i-1, index)] = arrayListOf(num)
                        } else {
                            map[Pair(i-1, index)]?.add(num)
                        }
                    }
                    if (i+1 < input.size && input[i+1][index] == '*') {
                        if (!map.contains(Pair(i+1, index))) {
                            map[Pair(i+1, index)] = arrayListOf(num)
                        } else {
                            map[Pair(i+1, index)]?.add(num)
                        }
                    }
                    if (input[i][index] == '*') {
                        if (!map.contains(Pair(i, index))) {
                            map[Pair(i, index)] = arrayListOf(num)
                        } else {
                            map[Pair(i, index)]?.add(num)
                        }
                    }
                }
                j = open
            }
        }
        map.forEach { (key, value) ->
            if (value.size == 2) {
                key.println()
                res += value[0].toInt() * value[1].toInt()
            }
        }
        return res
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part2(testInput) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}