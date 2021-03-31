package pl.androidcoder.robolectric

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

open class MainActivity : AppCompatActivity() {

  lateinit var repository: SiteRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    inject()
    val adapter = SiteAdapter()
    list.adapter = adapter

    repository.getSites(
      success = {
        adapter.setData(it)
      }
    )
  }

  open fun inject() {
    repository = RealSiteRepository()
  }
}
