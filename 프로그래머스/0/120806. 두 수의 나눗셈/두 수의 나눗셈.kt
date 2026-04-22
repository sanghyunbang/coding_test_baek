class Solution {
    fun solution(num1: Int, num2: Int): Int {
        val result = num1.toDouble() / num2 * 1000
        return result.toInt()
    }
}