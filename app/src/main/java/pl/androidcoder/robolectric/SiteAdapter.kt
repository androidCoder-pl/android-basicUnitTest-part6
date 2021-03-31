package pl.androidcoder.robolectric

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.androidcoder.robolectric.databinding.SiteItemBinding

open class SiteAdapter : RecyclerView.Adapter<SiteViewHolder>() {

  private var items = emptyList<Site>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SiteViewHolder =
    SiteViewHolder(SiteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

  override fun getItemCount(): Int = items.size

  override fun onBindViewHolder(holder: SiteViewHolder, position: Int) {
    holder.bind(items[position])
  }

  fun setData(newItems : List<Site>){
    items = newItems
    notifyDataSetChanged()
  }
}

class SiteViewHolder(
  private val binding: SiteItemBinding
) : RecyclerView.ViewHolder(binding.root) {

  fun bind(site: Site) {
    binding.site = site
    binding.executePendingBindings()
  }
}