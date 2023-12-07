package br.com.rodorush.cadastrodeativos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.rodorush.cadastrodeativos.R
import br.com.rodorush.cadastrodeativos.adapter.AtivoAdapter
import br.com.rodorush.cadastrodeativos.databinding.FragmentListaAtivosBinding
import br.com.rodorush.cadastrodeativos.model.Ativo
import br.com.rodorush.cadastrodeativos.viewmodel.AtivoViewModel

class ListaAtivosFragment : Fragment() {
    private var _binding: FragmentListaAtivosBinding? = null
    private val binding get() = _binding!!
    lateinit var ativoAdapter: AtivoAdapter
    lateinit var viewModel: AtivoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaAtivosBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listaAtivosFragment_to_cadastroFragment)
        }

        configureRecyclerView()

        return binding.root
    }

    private fun configureRecyclerView() {
        viewModel = ViewModelProvider(this).get(AtivoViewModel::class.java)
        viewModel.allAssets.observe(viewLifecycleOwner) { list ->
            list?.let {
                ativoAdapter.updateList(list as ArrayList<Ativo>)
            }
        }

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        ativoAdapter = AtivoAdapter()
        recyclerView.adapter = ativoAdapter
    }
}