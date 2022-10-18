package com.rafaelleal.android.turmasdatabaseproject.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import kotlinx.coroutines.flow.Flow

/**
 * Anotações para o banco de dados:
 *
 * @Dao -> Data Access Object: objeto para acessar dados da tabela
 *
 * @Insert -> Implementa inserção no banco de dados
 *
 * @Update -> Implementa atualização no banco de dados
 *
 * @Delete -> Implementa a remoção no banco de dados
 *
 * @Query -> Faz uma pesquisa como descrita com SQL
 *
 * SQL Tutorial:
 * https://www.w3schools.com/sql/default.asp
 *
 */

@Dao
interface TurmaDao {

//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    Se tentar Inserir um item que já existe a operação será abortada.

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    Se tentar Inserir um item que já existe este será substituído pelo novo.

    // Se tentar inserir um item que já existe essa inserção será ignorada.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(turma: Turma)

    // Atualiza item já existente
    @Update
    fun update(turma: Turma)

    // Remove item da tabela
    @Delete
    fun delete(turma: Turma)

    // Retorna todas as turmas
    @Query("SELECT * FROM Turma")
    fun getAll() : Flow<List<Turma>>

    // Flow mantém a lista atualizada de acordo com o banco de dados.
    // Atualizações são percebidas.
    // Também não é necessário usar suspend.

    // Retorna item da tabela onde o id é o requisitado
    @Query("SELECT * FROM Turma Where id = :id")
    fun getById(id: Long) : Turma

}