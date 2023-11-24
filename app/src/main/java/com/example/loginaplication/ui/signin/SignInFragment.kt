package com.example.loginaplication.ui.signin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.loginaplication.R
import com.example.loginaplication.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding
        get() = _binding!! //si el valor es nulo, lanza una excepción

    //Se inicializará posteriormente el viewModel
    //private lateinit var viewModel: SignInViewModel
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignInBinding.inflate(inflater, container, false)

        //Pasamos a la interfaz la instancia del ViewModel para que actualice / recoga los valores
        //del email y password automáticamente y asociar el evento onClick del botón a una función
        //se especifica en el xml la variable viewmodel
        binding.viewmodel = this.viewModel

        //IMPORTANTE: hay que establecer el Fragment/Activity vinculado al binding para actualizar
        //los valores del Binding en base al ciclo de vida
        binding.lifecycleOwner = this

        binding.tieEmailSignIn.addTextChangedListener(SignInTextWatcher(binding.tieEmailSignIn))

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        /* Este código ya no es necesario ya que se implementa mediante Data Binding:
        binding.bUserList.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_userListFragment)
        }*/
        viewModel.getState().observe(viewLifecycleOwner, Observer {
            when (it) {
                SignInState.EmailFormatError -> setEmailEmptyError()
                SignInState.PasswordEmptyError -> setPasswordEmptyError()
                //hay que poner 'is' porque es un data CLASS
                is SignInState.AuthenticationError -> showMessage(it.message)
                else -> onSuccess()
            }
        })
    }

    /**
     * Función que muestra al usuario un mensaje
     */
    private fun showMessage(message: String) {
        //TODO: showMessage
        Toast.makeText(requireContext(), "Mi primer MVVM: $message", Toast.LENGTH_SHORT).show()
    }

    /**
     * Función que muestra el error de Email Empty
     */
    private fun setEmailEmptyError() {
        binding.tieEmailSignIn.error = getString(R.string.errEmailEmpty)
        binding.tilEmailSignIn.requestFocus() //el cursor se posiciona en el campo donde haya error
    }

    /**
     * Función que muestra el error de Password Empty
     */
    private fun setPasswordEmptyError() {
        binding.tiePasswordSignIn.error = getString(R.string.errEmailEmpty)
        binding.tilPasswordSignIn.requestFocus() //el cursor se posiciona en el campo donde haya error
    }

    private fun onSuccess() {
        Toast.makeText(requireContext(), "Caso de uso/Error", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open inner class SignInTextWatcher(private val textView: TextView) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            //no se usa
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //no se usa
        }

        //la clase externa debe ver las propiedades de la clase interna
        override fun afterTextChanged(s: Editable?) {
            when (textView.id) {
                R.id.tieEmailSignIn -> {
                    binding.tilEmailSignIn.error = null
                    binding.tilPasswordSignIn.isErrorEnabled = false
                }

                R.id.tiePasswordSignIn -> binding.tilPasswordSignIn.error = null

            }
        }
    }
}