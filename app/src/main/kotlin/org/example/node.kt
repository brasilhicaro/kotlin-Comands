package org.example

interface Tree<out T>

data class Node<out T>(val value: T, val left: Tree<T> = End, val right: Tree<T> = End) : Tree<T> {
    override fun toString(): String {
        val children = if (left == End && right == End) "" else " $left $right"
        return "T($value$children)"
    }
}

object End : Tree<Nothing> {
    override fun toString() = "."
}

fun insert(tree: Tree<Int>, value: Int): Tree<Int>{
    if (tree is End){
        return Node(value)
    }
    if (tree is Node){
        if (value < tree.value){
            return Node(tree.value, insert(tree.left, value), tree.right)
        }
        if (value > tree.value){
            return Node(tree.value, tree.left, insert(tree.right, value))
        }
    }
    return tree
}

fun lenght(tree: Tree<Int>): Int {
    var count = 0
    if (tree is End){
        return count
    }
    if (tree is Node){
        count++
        count += lenght(tree.left)
        count += lenght(tree.right)
    }
    return count
}

fun leafsValues(tree: Tree<Int>): List<Int> {
    val list = mutableListOf<Int>()
    if (tree is End){
        return list
    }
    if (tree is Node){
        if (tree.left is End && tree.right is End){
            list.add(tree.value)
        }
        list.addAll(leafsValues(tree.left))
        list.addAll(leafsValues(tree.right))
    }
    return list
}

fun convertToTree(list: String): String{
    val stack = mutableListOf<Node<Int>>()
    var current: Node<Int>? = null

    for (char in input) {
        when {
            char.isDigit() -> {
                val value = char.toString().toInt()
                val newNode = Node(value)
                if (current != null) {
                    stack.add(newNode)
                    if (current.left == End) {
                        current.left = newNode
                    } else {
                        current.right = newNode
                    }
                }
                current = newNode
            }
            char == '(' -> {
                // No '(' vamos fazer o push do nó atual para a pilha
                stack.add(current!!)
            }
            char == ',' -> {
                // No ',' vamos continuar na mesma posição na árvore
            }
            char == ')' -> {
                // No ')' vamos pop o nó atual da pilha
                current = stack.removeAt(stack.lastIndex)
            }
        }
    }

    // A raiz da árvore será o último nó na pilha
    return stack.lastOrNull() ?: End
}
}
fun convertToString(tree:Tree<Int>): String{
    var str = ""
    // expected output: "30(11(9,10),40(,60(50,70)))"
    if (tree is End){
        return " "
    }
    if (tree is Node){
        str += tree.value
        if (tree.left is Node){
            str += "("
            str += convertToString(tree.left)
            str += ","
        }
        if (tree.right is Node){
            str += "("
            str += convertToString(tree.right)
            str += ","
        }
    }
    str += ")"
    return str
}

fun main() {
    var initTree = Node(30, Node(11, Node(9), Node(10)), Node(40, End, Node(60, Node(50), End)))
    println("fiz o insert")
    var tree = insert(initTree, 70)
    println(tree)
    println("o tamanho da arvore é")
    println(lenght(tree))
    println("as folhas da arvore são")
    println(leafsValues(tree))

    println("convertendo a arvore para string")
    println(convertToString(tree))
    
    val list2 = "30(11(9,12),40(,60(50,70)))"
    println(convertToTree(list2))

}
