package com.example.loginaplication.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.loginaplication.R
import com.example.loginaplication.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding : FragmentSignInBinding? = null
    private val binding
        get() = _binding!! //si el valor es nulo, lanza una excepción

    //Se inicializará posteriormente el viewModel
    //private lateinit var viewModel: SignInViewModel
    private val viewModel:SignInViewModel by viewModels()

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

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btRegister.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        /*
        Este código ya no es necesario ya que se implementa mediante Data Binding
        binding.bUserList.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_userListFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}