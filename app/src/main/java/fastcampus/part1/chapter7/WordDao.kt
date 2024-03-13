package fastcampus.part1.chapter7

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface WordDao{
    @Query("SELECT * FROM WORD ORDER BY ID DESC")
    fun getAll(): List<Word>

    @Query("SELECT * FROM WORD ORDER BY ID DESC LIMIT 1")
    fun getLatestWord(): Word

    @Insert
    fun insert(word: Word)

    @Delete
    fun delete(word: Word)

    @Update
    fun update(word: Word)
}