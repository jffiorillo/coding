package io.sort


fun <T : Comparable<T>> List<T>.quickSort(): List<T> = when {
  size < 2 -> this
  else ->
    this[this.size / 2].let { pivot ->
      this.filter { it < pivot }.quickSort() + this.filter { it == pivot } + this.filter { it > pivot }.quickSort()
    }
}