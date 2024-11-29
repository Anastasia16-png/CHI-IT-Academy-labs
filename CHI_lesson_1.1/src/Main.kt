//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import kotlin.random.Random
import kotlin.system.measureTimeMillis

// Bubble Sort
fun bubbleSort(arr: IntArray) {
    val n = arr.size
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                // Обмін елементів
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
}

// Quick Sort
fun quickSort(arr: IntArray, low: Int, high: Int) {
    if (low < high) {
        val pi = partition(arr, low, high)
        quickSort(arr, low, pi - 1)
        quickSort(arr, pi + 1, high)
    }
}

fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1
    for (j in low until high) {
        if (arr[j] <= pivot) {
            i++
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    val temp = arr[i + 1]
    arr[i + 1] = arr[high]
    arr[high] = temp
    return i + 1
}

// тестування
fun main() {
    val arraySize = 1000
    val randomArray1 = IntArray(arraySize) { Random.nextInt(0, 10000) }
    val randomArray2 = randomArray1.copyOf() // копія масиву для Quick Sort

    // вимірювання часу виконання Bubble Sort
    val bubbleSortTime = measureTimeMillis {
        bubbleSort(randomArray1)
    }
    // час виконання Quick Sort
    val quickSortTime = measureTimeMillis {
        quickSort(randomArray2, 0, randomArray2.size - 1)
    }

    println("Час виконання Bubble Sort $bubbleSortTime ms")
    println("Час виконання Quick Sort $quickSortTime ms")
}