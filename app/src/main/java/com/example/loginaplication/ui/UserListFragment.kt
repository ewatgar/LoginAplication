package com.example.loginaplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginaplication.adapter.UserAdapter
import com.example.loginaplication.data.repository.UserRepository
import com.example.loginaplication.databinding.FragmentUserListBinding

class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding
        get() = _binding!!

    //private val adapter : UserAdapter = UserAdapter()
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setUpUserAdapter() //se debe llamar antes que la creación del Recycler
        setUpUserRecyler()
    }

    /**
     * Función que inicializa el RecyclerView que muestra el listado de usuarios de la aplicación
     */
    private fun setUpUserRecyler() {
        //Crear el Adapter con los valores en el constructor primario
        var adapter: UserAdapter = UserAdapter(UserRepository.dataSet,requireContext())

        //1. ¿Cómo quiero que se muestren los elementos de la lista?
        with (binding.rvUser){
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }
    /*
    private fun setUpDataSetUser(): MutableList<User>{
        var dataSet: MutableList<User> = ArrayList()
        dataSet.add(User("Lourdes","Rodriguez","lourdes@iesportada.org"))
        dataSet.add(User("aaa","bbb","abc@iesportada.org"))
        return dataSet
    }*/
}