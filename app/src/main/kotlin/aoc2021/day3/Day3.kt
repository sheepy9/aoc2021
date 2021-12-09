package Day3

import SolutionInterface.SolutionIf
import Reader.Reader
import kotlin.math.roundToInt

class Day3 : SolutionIf
{
    public constructor()
    {
        val reader = Reader("day3.txt");
        this.inputLines = reader.readInput();
    }

    override fun solveFirst() : String
    {
        val cols : Int = this.inputLines[0].length;
        val rows : Int = this.inputLines.size;
        var sums : List<Int> = List<Int>(cols){0};
        
        this.inputLines.forEach(
        { 
            it -> 
            val numList = it.toList().map({chr -> chr.toString().toInt()});
            sums = numList.zip(sums){a,b -> a+b};
        });

        val power = sums.map(
        {
            it -> 
            if(it > rows/2) "1" 
            else "0"
        }).joinToString("").toInt(radix = 2);

        val eps = sums.map(
        {
            it -> 
            if(it < rows/2) "1" 
            else "0" 
        }).joinToString("").toInt(radix = 2);

        return (power*eps).toString();
    }

    override fun solveSecond() : String
    {
        var matrixMore = MutableList<MutableList<Int>>(0){MutableList<Int>(0){0}};
        var matrixFewer = MutableList<MutableList<Int>>(0){MutableList<Int>(0){0}};
        var rowsM : Int = this.inputLines.size;
        var rowsF : Int = this.inputLines.size;
        val cols : Int = this.inputLines[0].length;

        this.inputLines.forEach(
        { 
            it -> 
            matrixMore.add(
            it.toList().map({chr -> chr.toString().toInt()}).toMutableList());
            matrixFewer.add(
            it.toList().map({chr -> chr.toString().toInt()}).toMutableList());
        });

        for(i in 0..cols-1)
        {
            val onesM : Int = matrixMore.count({it -> it[i] == 1});
            val onesF : Int = matrixFewer.count({it -> it[i] == 1});

            if(rowsM > 1)
            {
                if(onesM >= rowsM - onesM) matrixMore.removeAll({it-> it[i] == 0});
                else matrixMore.removeAll({it-> it[i] == 1});
            }

            if(rowsF > 1)
            {
                if(onesF < rowsF - onesF) matrixFewer.removeAll({it-> it[i] == 0});
                else matrixFewer.removeAll({it-> it[i] == 1});
            }

            rowsM = matrixMore.size;
            rowsF = matrixFewer.size;
        }

        val oxygen = matrixMore.flatten().joinToString("").toString().toInt(2);
        val co2 = matrixFewer.flatten().joinToString("").toString().toInt(2);

        return (oxygen*co2).toString();
    }

    private var inputLines : List<String> = listOf<String>();
}