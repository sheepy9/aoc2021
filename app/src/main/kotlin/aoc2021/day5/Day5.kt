package Day5

import SolutionInterface.SolutionIf
import Reader.Reader

class Day5 : SolutionIf
{
    public constructor()
    {

        val reader = Reader("day5.txt");
        this.inputLines = reader.readInput();
    }
    
    override fun solveFirst() : String
    {
        this.lineList = inputLines.map({
            row -> row.split(" -> ").map({
                pair -> pair.split(",").map({
                    num -> num.toInt()
                })
            })
        });

        val maxVal = this.lineList.flatten().flatten().maxOrNull()!!;

        // straightLines[i][j][k] represents the i-th line, j=0 for the origin
        // point, j=1 for the destination point, k=0 for x component, k=1 for the
        // y component
        var straightLines : List<List<List<Int>>> = this.lineList.filter({
            it-> ((it[0][0] == it[1][0]) or (it[0][1] == it[1][1]))
        })

        var positionMatrix : List<MutableList<Int>> = 
            List<MutableList<Int>>(maxVal+1){MutableList<Int>(maxVal+1){0}};


        straightLines.forEach({
            line->
            for(j in line[0][0] toward line[1][0])
                for(k in line[0][1] toward line[1][1])
                    positionMatrix[k][j]++;

        });

        return positionMatrix.flatten().count(){it -> it >= 2}.toString();
    }

    override fun solveSecond() : String
    {
        this.lineList = inputLines.map({
            row -> row.split(" -> ").map({
                pair -> pair.split(",").map({
                    num -> num.toInt()
                })
            })
        });

        val maxVal = this.lineList.flatten().flatten().maxOrNull()!!;

        // straightLines[i][j][k] represents the i-th line, j=0 for the origin
        // point, j=1 for the destination point, k=0 for x component, k=1 for the
        // y component
        var straightLines : List<List<List<Int>>> = this.lineList.filter({
            it-> ((it[0][0] == it[1][0]) or (it[0][1] == it[1][1]))
        })

        var diagonalLines : List<List<List<Int>>> = this.lineList.filter({
            it-> ((it[0][0] != it[1][0]) and (it[0][1] != it[1][1]))
        })

        var positionMatrix : List<MutableList<Int>> = 
            List<MutableList<Int>>(maxVal+1){MutableList<Int>(maxVal+1){0}};


        straightLines.forEach({
            line->
            for(j in line[0][0] toward line[1][0])
                for(k in line[0][1] toward line[1][1])
                    positionMatrix[k][j]++;

        });

        diagonalLines.forEach({
            line->
            var xCoords = (line[0][0] toward line[1][0]).toList();
            var yCoords = (line[0][1] toward line[1][1]).toList();
            var cnt = 0;
            while(cnt < xCoords.size)
            {
                positionMatrix[yCoords[cnt]][xCoords[cnt]]++;
                cnt++;
            }

        });

        return positionMatrix.flatten().count(){it -> it >= 2}.toString();
    }

    private infix fun Int.toward(to: Int): IntProgression 
    {
        val step = if (this > to) -1 else 1;
        return IntProgression.fromClosedRange(this, to, step);
    }

    private var inputLines : List<String> = listOf<String>();
    private var lineList : List<List<List<Int>>> = listOf<List<List<Int>>>();
}