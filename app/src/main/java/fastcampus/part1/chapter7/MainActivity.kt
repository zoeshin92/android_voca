package fastcampus.part1.chapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fastcampus.part1.chapter7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WordAdaptor.ItemClickListner {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordAdapter: WordAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.addButton.setOnClickListener {
            Intent(this, AddActivity::class.java).let{
                startActivity(it)
            }
        }
    }
    private fun initRecyclerView() {
        val dummyList = mutableListOf(
            Word("weather", "날씨", "명사"),
            Word("honey", "꿀", "명사"),
            Word("run", "달리다", "동사"),
        )
        wordAdapter = WordAdaptor(dummyList, this)
        binding.wordRecyclerView.apply {
            adapter = wordAdapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
    }

    override fun onClick(word: Word) {
        Toast.makeText(this, "${word.text}가 클릭 됐습니다", Toast.LENGTH_SHORT)
    }

}