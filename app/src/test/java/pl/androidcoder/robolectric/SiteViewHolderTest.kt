package pl.androidcoder.robolectric

import android.app.Activity
import android.view.LayoutInflater
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.spyk
import io.mockk.verify
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import pl.androidcoder.robolectric.databinding.SiteItemBinding

@RunWith(AndroidJUnit4::class)
class SiteViewHolderTest {
  private val context = Robolectric.buildActivity(Activity::class.java).get()
  private lateinit var layoutInflater: LayoutInflater
  private lateinit var binding: SiteItemBinding

  private val site = Site(
    name = "Best android blog",
    url = "http://androidcoder.pl",
    tags = listOf("test", "test2"),
    labelColor = "#FFFFFF"
  )

  @Before
  fun setUp() {
    layoutInflater = LayoutInflater.from(context)
    binding = spyk(SiteItemBinding.inflate(layoutInflater))
  }

  @Test
  fun testHolderBinding() {
    val holder = SiteViewHolder(binding)
    holder.bind(site)
    assertThat(holder.itemView, equalTo(binding.root))
    verify { binding.site = site }
  }
}