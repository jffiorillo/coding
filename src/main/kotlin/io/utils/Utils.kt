package io.utils


fun <INPUT, VALUE, OUTPUT> runTests(
    values: List<INPUT>
    , evaluation: (VALUE, OUTPUT) -> Boolean = { v, o -> v == o }
    , valueString: (VALUE) -> String = { it.toString() }
    , outputString: (OUTPUT) -> String = { it.toString() }
    , execution: (INPUT) -> Pair<VALUE, OUTPUT>) {
  values.forEachIndexed { index, input ->
    val (value, output) = execution(input)
    if (evaluation(value, output)) {
      println("index $index output ${outputString(output)} is valid")
    } else {
      println("index $index Expected ${valueString(value)}")
      println(" but instead got ${outputString(output)}")
    }

  }
}