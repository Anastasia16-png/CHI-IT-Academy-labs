package com.example.lab5

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SwitchAdapter(private val context: Context, private val items: List<String>) :
    RecyclerView.Adapter<SwitchAdapter.ViewHolder>() {

    private val states = BooleanArray(items.size) // перемикач on off

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
        val switchToggle: Switch = view.findViewById(R.id.switchToggle)

        fun bind(position: Int) {
            textView.text = if (states[position]) "ON" else "OFF"
            textView.setTextColor(if (states[position]) context.getColor(android.R.color.holo_green_dark)
            else context.getColor(android.R.color.holo_red_dark))

            switchToggle.isChecked = states[position]
            switchToggle.setOnCheckedChangeListener { _, isChecked ->
                states[position] = isChecked
                textView.text = if (isChecked) "ON" else "OFF"
                textView.setTextColor(if (isChecked) context.getColor(android.R.color.holo_green_dark)
                else context.getColor(android.R.color.holo_red_dark))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_switch, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = items.size
}