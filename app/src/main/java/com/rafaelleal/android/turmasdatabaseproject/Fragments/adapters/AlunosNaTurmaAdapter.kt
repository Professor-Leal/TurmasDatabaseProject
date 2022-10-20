package com.rafaelleal.android.turmasdatabaseproject.Fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rafaelleal.android.turmasdatabaseproject.databinding.AlunosNaTurmaListItemBinding
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno


/**
 * Extende ListAdapter
 * com parâmetros:
 * classe modelo: Aluno
 * viewHolder a ser criado dentro do Adapter
 * passando o Callback criado ao final do arquivo
 */
class AlunosNaTurmaAdapter(val listener: AlunosNaTurmaListener) :
    ListAdapter<
            Aluno,
            AlunosNaTurmaAdapter.ViewHolder
            >(AlunosNaTurmaDiffCallback()) {

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
        val binding: AlunosNaTurmaListItemBinding,
        val listener: AlunosNaTurmaListener
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Aluno, position: Int) {
            binding.apply {
                alunoNome.text  = item.nome
                matricula.text = item.matricula
                periodo.text = item.periodo.toString()

                ivDelete.setOnClickListener {
                    listener.onDeleteClick(item)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, listener: AlunosNaTurmaListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlunosNaTurmaListItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding, listener)
            }
        }
    }

}

class AlunosNaTurmaDiffCallback : DiffUtil.ItemCallback<Aluno>() {

    override fun areItemsTheSame(oldItem: Aluno, newItem: Aluno): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Aluno, newItem: Aluno): Boolean {
        return oldItem == newItem
    }
}

// implementar cliques:
// Crie a interface e passe dentro do ViewHolder
interface AlunosNaTurmaListener {
    fun onDeleteClick(aluno: Aluno)
}

