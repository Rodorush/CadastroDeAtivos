package br.com.rodorush.cadastrodeativos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.rodorush.cadastrodeativos.model.Ativo

@Dao
interface AtivoDAO {
    @Insert
    suspend fun insert(ativo: Ativo)

    @Update
    suspend fun update(ativo: Ativo)

    @Delete
    suspend fun delete(ativo: Ativo)

    @Query("SELECT * FROM ativo ORDER BY nome")
    fun getAllAssets(): LiveData<List<Ativo>>

    @Query("SELECT * FROM ativo WHERE id=:id")
    fun getAssetById(id: Int): LiveData<Ativo>
}