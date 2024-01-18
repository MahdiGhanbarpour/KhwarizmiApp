package ir.mahdighanbarpour.khwarazmiapp.features.homeStudentScreen.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.mahdighanbarpour.khwarazmiapp.R
import ir.mahdighanbarpour.khwarazmiapp.databinding.ItemPopularExamBinding

class PopularExamAdapter(
    private val data: ArrayList<Pair<String, String>>,
    private val popularExamEvents: PopularExamEvents
) : RecyclerView.Adapter<PopularExamAdapter.PopularExamAdapterViewHolder>() {

    private lateinit var binding: ItemPopularExamBinding

    inner class PopularExamAdapterViewHolder(binding: ItemPopularExamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(data: Pair<String, String>) {
            binding.tvPopularExamNameItem.text = data.first

            Glide.with(binding.root.context).load(data.second).error(R.drawable.img_error)
                .apply(RequestOptions().centerCrop()).into(binding.ivPopularExamImageItem)

            itemView.setOnClickListener {
                popularExamEvents.onPopularExamClicked(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PopularExamAdapterViewHolder {
        binding = ItemPopularExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularExamAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PopularExamAdapterViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    interface PopularExamEvents {
        fun onPopularExamClicked(data: Pair<String, String>)
    }
}