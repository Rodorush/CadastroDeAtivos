package br.com.rodorush.cadastrodeativos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.rodorush.cadastrodeativos.databinding.AtivoCelulaBinding
import br.com.rodorush.cadastrodeativos.model.Ativo

class AtivoAdapter():
    RecyclerView.Adapter<AtivoAdapter.AtivoViewHolder>() {

        private lateinit var binding: AtivoCelulaBinding

        var ativosLista = ArrayList<Ativo>()
        var listener: AtivoListener? = null
        var ativosListaFilterable = ArrayList<Ativo>()

    fun updateList(newList: ArrayList<Ativo> ) {
        ativosLista = newList
        ativosListaFilterable = ativosLista
        notifyDataSetChanged()
    }

    fun setClickLestener(listener: AtivoListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AtivoViewHolder {
        binding = AtivoCelulaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AtivoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AtivoViewHolder, position: Int) {
        holder.siglaVH.text = ativosLista[position].sigla
        holder.nomeVH.text = ativosLista[position].nome
    }

    override fun getItemCount(): Int {
        return ativosLista.size
    }

    inner class AtivoViewHolder(view:AtivoCelulaBinding): RecyclerView.ViewHolder(view.root) {
        val siglaVH = view.sigla
        val nomeVH = view.nome

        init {
            view.root.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    interface AtivoListener {
        fun onItemClick(pos: Int)
    }
}