package io.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.withContext
import kotlin.math.min


const val workers = 500
suspend fun <T, R> createActorWithResult(
    values: List<T>, dispatcher: CoroutineDispatcher = Dispatchers.IO,
    createWorkers: (ReceiveChannel<T>, SendChannel<R>) -> Unit
): List<R> = withContext(dispatcher) {
  var workLeft = values.size
  val sendChannel = Channel<T>()
  val receiverChannel = Channel<R>()
  val result = mutableListOf<R>()
  try {
    repeat(workLeft) { createWorkers(sendChannel, receiverChannel) }
    values.forEach { sendChannel.send(it) }
    if (workLeft > 0)
      for (nextValue in receiverChannel) {
        result.add(nextValue)
        workLeft--
        if (workLeft == 0)
          return@withContext result
      }
  } finally {
    sendChannel.close()
    receiverChannel.close()
  }
  return@withContext result
}

suspend fun <T> createActor(
    values: List<T>, dispatcher: CoroutineDispatcher = Dispatchers.IO,
    createWorkers: (ReceiveChannel<T>, SendChannel<Boolean>) -> Unit
) = withContext(dispatcher) {
  var workLeft = values.size
  val sendChannel = Channel<T>()
  val receiverChannel = Channel<Boolean>()
  try {
    repeat(min(workers, workLeft)) { createWorkers(sendChannel, receiverChannel) }
    values.forEach { sendChannel.send(it) }
    if (workLeft > 0)
      for (i in receiverChannel) {
        workLeft--
        if (workLeft == 0)
          break
      }

  } finally {
    sendChannel.close()
    receiverChannel.close()
  }
}