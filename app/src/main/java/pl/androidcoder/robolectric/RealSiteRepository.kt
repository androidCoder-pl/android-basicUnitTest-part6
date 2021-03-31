package pl.androidcoder.robolectric

class RealSiteRepository : SiteRepository {
  override fun getSites(success: (List<Site>) -> Unit) {
    success.invoke(
      (1..20).map {
        Site(
          name = "Best android blog $it",
          url = "http://androidcoder.pl/$it",
          tags = listOf("test", "test$it"),
          labelColor = listOf("#B4B5BE", "#333738", "#B18742", "#635B6C").get(it%4)
        )
      }
    )
  }
}