package pl.androidcoder.robolectric

class MockSiteRepository : SiteRepository {
  var sites: List<Site>? = null
    set(value) {
      field = value
      value?.let { sites ->
        pendingRequest.forEach { it.invoke(sites) }
      }
    }
  private val pendingRequest = mutableListOf<((List<Site>) -> Unit)>()

  override fun getSites(success: (List<Site>) -> Unit) {
    sites.let {
      if (it == null) pendingRequest += success
      else success.invoke(it)
    }
  }
}