package br.com.rodorush.cadastrodeativos.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.rodorush.cadastrodeativos.database.AtivoDatabase
import br.com.rodorush.cadastrodeativos.model.Ativo
import br.com.rodorush.cadastrodeativos.repository.AtivoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AtivoViewModel(application: Application): AndroidViewModel(application) {
    private val repository: AtivoRepository
    var allAssets: LiveData<List<Ativo>>
    lateinit var ativo: LiveData<Ativo>

    init {
        val dao = AtivoDatabase.getDatabase(application).ativoDAO()
        repository = AtivoRepository(dao)
        allAssets = repository.getAllAssets()
    }

    fun insert(ativo: Ativo) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(ativo)
    }
}