package com.example.loginaplication.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginaplication.R
import com.example.loginaplication.adapter.UserAdapter
import com.example.loginaplication.data.model.User
import com.example.loginaplication.databinding.FragmentUserListBinding
import com.example.loginaplication.ui.userlist.usecase.UserListState
import com.example.loginaplication.ui.userlist.usecase.UserListViewModel

class UserListFragment : Fragment(), UserAdapter.OnUserClick {
    private var _binding: FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

    //viewModels() es gestor/proveedor de viewmodel
    private val viewmodel: UserListViewModel by viewModels()

    private lateinit var userAdapter : UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpUserRecyler()

        viewmodel.getState().observe(viewLifecycleOwner, Observer {
            when (it) {
                is UserListState.Loading -> showProgressBar(it.value)
                UserListState.NoDataError -> showNoDataError()
                is UserListState.Success -> onSuccess(it.dataset)
            }
        })
    }

    /**
     * Cuando el fragment se inicia debe pedir el listado de usuarios al ViewModel-Infraestructura
     * (Firebase, Json, Room...)
     */
    override fun onStart() {
        super.onStart()
        viewmodel.getUserList()
    }

    private fun onSuccess(dataset: ArrayList<User>) {
        //desactivar la animación y visualizar el recuclerview
        hideNoDataError()
        userAdapter.update(dataset)
    }

    private fun hideNoDataError() {
        binding.animationView.visibility = View.GONE
        binding.rvUser.visibility = View.VISIBLE
    }

    /**
     * Función que muestra el error de no hay datos
     */
    private fun showNoDataError() {
        //cambia la visibilidad
        binding.animationView.visibility = View.VISIBLE //se visualiza, sale la animación de que no hay datos
        binding.rvUser.visibility = View.GONE //no ocupa espacio
    }

    /**
     * Mostrar el progressbar en la vista
     */
    private fun showProgressBar(value: Boolean) {
        if (value)
            findNavController().navigate((R.id.action_userListFragment_to_fragmentProgressDialog))
        else
            findNavController().popBackStack()
    }

    /**
     * Función que inicializa el RecyclerView que muestra el listado de usuarios de la aplicación
     */
    private fun setUpUserRecyler() {
        //Crear el Adapter con los valores en el constructor primario
        userAdapter = UserAdapter(this) {
            Toast.makeText(requireContext(), "Usuario seleccionado $it", Toast.LENGTH_SHORT).show()
        }

        //1. ¿Cómo quiero que se muestren los elementos de la lista?
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter = userAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     *
     */
    override fun userClick(user: User) {
        Toast.makeText(requireActivity(), "Pulsación corta en el usuario $user", Toast.LENGTH_SHORT)
            .show()
    }

    override fun userOnLongClick(user: User) {
        Toast.makeText(requireActivity(), "Pulsación larga en el usuario $user", Toast.LENGTH_SHORT)
            .show()
    }
}