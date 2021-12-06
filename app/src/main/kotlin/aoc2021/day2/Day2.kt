package Day2

import SolutionInterface.SolutionIf
import Reader.Reader

class Day2 : SolutionIf
{
    public constructor()
    {
        val reader = Reader("day2.txt");
        this.inputLines = reader.readInput();
    }

    override fun solveFirst() : String
    {
        val forwardList : List<String> = this.inputLines.filter(
                                    { it -> it.split(" ")[0] == "forward" });
        val upList : List<String> = this.inputLines.filter(
                                    {it -> it.split(" ")[0] == "up"});
        val downList : List<String> = this.inputLines.filter(
                                    { it -> it.split(" ")[0] == "down" });

        val horizontal : Int = forwardList.map(
                                {it -> it.split(" ")[1].toInt()}).sum();
        val depth : Int = downList.map({it.split(" ")[1].toInt()}).sum() 
                          - upList.map({it->it.split(" ")[1].toInt()}).sum();

        return (horizontal*depth).toString();
    }

    override fun solveSecond() : String
    {
        var aim : Int = 0;
        var horizontal : Int = 0;
        var depth : Int = 0;

        val commandMap = mapOf(
        "forward" to 
        {
            value : Int -> 
            horizontal += value; 
            depth += aim * value
        },
        "up" to 
        {
            value : Int -> 
            aim -= value;
        },
        "down" to 
        {
            value : Int -> 
            aim += value;
        }); 

        this.inputLines.forEach{it -> 
            commandMap[it.split(" ")[0]]?.invoke(it.split(" ")[1].toInt())};
        
        return (horizontal*depth).toString();
    }

    private var inputLines : List<String> = listOf<String>();
}