package pl.androidcoder.robolectric.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import pl.androidcoder.robolectric.*

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  private val scenario = Robolectric.buildActivity(TestedActivity::class.java)
  private val mockRepository = MockSiteRepository()


  @Before
  fun setUp() {
    scenario.get().repository = mockRepository
  }

  private fun setSites(sites: List<Site>) {
    mockRepository.sites = sites
  }

  @Test
  fun show_none_items_when_sites_are_empty() {
    setSites(emptyList())
    scenario.setup()
    onView(withId(R.id.list)).check(matches(hasChildCount(0)))
  }

  @Test
  fun show_data_when_sites_are_not_empty() {
    //GIVEN
    val site = Site("Test name", "http://test.com", listOf("tag1", "tag2"), "#FF0000")
    setSites(listOf(site))
    //WHEN
    scenario.setup()
    //THEN
    onView(withId(R.id.list)).check(matches(hasChildCount(1)))
    onView(
      allOf(
        withChild(withText(site.name)),
        withChild(withText(site.url)),
        withChild(withText("#tag1 #tag2")),
        isDescendantOfA(withId(R.id.list))
      )
    ).check(matches(isDisplayed()))
  }

  @Test
  fun test_adapter_usage() {
    //GIVEN
    setSites(listOf())
    //WHEN
    scenario.setup()
    //THEN
    onView(withId(R.id.list)).check { view, _ ->
      if (view is RecyclerView) {
        assert(view.adapter is SiteAdapter)
      }
    }
  }
}

class TestedActivity : MainActivity() {
  override fun inject() {}
}