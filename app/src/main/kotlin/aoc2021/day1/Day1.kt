package Day1

import SolutionInterface.SolutionIf
import Reader.Reader

class Day1 : SolutionIf
{
    public constructor()
    {
        val reader = Reader("day1.txt");
        this.inputLines = reader.readInput();
    }

    override fun solveFirst() : String
    {
        var counter : Int = 0;
        for(i in 1..(this.inputLines.size-1))
            if(this.inputLines[i].toInt() > this.inputLines[i-1].toInt())
                counter++;

        return counter.toString();
    }

    override fun solveSecond() : String
    {
        val depths : List<Int> = this.inputLines.map { it -> it.toInt() };
        var counter : Int = 0;
        for(i in 0..(depths.size-4))
            if(depths.subList(i, i+3).sum() < depths.subList(i+1, i+4).sum())
                counter++;

        return counter.toString();
    }

    public fun solveFirstVar1() : String
    {
        val depths : List<Int> = this.inputLines.map { it -> it.toInt() };
        val solution : Int = depths.windowed(size = 2).count{ (a,b) -> a < b};

        return solution.toString();
    }

    public fun solveSecondVar1() : String
    {
        val depths : List<Int> = this.inputLines.map { it -> it.toInt() };
        val solution : Int = depths.windowed(size = 3).map{ it -> it.sum()}
                .windowed(size = 2).count{ (a,b) -> a < b}

        return solution.toString();
    }

    private var inputLines : List<String> = listOf<String>();
}