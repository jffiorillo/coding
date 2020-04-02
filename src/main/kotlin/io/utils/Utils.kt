package io.utils


fun <INPUT, VALUE, OUTPUT> runTests(
    values: List<INPUT>
    , evaluation: (VALUE, OUTPUT) -> Boolean = { v, o -> v == o }
    , execution: (INPUT) -> Pair<VALUE, OUTPUT>) {
  values.forEachIndexed { index, input ->
    val (value, output) = execution(input)
    if (evaluation(value, output)) {
      println("index $index output $output is valid")
    } else {
      println("index $index Expected $value but instead got $output")
    }

  }
}