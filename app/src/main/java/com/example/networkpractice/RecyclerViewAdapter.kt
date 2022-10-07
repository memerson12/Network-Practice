package com.example.networkpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val onClick: (Person) -> Unit) :
    ListAdapter<Person, RecyclerViewAdapter.PersonViewHolder>(PersonDiffCallback) {

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    class PersonViewHolder(itemView: View, val onClick: (Person) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val personTextView: TextView = itemView.findViewById(R.id.person_name)
        private val personImageView: ImageView = itemView.findViewById(R.id.avatar_image)
        private var currentPerson: Person? = null

        init {
            itemView.setOnClickListener {
                currentPerson?.let {
                    onClick(it)
                }
            }
        }

        /* Bind flower name and image. */
        fun bind(person: Person) {
            currentPerson = person

            personTextView.text = person.name
//            if (person.image != null) {
//                personImageView.setImageResource(person.image)
//            } else {
//                personImageView.setImageResource(R.drawable.rose)
//            }
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        return PersonViewHolder(view, onClick)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}

object PersonDiffCallback : DiffUtil.ItemCallback<Person>() {
    override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
        return oldItem.id == newItem.id
    }
}