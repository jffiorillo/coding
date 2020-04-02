package io.utils


fun <T, V, O> runTests(values: List<T>, execution: (T) -> Triple<Boolean, V, O>) {
  values.forEachIndexed { index, input ->
    val (isValid, value, output) = execution(input)
    if (isValid) {
      println("index $index output $output is valid")
    } else {
      println("index $index Expected $value but instead got $output")
    }

  }
}