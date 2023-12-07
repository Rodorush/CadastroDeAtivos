package br.com.rodorush.cadastrodeativos.repository

import androidx.lifecycle.LiveData
import br.com.rodorush.cadastrodeativos.dao.AtivoDAO
import br.com.rodorush.cadastrodeativos.model.Ativo

class AtivoRepository(private val ativoDAO: AtivoDAO) {

    suspend fun insert(ativo: Ativo) {
        ativoDAO.insert(ativo)
    }

    fun getAllAssets(): LiveData<List<Ativo>> {
        return ativoDAO.getAllAssets()
    }

    fun getAssetById(id: Int): LiveData<Ativo> {
        return ativoDAO.getAssetById(id)
    }
}