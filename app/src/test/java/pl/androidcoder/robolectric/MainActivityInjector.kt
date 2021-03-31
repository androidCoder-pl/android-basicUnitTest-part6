package pl.androidcoder.robolectric

import pl.androidcoder.robolectric.activity.MainActivityTest

class MainActivityInjector {
  fun inject(mainActivity: MainActivity) {
    mainActivity.repository = MockSiteRepository()
  }
}