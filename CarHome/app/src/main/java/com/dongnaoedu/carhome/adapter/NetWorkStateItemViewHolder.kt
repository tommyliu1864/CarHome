package com.dongnaoedu.carhome.adapter

import android.view.View
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.dongnaoedu.carhome.databinding.NetworkStateItemBinding

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class NetWorkStateItemViewHolder(
    private val binding: NetworkStateItemBinding,
    val retryCallback: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bindData(data: LoadState) {
        binding.apply {
            // 正在加载，显示进度条
            progress.isVisible = data is LoadState.Loading
            // 加载失败，显示并点击重试按钮
            retryButton.isVisible = data is LoadState.Error
            retryButton.setOnClickListener { retryCallback() }
            // 加载失败显示错误原因
            errorMsg.isVisible = !(data as? LoadState.Error)?.error?.message.isNullOrBlank()
            errorMsg.text = (data as? LoadState.Error)?.error?.message
        }
    }

}

inline var View.isVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }