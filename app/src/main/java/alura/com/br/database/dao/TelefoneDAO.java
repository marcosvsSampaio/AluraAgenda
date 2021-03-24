package alura.com.br.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import alura.com.br.entity.Telefone;

@Dao
public interface TelefoneDAO {
    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId " +
            "LIMIT 1")
    Telefone getPrimeiroTelefone(int alunoId);

    @Insert
    void salva(Telefone ... telefones);

    @Query("SELECT * FROM Telefone " +
            "WHERE alunoId = :alunoId")
    List<Telefone> buscaTodosTelefonesDoAluno(int alunoId);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
