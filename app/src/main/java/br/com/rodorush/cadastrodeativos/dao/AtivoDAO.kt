package br.com.rodorush.cadastrodeativos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.rodorush.cadastrodeativos.model.Ativo

@Dao
interface AtivoDAO {
    @Insert
    suspend fun insert(ativo: Ativo)

    @Query("SELECT * FROM ativo ORDER BY nome")
    fun getAllAssets(): LiveData<List<Ativo>>
}