package com.example.personappp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.personappp.databinding.PersonItemBinding
import com.example.personappp.model.Person

class PersonAdapter(private val personList:ArrayList<Person>):RecyclerView.Adapter<PersonAdapter.MyViewHolder>() {

    var onClick : ((Person)->Unit) ?= null

    class MyViewHolder(val binding: PersonItemBinding) :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = PersonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.apply {
            personName.text = personList[position].personName
            personCourse.text = "course " + personList[position].personCourse. toString()
            personCenter.text = "center" + personList[position].personCenter.toString()
            personImg.setImageResource(personList[position].personImg)
        }

        holder.itemView.setOnClickListener {
            onClick?.invoke(personList[position])
        }

        holder.itemView.setOnLongClickListener {
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Fruit Item")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes") { _, _ ->
                    personList.removeAt(position)
                    notifyItemRemoved(position)
                }
                .setNegativeButton("No", null)
                .show()
            true
        }


    }


}

