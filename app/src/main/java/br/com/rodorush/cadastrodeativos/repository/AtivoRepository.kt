package br.com.rodorush.cadastrodeativos.repository

import br.com.rodorush.cadastrodeativos.dao.AtivoDAO
import br.com.rodorush.cadastrodeativos.model.Ativo

class AtivoRepository(private val ativoDAO: AtivoDAO) {

    suspend fun insert(ativo: Ativo) {
        ativoDAO.insert(ativo)
    }
}