package com.rafaelleal.android.Alunosdatabaseproject.daos

import androidx.room.*
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno



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
 */

@Dao
interface AlunoDao {

//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    Se tentar Inserir um item que já existe a operação será abortada.

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    Se tentar Inserir um item que já existe este será substituído pelo novo.

    // Se tentar inserir um item que já existe essa inserção será ignorada.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(aluno: Aluno)

    // Atualiza item já existente
    @Update
    fun update(aluno: Aluno)

    // Remove item da tabela
    @Delete
    fun delete(aluno: Aluno)

    // Retorna todos os Alunos
    @Query("SELECT * FROM Aluno")
    fun getAll() : List<Aluno>

    // Retorna item da tabela onde o id é o requisitado
    @Query("SELECT * FROM Aluno Where id = :id")
    fun getById(id: Long) : Aluno

}