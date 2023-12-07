package br.com.rodorush.cadastrodeativos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.rodorush.cadastrodeativos.R
import br.com.rodorush.cadastrodeativos.databinding.FragmentDetalheBinding
import br.com.rodorush.cadastrodeativos.model.Ativo
import br.com.rodorush.cadastrodeativos.viewmodel.AtivoViewModel
import com.google.android.material.snackbar.Snackbar

class DetalheFragment : Fragment() {
    private var _binding: FragmentDetalheBinding? = null
    private val binding get() = _binding!!

    lateinit var ativo: Ativo

    lateinit var siglaEditText: EditText
    lateinit var nomeEditText: EditText
    lateinit var mercadoEditText: EditText

    lateinit var viewModel: AtivoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(AtivoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalheBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        siglaEditText = binding.commonLayout.editTextSigla
        nomeEditText = binding.commonLayout.editTextNome
        mercadoEditText = binding.commonLayout.editTextMercado

        val idAtivo = requireArguments().getInt("idAtivo")

        viewModel.getAssetById(idAtivo)

        viewModel.ativo.observe(viewLifecycleOwner) { result ->
            result?.let {
                ativo = result
                siglaEditText.setText(ativo.sigla)
                nomeEditText.setText(ativo.nome)
                mercadoEditText.setText(ativo.mercado)
            }
        }

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.detalhe_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_alterarAtivo -> {

                        ativo.sigla = siglaEditText.text.toString()
                        ativo.nome = nomeEditText.text.toString()
                        ativo.mercado = mercadoEditText.text.toString()

                        viewModel.update(ativo)
                        Snackbar.make(binding.root, "Ativo alterado", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    R.id.action_excluirAtivo -> {
                        viewModel.delete(ativo)
                        Snackbar.make(binding.root, "Ativo apagado", Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}