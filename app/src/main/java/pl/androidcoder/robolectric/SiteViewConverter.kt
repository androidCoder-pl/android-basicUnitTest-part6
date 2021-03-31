package pl.androidcoder.robolectric

import android.graphics.Color
import java.lang.IllegalArgumentException

class SiteViewConverter {
  companion object {
    @JvmStatic
    fun convertTags(tags: List<String>): String {
      return if (tags.isEmpty()) ""
      else tags.filter { it.isNotBlank() }
        .joinToString(separator = " ") { "#$it" }
    }

    @JvmStatic
    fun convertHexColor(hexLabelColor: String): Int {
      if(hexLabelColor.isBlank()) return 0
      return try {
        Color.parseColor(hexLabelColor)
      } catch (e: IllegalArgumentException) {
        0
      }
    }
  }
}