package com.example.personappp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personappp.adapter.PersonAdapter
import com.example.personappp.databinding.ActivityMainBinding
import com.example.personappp.model.Person


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var personAdapter: PersonAdapter
    val person = ArrayList<Person>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.personRv.layoutManager = LinearLayoutManager(this)


        person.add(com.example.personappp.model.Person("Arman", "AMAD", "DIPTI", R.drawable.armann, "he is a good boy"))
        person.add(com.example.personappp.model.Person("Shamima", "AMAD", "DIPTI", R.drawable.wifu, "He is my wifu....ekta love emoji"))
        person.add(com.example.personappp.model.Person("Noman", "AMAD", "DIPTI", R.drawable.noman, "he is a bosti boy"))
        person.add(com.example.personappp.model.Person("Sazzad", "AMAD", "DIPTI", R.drawable.batla, "he is a bad boy"))
        person.add(com.example.personappp.model.Person("Shayekh", "AMAD", "DIPTI", R.drawable.hadi, "he is a massage boy"))
        person.add(com.example.personappp.model.Person("Tanvir", "AMAD", "DIPTI", R.drawable.tanvir, "he is a phamechy boy"))
        person.add(com.example.personappp.model.Person("Rahat", "AMAD", "DIPTI", R.drawable.rah, "he is a  jia udddan"))
        person.add(com.example.personappp.model.Person("Kawsar", "AMAD", "DIPTI", R.drawable.kaw, "he is a good boy"))


        personAdapter = PersonAdapter(person)
        binding.personRv.adapter = personAdapter

        personAdapter.onClick={
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it. personName)
            intent.putExtra("course", it. personCourse)
            intent.putExtra("center", it. personCenter)
            intent.putExtra("image", it. personImg)
            intent.putExtra("desc", it. personDesc)
            startActivity(intent)
        }

        binding.addBtn.setOnClickListener {
           showpersonAddDialog()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                person.removeAt(viewHolder.adapterPosition)
                personAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.personRv)



    }

    private fun showpersonAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_person_layout, null)
        val nameEt = dialogView.findViewById<EditText>(R.id.personNameEt)
        val priceEt = dialogView.findViewById<EditText>(R.id.personCourseEt)
        val qntEt = dialogView.findViewById<EditText>(R.id.personCenterEt)
        val descEt = dialogView.findViewById<EditText>(R.id.personDescEt)

        AlertDialog.Builder(this)
            .setTitle("Add Person")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEt.text.toString()
                val course = priceEt.text.toString().toDouble()
                val center = qntEt.text.toString().toInt()
                val desc = descEt.text.toString()
                val img = R.drawable.arman
                person.add(com.example.personappp.model.Person(name, center.toString(),
                    course.toString(),img,desc))
                personAdapter.notifyItemInserted(person.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()

    }


}