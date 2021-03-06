/*
 * Copyright (c) 2020 My VKU by tsnAnh
 */

package dev.tsnanh.vku.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.tsnanh.vku.viewholders.ImageViewerViewHolder

class ImageViewerPagerAdapter(
    private val listImage: Array<String>
) : RecyclerView.Adapter<ImageViewerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewerViewHolder.from(parent)

    override fun getItemCount() = listImage.size

    override fun onBindViewHolder(holder: ImageViewerViewHolder, position: Int) {
        holder.bind(listImage[position])
    }
}