package com.freddieptf.reader

import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.github.chrisbanes.photoview.PhotoViewAttacher

/**
 * Created by fred on 3/22/15.
 */
class PicPagerAdapter(val pages: List<String>?) : PagerAdapter() {

    override fun getCount(): Int {
        return pages?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.pager_pic_item, container, false)
        val viewHolder = ViewHolder(view)
        viewHolder.bind(pages!![position])
        container.addView(view)
        return view
    }

    internal inner class ViewHolder(view: View) {
        var imageView: ImageView

        init {
            imageView = view.findViewById(R.id.pager_ImageView_item)
        }

        fun bind(path: String) {
            var p = PhotoViewAttacher(imageView)
            Glide.with(imageView.context).load(path).into(object :
                    CustomViewTarget<View, Drawable>(imageView) {
                override fun onResourceCleared(placeholder: Drawable?) {}
                override fun onLoadFailed(errorDrawable: Drawable?) {}
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    var rh = resource.intrinsicHeight
                    var rw = resource.intrinsicWidth
                    var ivh = imageView.height
                    var ivw = imageView.width
                    var matrix = Matrix()
                    val scale: Float
                    if (rw * ivh > ivw * rh) {
                        scale = ivh.toFloat() / rh.toFloat()
                    } else {
                        scale = ivw.toFloat() / rw.toFloat()
                    }
                    matrix.setScale(scale, scale)
                    imageView.scaleType = ImageView.ScaleType.MATRIX
                    imageView.imageMatrix = matrix
                    imageView.setImageDrawable(resource)
                }

            })
        }
    }
}


