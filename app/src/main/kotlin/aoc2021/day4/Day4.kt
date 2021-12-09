package Day4

import SolutionInterface.SolutionIf
import Reader.Reader

class Day4 : SolutionIf
{
    public constructor()
    {
        val reader = Reader("day4.txt");
        this.inputLines = reader.readInput();
        this.rawInput = reader.readRawInput();
        this.bingoNumbers = inputLines.first().split(",").map({
            it-> it.toInt()
        });
        var tabsList  = this.rawInput.split("\n\n").filterIndexed({
            idx, x -> idx > 0 });
        var tabs = tabsList.map({it -> it.split("\n")});

        // tables[i][j][k] is the i-th table, j-th row of that table 
        // and k-th number of that row
        this.tables = tabs.map({
            it -> it.map({
            row-> row.chunked(3){
            num -> num.toString().replace(" ","").toInt()
        }})}).toMutableList();
    }
    
    override fun solveFirst() : String
    {
        tTables = tables.map({it-> this.transpose(it)});

        var winningTable : List<List<Int>> = listOf(listOf<Int>());
        var winningNumber : Int = 0;
        var revealedNumbers : MutableSet<Int> = mutableSetOf<Int>(0);
        
        for(i in 5..this.bingoNumbers.size)
        {
            var gameOver = false;
            revealedNumbers.addAll(this.bingoNumbers.take(i));

            run tableChecker@
            {
                this.tables.forEach({
                    table -> 
                    table.forEach({
                        row ->  
                        if(revealedNumbers.containsAll(row))
                        {
                            winningTable = table;
                            winningNumber = this.bingoNumbers[i-1];
                            gameOver = true;
                            return@tableChecker
                        }
                    })
                });
            };

            run tableChecker@
            {
                this.tTables.forEach({
                    table -> 
                    table.forEach({
                        row ->  
                        if(revealedNumbers.containsAll(row))
                        {
                            winningTable = table;
                            winningNumber = this.bingoNumbers[i-1];
                            gameOver = true;
                            return@tableChecker
                        }
                    })
                });
            };

            if(gameOver)
                break;
        }
    
        val summingTable = winningTable.flatten().filter({it -> !revealedNumbers.contains(it);})
        return (summingTable.sum()*winningNumber).toString()
    }

    override fun solveSecond() : String
    {
        tTables = tables.map({it-> this.transpose(it)});

        var winningTable : List<List<Int>> = listOf(listOf<Int>());
        var winningNumber : Int = 0;
        var revealedNumbers : MutableSet<Int> = mutableSetOf<Int>(0);
        var winningRevealedNumbers : MutableSet<Int> = mutableSetOf<Int>(0);
        var winningTables : MutableList<Int> = mutableListOf<Int>(0);
        
        for(i in 5..this.bingoNumbers.size)
        {
            revealedNumbers.addAll(this.bingoNumbers.take(i));

            this.tables.forEach({
                table -> 
                table.forEach({
                    row ->  
                    if(revealedNumbers.containsAll(row))
                    {
                        if(!winningTables.contains(this.tables.indexOf(table)))
                        {
                            winningTables.add(this.tables.indexOf(table));
                            winningTable = table;
                            winningNumber = this.bingoNumbers[i-1];
                            winningRevealedNumbers = revealedNumbers.toMutableSet();
                        }
                        return@forEach
                    }
                })
            });

            this.tTables.forEach({
                table -> 
                table.forEach({
                    row ->  
                    if(revealedNumbers.containsAll(row))
                    {
                        if(!winningTables.contains(this.tTables.indexOf(table)))
                        {
                            winningTables.add(this.tTables.indexOf(table));
                            winningTable = table;
                            winningNumber = this.bingoNumbers[i-1];
                            winningRevealedNumbers = revealedNumbers.toMutableSet();
                        }
                        return@forEach
                    }
                })
            });
        }
    
        val summingTable = winningTable.flatten().filter({it -> !winningRevealedNumbers.contains(it);})
        return (summingTable.sum()*winningNumber).toString()
    }

    private fun transpose(matrix : List<List<Int>>) : List<List<Int>>
    {
        val rows = matrix.size;
        val cols = matrix.first().size;
        var tMatrix = MutableList<MutableList<Int>>(cols){MutableList<Int>(rows){0}};

        for(i in 0..rows-1)
            for(j in 0..cols-1)
                tMatrix[j][i] = matrix[i][j];

        return tMatrix;
    }

    private var inputLines : List<String> = listOf<String>();
    private var tables : MutableList<List<List<Int>>> = mutableListOf<List<List<Int>>>();
    private var tTables : List<List<List<Int>>> = listOf<List<List<Int>>>();
    private var rawInput : String = "";
    private var bingoNumbers : List<Int> = listOf<Int>();
}