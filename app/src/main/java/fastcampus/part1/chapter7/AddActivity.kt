package fastcampus.part1.chapter7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.addTextChangedListener
import com.google.android.material.chip.Chip
import fastcampus.part1.chapter7.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private var originWord: Word? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        binding.addButton.setOnClickListener {
            if (originWord == null) add() else edit()
        }
    }

    private fun initViews() {
        val types = listOf(
            "명사", "동사", "대명사", "형용시", "부사", "감탄사", "전치사", "접속사"
        )
        binding.typeChipGroup.apply {
            types.forEach { text ->
                addView(createChip(text))
            }
        }
        binding.textInputEditText.addTextChangedListener {
            it?.let { text ->
                binding.textTextInputLayout.error = when (text.length) {
                    0 -> "값을 입력해주세요"
                    1 -> "2자 이상을 입력해주세요"
                    else -> null
                }
            }
        }

        originWord = intent.getParcelableExtra("originWord")
        originWord?.let { word ->
            binding.textInputEditText.setText(word.text)
            binding.meanTextInputEditText.setText(word.mean)
            val selectedChip =
                binding.typeChipGroup.children.firstOrNull { (it as Chip).text == word.type } as? Chip
            selectedChip?.isChecked = true
        }
    }

    private fun createChip(text: String): Chip {
        return Chip(this).apply {
            setText(text)
            isCheckable = true
            isClickable = true
        }
    }

    private fun add() {
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
//        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val checkedChipId = binding.typeChipGroup.checkedChipId
        val type = if (checkedChipId != View.NO_ID) {
            val chip = findViewById<Chip>(checkedChipId)
            chip?.text?.toString() ?: ""
        } else ""
        if (text.isEmpty() || mean.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "값을 모두 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }
        val word = Word(text, mean, type)
        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.insert(word)
            runOnUiThread {
                Toast.makeText(this, "저장되었습니다", Toast.LENGTH_SHORT).show()
            }
            val intent = intent.putExtra("isUpdated", true)
            setResult(RESULT_OK, intent)
            finish()
        }.start()
    }

    private fun edit() {
        val text = binding.textInputEditText.text.toString()
        val mean = binding.meanTextInputEditText.text.toString()
        val type = findViewById<Chip>(binding.typeChipGroup.checkedChipId).text.toString()
        val editedWord = originWord?.copy(text = text, mean = mean, type = type)

        Thread {
            editedWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.update(word)
                val intent = Intent().putExtra("editWord", editedWord)
                setResult(RESULT_OK, intent)
                runOnUiThread { Toast.makeText(this, "수정되었습니다", Toast.LENGTH_SHORT).show() }
                finish()
            }
        }.start()
    }
}

// cmd + shift + v : 이전의 복사들 기록