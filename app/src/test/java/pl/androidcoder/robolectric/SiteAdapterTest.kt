package pl.androidcoder.robolectric

import android.app.Activity
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.spyk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric

@RunWith(AndroidJUnit4::class)
class SiteAdapterTest {
  private val activity = Robolectric.buildActivity(Activity::class.java)
  private val context = activity.get()
  private val adapter = spyk(SiteAdapter()) {
    every { onCreateViewHolder(any(), any()) } answers { spyk(callOriginal()) }
  }

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

  private lateinit var recyclerView: RecyclerView
  private lateinit var holder: SiteViewHolder

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
    verify { holder.bind(site) }
  }

  @Test
  fun testDataChanged() {
    adapter.setData(listOf(newSite))
    adapter.bindViewHolder(holder, 0)

    verify { holder.bind(newSite) }
  }
}
