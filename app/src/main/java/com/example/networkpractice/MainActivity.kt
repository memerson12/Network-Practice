package com.example.networkpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.networkpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val personAdapter = RecyclerViewAdapter { person -> adapterOnClick(person) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = personAdapter

        flowersListViewModel.flowersLiveData.observe(this, {
            it?.let {
                flowersAdapter.submitList(it as MutableList<Flower>)
                headerAdapter.updateFlowerCount(it.size)
            }
        })
    }

    private fun adapterOnClick(person: Person) {
//        val intent = Intent(this, FlowerDetailActivity()::class.java)
//        intent.putExtra(FLOWER_ID, flower.id)
//        startActivity(intent)
    }
}