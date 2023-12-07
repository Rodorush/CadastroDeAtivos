package br.com.rodorush.cadastrodeativos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import br.com.rodorush.cadastrodeativos.databinding.FragmentDetalheBinding
import br.com.rodorush.cadastrodeativos.model.Ativo
import br.com.rodorush.cadastrodeativos.viewmodel.AtivoViewModel

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
    }
}