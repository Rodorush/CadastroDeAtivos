package br.com.rodorush.cadastrodeativos.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.rodorush.cadastrodeativos.model.Ativo

@Dao
interface AtivoDAO {
    @Insert
    suspend fun insert(ativo: Ativo)
}