package com.uns.testitemtouchhelper.bottombar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uns.testitemtouchhelper.ItemTouchHelperListener
import com.uns.testitemtouchhelper.databinding.BottomNavItemBinding

class BottomNavBarListAdapter(val items: MutableList<BottomNavItem>) : RecyclerView.Adapter<BottomNavBarListAdapter.ViewHolder>(), ItemTouchHelperListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(BottomNavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int = items.size

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        val item = items[fromPosition]

        items.removeAt(fromPosition)
        items.add(toPosition, item)

        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(val binding: BottomNavItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: BottomNavItem) {
            binding.navImageView.setBackgroundResource(item.icon)
            binding.navNameTextView.text = itemView.context.getString(item.text)
        }

    }

}