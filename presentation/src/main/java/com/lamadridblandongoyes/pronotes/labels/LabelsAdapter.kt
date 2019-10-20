package com.lamadridblandongoyes.pronotes.labels

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lamadridblandongoyes.domain.models.Label
import com.lamadridblandongoyes.pronotes.R
import com.lamadridblandongoyes.pronotes.notes.ItemTapListener
import com.lamadridblandongoyes.pronotes.labels.LabelsAdapter.LabelViewHolder
import kotlinx.android.synthetic.main.row_label.view.*


class LabelsAdapter(private var itemTapListener: ItemTapListener)
    : RecyclerView.Adapter<LabelViewHolder>(){

    private var labels: ArrayList<Label> = ArrayList<Label>()

    fun addLabels(labels: ArrayList<Label>) {
        this.labels.addAll(labels)
    }

    fun clearLabels() {
        this.labels.clear()
    }

    fun addLabel(label: Label) {
        this.labels.add(label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_label,parent,false)
        return LabelViewHolder(view)
    }

    override fun getItemCount(): Int = labels.size

    override fun onBindViewHolder(holder: LabelViewHolder, position: Int) {
        val label = labels[position]

        holder.tvTitle.text = label.title

        holder.containerView.setCardBackgroundColor(Color.parseColor(label.color))

        holder.itemView.setOnClickListener {
            itemTapListener.onItemTapped(position)
        }

        holder.itemView.setOnLongClickListener {
            itemTapListener.onItemLongTapped(position)
        }
    }

    inner class LabelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvTitle: AppCompatTextView = itemView.label_title
        val containerView: CardView = itemView.row_label_container
    }
}