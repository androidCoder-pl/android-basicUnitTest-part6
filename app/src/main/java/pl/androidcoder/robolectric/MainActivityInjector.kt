package pl.androidcoder.robolectric

class MainActivityInjector {
  var repository: SiteRepository = RealSiteRepository()

  fun inject(mainActivity: MainActivity) {
    mainActivity.repository = repository
  }
}