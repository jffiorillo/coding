fun main(args: Array<String>) {

}

fun isomorphicString(first:String,second:String):Boolean{
    if (first.length != second.length)
        return false
    else if (first.isEmpty())
        return true
    first.zip(second).fold(Pair(mapOf<String,String>(),true)){acc, pair -> if (!acc.second) else  pair.first }
    return true
}