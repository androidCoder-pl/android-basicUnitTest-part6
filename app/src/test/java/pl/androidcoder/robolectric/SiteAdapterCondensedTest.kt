package pl.androidcoder.robolectric

import android.app.Activity
import android.graphics.drawable.ColorDrawable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.android.synthetic.main.site_item.view.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric

@RunWith(AndroidJUnit4::class)
class SiteAdapterCondensedTest {
  private val context = Robolectric.buildActivity(Activity::class.java).get()
  private val adapter = SiteAdapter()

  private val site = Site(
    name = "Best android blog",
    url = "http://androidcoder.pl",
    tags = listOf("test", "test2"),
    labelColor = "#FFFFFF"
  )

  private val newSite = Site(
    name = "Best new android blog",
    url = "http://androidcoder.pl/home",
    tags = listOf("test", "test2", "test3"),
    labelColor = "#FFFFFF"
  )

  lateinit var recyclerView: RecyclerView
  lateinit var holder: SiteViewHolder

  @Before
  fun setUp() {
    recyclerView = RecyclerView(context)
    recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    recyclerView.adapter = adapter
    adapter.setData(listOf(site))
    recyclerView.measure(0, 0)
    holder = recyclerView.findViewHolderForLayoutPosition(0) as SiteViewHolder
  }

  @Test
  fun testBindViewHolder() {
    assertThat(holder.itemView.name.text.toString(), equalTo(site.name))
    assertThat(holder.itemView.url.text.toString(), equalTo(site.url))
    assertThat(holder.itemView.hashtags.text.toString(), equalTo("#test #test2"))
    assertThat((holder.itemView.color.background as ColorDrawable).color, equalTo(-1))
  }

  @Test
  fun testDataChanged() {
    adapter.setData(listOf(newSite))
    adapter.bindViewHolder(holder, 0)

    assertThat(holder.itemView.name.text.toString(), equalTo(newSite.name))
    assertThat(holder.itemView.url.text.toString(), equalTo(newSite.url))
    assertThat(holder.itemView.hashtags.text.toString(), equalTo("#test #test2 #test3"))
    assertThat((holder.itemView.color.background as ColorDrawable).color, equalTo(-1))
  }

  @Test
  fun testColorConverter() {
    assertConvertHexColor("", 0)
    assertConvertHexColor("WRONG DATA", 0)
    assertConvertHexColor("#FFFFFFFF", -1)
    assertConvertHexColor("#AAAAAA", -5592406)
    assertConvertHexColor("#00AAAAAA", 11184810)
  }

  @Test
  fun testConvertTags() {
    assertConvertTags(emptyList(), "")
    assertConvertTags(listOf("aa"), "#aa")
    assertConvertTags(listOf("aa", " "), "#aa")
    assertConvertTags(listOf("aa", ""), "#aa")
    assertConvertTags(listOf("aa", "bb"), "#aa #bb")
  }

  private fun assertConvertTags(tags: List<String>, listOfTags: String) {
    adapter.setData(listOf(newSite.copy(tags = tags)))
    adapter.bindViewHolder(holder, 0)
    assertThat(holder.itemView.hashtags.text.toString(), equalTo(listOfTags))
  }


  private fun assertConvertHexColor(hexColor: String, intColor: Int) {
    adapter.setData(listOf(newSite.copy(labelColor = hexColor)))
    adapter.bindViewHolder(holder, 0)
    assertThat((holder.itemView.color.background as ColorDrawable).color, equalTo(intColor))
  }
}