package com.jmj.mypatients.views.treatment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jmj.domain.treatment.Treatment
import com.jmj.mypatients.databinding.FragmentTreatmentsBinding

class TreatmentsAdapter(private val onClickListener:TreatmentListener):
    ListAdapter<Treatment, TreatmentsAdapter.ViewHolder>(TreatmentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    class ViewHolder private constructor(private val binding: FragmentTreatmentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentTreatmentsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(
            item: Treatment,
            onClickListener: TreatmentListener
        ) {
            binding.treatment = item
            binding.onClickListener = onClickListener
        }
    }
}

class TreatmentDiffCallback : DiffUtil.ItemCallback<Treatment>() {
    override fun areItemsTheSame(oldItem: Treatment, newItem: Treatment) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Treatment, newItem: Treatment) = oldItem == newItem
}

class TreatmentListener(private val clickListener: (treatmentId: String) -> Unit) {
    fun onClick(treatment: Treatment) = clickListener.invoke(treatment.id)
}
