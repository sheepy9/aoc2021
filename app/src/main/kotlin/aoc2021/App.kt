/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package aoc2021

import Day1.Day1
import Day2.Day2
import Day3.Day3
import Day4.Day4
import Day5.Day5
import Day6.Day6
import java.io.File
import SolutionInterface.SolutionIf

fun main() 
{
    val day1Solution : Day1 = Day1();
    println("Day1 first solution: " + day1Solution.solveFirst());
    println("Day1 second solution: " + day1Solution.solveSecond());
    println("Day1 first solution (B): " + day1Solution.solveFirstVar1());
    println("Day1 second solution (B): " + day1Solution.solveSecondVar1());

    val day2Solution : Day2 = Day2();
    println("Day 2 first solution: " + day2Solution.solveFirst());
    println("Day 2 second solution: " + day2Solution.solveSecond());

    val day3Solution : Day3 = Day3();
    println("Day 3 first solution: " + day3Solution.solveFirst());
    println("Day 3 second solution: " + day3Solution.solveSecond());

    val day4Solution : Day4 = Day4();
    println("Day 4 first solution: " + day4Solution.solveFirst());
    println("Day 4 second solution: " + day4Solution.solveSecond());

    val day5Solution : Day5 = Day5();
    println("Day 5 first solution: " + day5Solution.solveFirst());
    println("Day 5 second solution: " + day5Solution.solveSecond());

    val day6Solution : Day6 = Day6();
    println("Day 6 first solution: " + day6Solution.solveFirst());
    println("Day 6 second solution: " + day6Solution.solveSecond());
}
