package com.example.loginaplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loginaplication.R
import com.example.loginaplication.data.model.User
import com.example.loginaplication.databinding.LayoutUserItemBinding
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(
    private val dataset: MutableList<User>,
    private val context: Context,
    private val listener:OnUserClick,
    private val onItemClick: (user:User)-> Unit
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    /**
     * Esta interfaz es el contrato entre el Adapter y el Fragmento que lo contiene.
     */
    interface OnUserClick {
        fun userClick(user: User) //Pulsaciçon corta
        fun userOnLongClick(user: User) //Pulsación larga
        //fun deleteClick(user: User) //Eliminar usuario
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //return UserViewHolder(layoutInflater.inflate(R.layout.layout_user_item, parent, false))
        return UserViewHolder(LayoutUserItemBinding.inflate(layoutInflater, parent, false))
    }

    /**
     * Función que devuelve el número de elementos y por tanto se llamará el método onCreateViewHolder
     * tantas veces como item se visualicen en el RecyclerView
     */
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = dataset.get(position)
        holder.bind(item, context)
    }

    /**
     * La clase ViewHolder contiene todos los elementos de View o del layout XML que se ha inflado
     */
    //VERSION 1: usar findViewById
    /*
    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById(R.id.tvName) as TextView
        val tvSurname = view.findViewById(R.id.tvSurname) as TextView
        val tvEmail = view.findViewById(R.id.tvEmail) as TextView
        //val imgUser = view.findViewById(R.id.imgUser) as CircleImageView

        fun bind(item: User, context: Context) {
            tvName.text = item.name
            tvSurname.text = item.surname
            tvEmail.text = item.email
        }
    }*/
    inner class UserViewHolder(private val binding: LayoutUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User, context: Context) {
            with(binding) {
                //imgUser.text = item.name.substring(0)
                tvName.text = item.name
                tvSurname.text = item.surname
                tvEmail.text = item.email
                //Manejamos el evento EventClick
                root.setOnClickListener { _ ->
                    //Llamaré a un método de la interfaz declarada dentro del adapter
                    //Como no se utiliza el parámetro de entrada de tipo View, Kotlin me recomienda usar '_'
                    //listener.userClick(item)
                    onItemClick(item)
                }
                //Manejar la pulsación larga EventLongClick
                root.setOnLongClickListener { _ ->
                    listener.userOnLongClick(item)
                    //Se debe indicar al framework/android que se consume el evento
                    //todo ???
                    //return@SetOnLongClickListener true
                    true
                }

            }
            /*
            binding.tvName.text = item.name
            binding.tvSurname.text = item.surname
            binding.tvEmail.text = item.email
            //binding.imgUser.text = item.name.subString(0)*/
        }
    }
}