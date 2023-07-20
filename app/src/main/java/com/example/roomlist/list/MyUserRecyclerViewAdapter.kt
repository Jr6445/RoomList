package com.example.roomlist.list

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.db.model.entity.User
import com.example.libdb.model.entity.Empresa
import com.example.roomlist.R

import com.example.roomlist.list.placeholder.PlaceholderContent.PlaceholderItem
import com.example.roomlist.databinding.FragmentUserBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyUserRecyclerViewAdapter(
    private val values: List<Empresa>
) : RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.nombre
        holder.direc.text = item.direccion
        //holder.email.text = item.email
        //holder.tele.text = item.telefono
        Glide.with(holder.itemView)
            .load(item.urlImagen)
            .into(holder.image);
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.content
        val direc: TextView = binding.dire
        //val email: TextView = binding.email
       // val tele: TextView = binding.tele
        val image:ImageView = binding.imageView

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}