package Day6

import SolutionInterface.SolutionIf
import Reader.Reader

class Day6 : SolutionIf
{
    public constructor()
    {
        val reader = Reader("day6.txt");
        this.inputLines = reader.readInput();
    }

    override fun solveFirst() : String
    {
        return this.simaluteDays(80).toString();
    }

    override fun solveSecond() : String
    {
        return (this.simaluteDays(256)).toString()

    }

    private fun simaluteDays(n : Int) : Long
    {
        fun LongArray.rotate(n: Int) = 
            let { sliceArray(n until size) + sliceArray(0 until n) }

        val inputList = this.inputLines[0].toString()
            .split(",")
            .map({it -> it.toInt()});

        var fishGroups = longArrayOf(inputList.count({ it -> it == 0 }).toLong(),
                                     inputList.count({ it -> it == 1 }).toLong(),
                                     inputList.count({ it -> it == 2 }).toLong(),
                                     inputList.count({ it -> it == 3 }).toLong(),
                                     inputList.count({ it -> it == 4 }).toLong(),
                                     inputList.count({ it -> it == 5 }).toLong(),
                                     inputList.count({ it -> it == 6 }).toLong());
        var fishGroupsJr = longArrayOf(0,0);

        for (i in 1..n)
        {
            val oldestJr = fishGroupsJr[0];
            val oldest = fishGroups[0];
            fishGroupsJr[0] = fishGroupsJr[1];
            fishGroups = fishGroups.rotate(1);
            fishGroups[6] = fishGroups[6] + oldestJr;
            fishGroupsJr[1] = oldest;
        }
         
        return fishGroups.sum() + fishGroupsJr.sum();
    }


    private var inputLines : List<String> = listOf<String>();
}
