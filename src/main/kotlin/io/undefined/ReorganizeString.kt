package io.undefined

import io.utils.runTests


// https://leetcode.com/problems/reorganize-string/
class ReorganizeString {

  fun execute(input: String): String {
    val result = StringBuilder()
    val counts = input.fold(IntArray(26)) { acc, value -> acc.apply { this[value.toIndex()]++ } }
    var last: Int? = null
    while (result.length != input.length) {
      val max = counts.maxIndex(last)
      if (counts[max] == 0) break
      counts[max]--
      last = max
      result.append(last.fromIndexToChar())

    }
    return if (result.length == input.length) result.toString() else ""
  }

  private fun IntArray.maxIndex(invalid: Int? = null): Int = this.foldIndexed( null as Int?) { index, accIndex, value ->
    when {
      index == invalid -> accIndex
      accIndex != null && this[accIndex] > value -> accIndex
      else -> index
    }
  }!!

  private fun Char.toIndex() = this - 'a'
  private fun Int.fromIndexToChar() = 'a' + this
}

fun main() {
  runTests(listOf(
      "aab" to "aba",
      "aaab" to "",
      "aaba" to "",
      "vvvlo" to "vovlv",
      "zhmyo" to "zyomh",
      "gpneqthatplqrofqgwwfmhzxjddhyupnluzkkysofgqawjyrwhfgdpkhiqgkpupgdeonipvptkfqluytogoljiaexrnxckeofqojltdjuujcnjdjohqbrzzzznymyrbbcjjmacdqyhpwtcmmlpjbqictcvjgswqyqcjcribfmyajsodsqicwallszoqkxjsoskxxstdeavavnqnrjelsxxlermaxmlgqaaeuvneovumneazaegtlztlxhihpqbajjwjujyorhldxxbdocklrklgvnoubegjrfrscigsemporrjkiyncugkksedfpuiqzbmwdaagqlxivxawccavcrtelscbewrqaxvhknxpyzdzjuhvoizxkcxuxllbkyyygtqdngpffvdvtivnbnlsurzroxyxcevsojbhjhujqxenhlvlgzcsibcxwomfpyevumljanfpjpyhsqxxnaewknpnuhpeffdvtyjqvvyzjeoctivqwann" to "jxjqjxjqjljxjqnljxqnljecaxvqonljecayxvqponljecayxvqponljecayxvurqponljgecayxvusrqponlkjhgedcazyxvusrqponlkjhgedcazyxvutsrqponlkjihgfedcazyxwvutsrqponlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcba",
      "nlmxhnpifuaxinxpxlcttjnlggmkjioewbecnofqpvcikiazmn" to "ninxnixpnmlicxtponmlkjigfecazxwvutqponmlkjihgfecba",
      "tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao" to "hehemhecmhecvmhecvnmhecyvnmhfecaywvnmkhfecaywvunmkhfecaywvusnmkhfecayxwvusrpnmkhfecayxwvusrponmkhgfecayxwvutsrponmkihgfecbayxwvutsrponmkihgfedcbazyxwvutsrponmkihgfedcbazyxwvutsrponmkihgfedcbazyxwvutsrponmkihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcbazyxwvutsrqponmlkjihgfedcba"
  )) { (input, value) -> value to ReorganizeString().execute(input) }
}