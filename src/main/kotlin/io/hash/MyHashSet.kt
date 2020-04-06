package io.hash

class MyHashSet() {

  private val max = 100000
  private val intSize = Integer.SIZE
  private val bitArr: IntArray= IntArray(max / intSize + 1)

  fun add(key: Int) {
    val ind = key / intSize
    val bit = key % intSize
    val bitMask = 1 shl bit
    bitArr[ind] = bitArr[ind] or bitMask
  }

  fun remove(key: Int) {
    val ind = key / intSize
    val bit = key % intSize
    val bitMask = (1 shl bit).inv()
    bitArr[ind] = bitArr[ind] and bitMask
  }

  operator fun contains(key: Int): Boolean {
    val ind = key / intSize
    val bit = key % intSize
    val bitMask = 1 shl bit
    return bitArr[ind] and bitMask != 0
  }

}