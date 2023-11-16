package com.example.loginaplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.loginaplication.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding=FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Se utiliza esta función para inicializar los componentes que se han creado ya en la función
     * onCreateView
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //1. Crear colección de datos
        val itemList = arrayListOf("Private","Public","Empty")
        //2. Crear adapter
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item, itemList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        /*
        binding.spProfile.adapter = adapter
        binding.spProfile.setSelection(2)
        //3. Inicializar el listener que se lanza cuando el usuario modifica el valor
        binding.spProfile.onItemSelectedListener=null*/

        //Se usa el modismo With que dado un objeto se puede modificar propiedades dentro del bloque
        with (binding.spProfile){
            this.adapter = adapter
            setSelection(2)
            onItemSelectedListener=null
        }
    }
}