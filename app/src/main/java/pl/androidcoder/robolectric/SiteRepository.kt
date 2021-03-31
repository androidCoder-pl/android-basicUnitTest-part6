package pl.androidcoder.robolectric

interface SiteRepository {
  fun getSites(success: (List<Site>) -> Unit)
}