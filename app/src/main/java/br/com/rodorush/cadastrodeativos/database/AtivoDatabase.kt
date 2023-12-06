package br.com.rodorush.cadastrodeativos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.rodorush.cadastrodeativos.dao.AtivoDAO
import br.com.rodorush.cadastrodeativos.model.Ativo

@Database(entities = [Ativo::class], version = 1)
abstract class AtivoDatabase: RoomDatabase() {
    abstract fun ativoDAO(): AtivoDAO

    companion object {
        @Volatile
        private var INSTANCE: AtivoDatabase? = null

        fun getDatabase(context: Context): AtivoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AtivoDatabase::class.java,
                    "cadastrodeativos.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}