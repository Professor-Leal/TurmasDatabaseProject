package com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelleal.android.turmasdatabaseproject.databinding.AlunoListItemBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno


/**
 * Extende ListAdapter
 * com parâmetros:
 * classe modelo: Aluno
 * viewHolder a ser criado dentro do Adapter
 * passando o Callback criado ao final do arquivo
 */
class AlunosAdapter(val listener: AlunoListener) :
    ListAdapter<
            Aluno,
            AlunosAdapter.ViewHolder
            >(AlunoDiffCallback()) {

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
        val binding: AlunoListItemBinding,
        val listener: AlunoListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Aluno, position: Int) {
            binding.apply {
                alunoNome.text  = item.nome
                matricula.text = item.matricula
                periodo.text = item.periodo.toString()
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: AlunoListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlunoListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}


class AlunoDiffCallback : DiffUtil.ItemCallback<Aluno>() {

    override fun areItemsTheSame(oldItem: Aluno, newItem: Aluno): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Aluno, newItem: Aluno): Boolean {
        return oldItem == newItem
    }
}


// implementar cliques:
// Crie a interface e passe dentro do ViewHolder
interface AlunoListener {
    fun onClick(posicao: Int)
}

