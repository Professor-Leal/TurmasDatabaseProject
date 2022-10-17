package com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelleal.android.turmasdatabaseproject.databinding.TurmaListItemBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Turma

/**
 * Extende ListAdapter
 * com parâmetros:
 * classe modelo: Turma
 * viewHolder a ser criado dentro do Adapter
 * passando o Callback criado ao final do arquivo
 */
class TurmasAdapter(val listener: TurmaListener) :
    ListAdapter<
            Turma,
            TurmasAdapter.ViewHolder
            >(TurmaDiffCallback()) {

//    val swipeToDeleteCallback = SwipeToDeleteCallback()


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    /**
     * ViewHolder: Fixa os dados do modelo no item da lista
     */
    class ViewHolder private constructor(
        val binding: TurmaListItemBinding,
        val listener: TurmaListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Turma, position: Int) {
            binding.apply {
                turmaNome.text = item.nome
                turmaProfessor.text = item.professor
                turmaHorario.text = item.horario
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: TurmaListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TurmaListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}


class TurmaDiffCallback : DiffUtil.ItemCallback<Turma>() {

    override fun areItemsTheSame(oldItem: Turma, newItem: Turma): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Turma, newItem: Turma): Boolean {
        return oldItem == newItem
    }
}


// implementar cliques:
// Crie a interface e passe dentro do ViewHolder
interface TurmaListener {
    fun onClick(posicao: Int)
}

