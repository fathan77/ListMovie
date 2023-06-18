package com.d3if0154.listmovie.ui.daftarMovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d3if0154.listmovie.R
import com.d3if0154.listmovie.databinding.ListItemBinding
import com.d3if0154.listmovie.model.DaftarMovie
import com.d3if0154.listmovie.network.DaftarMovieApi

class DaftarMovieAdapter : RecyclerView.Adapter<DaftarMovieAdapter.ViewHolder>() {

    private val data = mutableListOf<DaftarMovie>()

    fun updateData(newData: List<DaftarMovie>){
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(daftarMovie: DaftarMovie) = with(binding){
            namaTextView.text = daftarMovie.nama
            tahunTextView.text= daftarMovie.tahun
            Glide.with(imageView.context)
                .load(DaftarMovieApi.getDaftarMovieUrl(daftarMovie.imageId))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener {
//                val message = root.context.getString(R.string.message, hewan.nama)
//                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() : Int {
        return data.size
    }
}