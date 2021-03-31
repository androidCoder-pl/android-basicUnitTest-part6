package pl.androidcoder.robolectric.view

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import pl.androidcoder.robolectric.Site
import pl.androidcoder.robolectric.databinding.SiteItemBinding

@RunWith(AndroidJUnit4::class)
class SiteItemViewTest {
  private val context = Robolectric.buildActivity(Activity::class.java).get()
  private lateinit var layoutInflater: LayoutInflater
  private lateinit var binding: SiteItemBinding

  @Before
  fun setUp() {
    layoutInflater = LayoutInflater.from(context)
    binding = SiteItemBinding.inflate(layoutInflater)
  }

  @Test
  fun testSiteItemBinding() {
    val site = Site(
      name = "Best android blog",
      url = "http://androidcoder.pl",
      tags = listOf("test", "test2"),
      labelColor = "#FFFFFF"
    )
    binding.site = site
    binding.executePendingBindings()

    assertThat(binding.name.text.toString(), equalTo(site.name))
    assertThat(binding.url.text.toString(), equalTo(site.url))
    assertThat(binding.hashtags.text.toString(), equalTo("#test #test2"))
    assertThat((binding.color.background as ColorDrawable).color, equalTo(-1))
  }
}