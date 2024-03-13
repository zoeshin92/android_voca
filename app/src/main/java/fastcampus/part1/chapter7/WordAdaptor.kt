package fastcampus.part1.chapter7

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fastcampus.part1.chapter7.databinding.ItemWordBinding

class WordAdaptor(private val list: MutableList<Word>) :
    RecyclerView.Adapter<WordAdaptor.WordViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflator =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = ItemWordBinding.inflate(inflator, parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.binding.apply {
            val word = list[position]
            textTextView.text = word.text
            meanTextView.text = word.mean
            typeChip.text = word.type
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class WordViewHolder(val binding: ItemWordBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}