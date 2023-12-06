package br.com.rodorush.cadastrodeativos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.rodorush.cadastrodeativos.R
import br.com.rodorush.cadastrodeativos.databinding.FragmentListaAtivosBinding

class ListaAtivosFragment : Fragment() {
    private var _binding: FragmentListaAtivosBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListaAtivosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listaAtivosFragment_to_cadastroFragment) }
        return root
    }
}