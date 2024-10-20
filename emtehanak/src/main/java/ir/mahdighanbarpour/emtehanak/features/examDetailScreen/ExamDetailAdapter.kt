package ir.mahdighanbarpour.emtehanak.features.examDetailScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.mahdighanbarpour.emtehanak.R
import ir.mahdighanbarpour.emtehanak.databinding.ItemExamDetailBinding

class ExamDetailAdapter(private val data: ArrayList<Triple<String, String, Int>>) :
    RecyclerView.Adapter<ExamDetailAdapter.ExamDetailAdapterViewHolder>() {

    private lateinit var binding: ItemExamDetailBinding

    inner class ExamDetailAdapterViewHolder(binding: ItemExamDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bindData(data: Triple<String, String, Int>) {
            // Placing the sent data in the relevant views
            binding.tvTitleItemExamDetail.text = data.first
            binding.tvValueItemExamDetail.text = data.second

            // Load image
            Glide.with(binding.root.context).load(data.third).error(R.drawable.img_error)
                .apply(RequestOptions().centerCrop()).into(binding.ivImageItemExamDetail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamDetailAdapterViewHolder {
        binding = ItemExamDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExamDetailAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ExamDetailAdapterViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}