package Reader

import java.io.File

class Reader {
    public constructor(file: String) 
    {
        this.fileName = file;
    }

    public fun readInput() : List<String> 
    {
        val myList : MutableList<String> = mutableListOf<String>();
        val file : File = File("src/main/resources/" + this.fileName);
        file.useLines() { lines -> lines.forEach { myList.add(it) } }
        return myList;
    }

    public fun readRawInput() : String 
    {
        val file : File = File("src/main/resources/" + this.fileName);
        return file.readText();
    }

    private var fileName : String = "";
}
