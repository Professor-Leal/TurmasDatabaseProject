package com.rafaelleal.android.TurmaAlunosdatabaseproject.daos

import androidx.room.*
import com.rafaelleal.android.turmasdatabaseproject.models.Aluno
import com.rafaelleal.android.turmasdatabaseproject.models.Turma
import com.rafaelleal.android.turmasdatabaseproject.models.TurmaAluno


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
interface TurmaAlunoDao {

//    @Insert(onConflict = OnConflictStrategy.ABORT)
//    Se tentar Inserir um item que já existe a operação será abortada.

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    Se tentar Inserir um item que já existe este será substituído pelo novo.

    // Se tentar inserir um item que já existe essa inserção será ignorada.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(TurmaAluno: TurmaAluno)

    // Atualiza item já existente
    @Update
    fun update(TurmaAluno: TurmaAluno)

    // Remove item da tabela
    @Delete
    fun delete(TurmaAluno: TurmaAluno)

    // Retorna todos os TurmaAlunos
    @Query("SELECT * FROM TurmaAluno")
    fun getAll() : List<TurmaAluno>

    // Retorna item da tabela onde o id é o requisitado
    @Query("SELECT * FROM TurmaAluno Where id = :id")
    fun getById(id: Long) : TurmaAluno

    // Retorna todos os ids de  alunos da turma:
    @Query("SELECT alunoId FROM TurmaAluno WHERE id = :turmaId  ")
    fun getAlunoIdsFromTurma(turmaId: Long): List<Long>

    // Retorna todos os ids de  turmas do aluno:
    @Query("SELECT turmaId FROM TurmaAluno WHERE id = :alunoId  ")
    fun getTurmaIdsFromAluno(alunoId: Long): List<Long>

    // Retorna alunos da turma
    @Query("SELECT * FROM Aluno WHERE EXISTS (SELECT * FROM TurmaAluno WHERE TurmaAluno.alunoId = Aluno.id AND TurmaAluno.turmaId = :turmaId )")
    fun getAlunosFromTurma(turmaId: Long): List<Aluno>

    // retorna turmas do aluno
    @Query("SELECT * FROM Turma WHERE EXISTS (SELECT * FROM TurmaAluno WHERE TurmaAluno.turmaId = Turma.id AND TurmaAluno.alunoId = :alunoId )")
    fun getTurmasFromAluno(alunoId: Long): List<Turma>

}