package com.express.android.lwbooksearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.express.android.lwbooksearch.databinding.RecyclerListRowBinding
import com.express.android.lwbooksearch.model.BookListModel
import com.express.android.lwbooksearch.model.VolumeInfo

class BookListAdapter: RecyclerView.Adapter<BookListAdapter.MyViewHolder>() {

    var bookListData = ArrayList<VolumeInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RecyclerListRowBinding.inflate(
                LayoutInflater.from(parent.context), parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookListData[position])
    }

    override fun getItemCount(): Int {
        return bookListData.size
    }

    private val diffCallback = object : DiffUtil.ItemCallback<BookListModel>(){
        override fun areItemsTheSame(oldItem: BookListModel, newItem: BookListModel): Boolean {
            return oldItem.items == newItem.items
        }

        override fun areContentsTheSame(oldItem: BookListModel, newItem: BookListModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    var books: List<BookListModel>
        get() = differ.currentList
        set(value) { differ.submitList(value)}

    class MyViewHolder(val binding: RecyclerListRowBinding): RecyclerView.ViewHolder(binding.root) {

        val tvTitle = binding.tvTitle
        val tvPublisher = binding.tvPublisher
        val tvDescription = binding.tvDescription
        val thumbImageView = binding.thumbImageView

        fun bind(data : VolumeInfo){
            tvTitle.text = data.volumeInfo.title
            tvPublisher.text = data.volumeInfo.publisher
            tvDescription.text = data.volumeInfo.description

            val url  = data.volumeInfo.imageLinks.smallThumbmail
            Glide.with(thumbImageView)
                .load(url)
                .circleCrop()
                .into(thumbImageView)
        }
    }
}