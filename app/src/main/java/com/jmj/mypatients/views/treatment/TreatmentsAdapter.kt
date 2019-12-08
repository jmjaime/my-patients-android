package com.jmj.mypatients.views.treatment


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jmj.domain.action.model.TreatmentAbbreviatedModel
import com.jmj.mypatients.databinding.FragmentTreatmentsBinding

class TreatmentsAdapter(private val onClickListener: TreatmentListener) :
    ListAdapter<TreatmentAbbreviatedModel, TreatmentsAdapter.ViewHolder>(TreatmentDiffCallback()) {

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
            item: TreatmentAbbreviatedModel,
            onClickListener: TreatmentListener
        ) {
            binding.treatment = item
            binding.onClickListener = onClickListener
        }
    }
}

class TreatmentDiffCallback : DiffUtil.ItemCallback<TreatmentAbbreviatedModel>() {
    override fun areItemsTheSame(
        oldItem: TreatmentAbbreviatedModel,
        newItem: TreatmentAbbreviatedModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: TreatmentAbbreviatedModel,
        newItem: TreatmentAbbreviatedModel
    ) = oldItem == newItem
}

class TreatmentListener(private val clickListener: (treatmentId: String) -> Unit) {
    fun onClick(treatment: TreatmentAbbreviatedModel) = clickListener.invoke(treatment.id)
}
