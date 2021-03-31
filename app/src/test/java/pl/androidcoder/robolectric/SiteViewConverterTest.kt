package pl.androidcoder.robolectric

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

class SiteViewConverterTest {
  @Test
  fun testConvertTags() {
    assertConvertTags(emptyList(), "")
    assertConvertTags(listOf("aa"), "#aa")
    assertConvertTags(listOf("aa", " "), "#aa")
    assertConvertTags(listOf("aa", ""), "#aa")
    assertConvertTags(listOf("aa", "bb"), "#aa #bb")
  }

  private fun assertConvertTags(tags: List<String>, listOfTags: String) {
    assertThat(SiteViewConverter.convertTags(tags), equalTo(listOfTags))
  }
}

@RunWith(AndroidJUnit4::class)
class SiteViewConverterAndroidTest {
  @Test
  fun testConvertHexColor() {
    assertConvertHexColor("", 0)
    assertConvertHexColor("WRONG DATA", 0)
    assertConvertHexColor("#FFFFFFFF", -1)
    assertConvertHexColor("#AAAAAA", -5592406)
    assertConvertHexColor("#00AAAAAA", 11184810)
  }

  private fun assertConvertHexColor(hexColor: String, intColor: Int) {
    assertThat(SiteViewConverter.convertHexColor(hexColor), equalTo(intColor))
  }
}