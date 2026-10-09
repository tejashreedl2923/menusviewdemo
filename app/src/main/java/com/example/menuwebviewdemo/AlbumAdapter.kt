
package com.example.menuwebviewdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import coil.load

data class AlbumItem(
    val title: String,
    val imageResId: Int = 0,
    val imageUri: android.net.Uri? = null,
    val imageUrl: String? = null
)

class AlbumAdapter(
    private val context: Context,
    private val images: MutableList<AlbumItem>
) : BaseAdapter() {

    override fun getCount(): Int = images.size

    override fun getItem(position: Int): AlbumItem = images[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_album, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.ivAlbumImage)
        val item = images[position]

        when {
            item.imageUri != null -> imageView.load(item.imageUri)

            item.imageResId != 0 ->
                imageView.setImageResource(item.imageResId)

            item.imageUrl != null -> {
                imageView.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(android.R.drawable.ic_menu_gallery)
                    error(android.R.drawable.ic_menu_report_image)
                }
            }

            else -> imageView.setImageResource(
                android.R.drawable.ic_menu_gallery
            )
        }

        return view
    }

    fun addImage(item: AlbumItem) {
        images.add(item)
        notifyDataSetChanged()
    }
}
