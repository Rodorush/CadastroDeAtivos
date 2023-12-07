package br.com.rodorush.cadastrodeativos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import br.com.rodorush.cadastrodeativos.databinding.AtivoCelulaBinding
import br.com.rodorush.cadastrodeativos.model.Ativo

class AtivoAdapter(): RecyclerView.Adapter<AtivoAdapter.AtivoViewHolder>(),
    Filterable {

    var listener: AtivoListener? = null
    var ativosLista = ArrayList<Ativo>()
    var ativosListaFilterable = ArrayList<Ativo>()

    private lateinit var binding: AtivoCelulaBinding

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
        return ativosListaFilterable.size
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

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(p0: CharSequence?): FilterResults {
                if (p0.toString().isEmpty())
                    ativosListaFilterable = ativosLista
                else {
                    val resultList = ArrayList<Ativo>()
                    for (row in ativosLista)
                        if (row.sigla.lowercase().contains(p0.toString().lowercase()))
                            resultList.add(row)
                    ativosListaFilterable = resultList
                }
                    val filterResults = FilterResults()
                    filterResults.values = ativosListaFilterable
                    return filterResults
                }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                ativosListaFilterable = p1?.values as ArrayList<Ativo>
                notifyDataSetChanged()
            }
        }
    }
}