package com.example.luastestapp.screens.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.luastestapp.R
import com.example.luastestapp.model.Tram
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class TramListAdapter(private var tramList: List<Tram>) :
    RecyclerView.Adapter<TramListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tramList[position])
    }

    override fun getItemCount(): Int {
        return tramList.size
    }

    fun updateTramList(updatedTramList: List<Tram>) {
        tramList = updatedTramList
        notifyDataSetChanged()
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(tram: Tram) {
            destinationTextView.text = tram.destination
            dueTimeTextView.text = formatDueMins(tram.dueMins)
        }

        private fun formatDueMins(dueMins: String): String {
            return if (dueMins.toInt() == 1)
                containerView.context.getString(
                    R.string.minute, dueMins
                ) else containerView.context.getString(R.string.minutes, dueMins)
        }
    }

}