package br.com.rodorush.cadastrodeativos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)

                val searchView = menu.findItem(R.id.action_search).actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(p0: String?): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        ativoAdapter.filter.filter(p0)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                TODO("Not yet implemented")
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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

        val listener = object : AtivoAdapter.AtivoListener {
            override fun onItemClick(pos: Int) {
                val a = ativoAdapter.ativosListaFilterable[pos]

                val bundle = Bundle()
                bundle.putInt("idAtivo", a.id)

                findNavController().navigate(
                    R.id.action_listaAtivosFragment_to_detalheFragment,
                    bundle
                )
            }
        }
        ativoAdapter.setClickLestener(listener)
    }
}