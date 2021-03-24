package alura.com.br.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import alura.com.br.database.converter.ConverterCalendar;
import alura.com.br.database.converter.ConverterTipoTelefone;
import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.entity.Aluno;
import alura.com.br.entity.Telefone;

import static alura.com.br.database.AgendaMigrations.MIGRATIONS;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ConverterCalendar.class, ConverterTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {

    public static final String NOME_BANCO_DE_DADOS = "agenda.db";
    public abstract AlunoDAO getRoomAlunoDao();
    public abstract TelefoneDAO getTelefoneDAO();


    public static AgendaDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DE_DADOS)
                .addMigrations(MIGRATIONS)
                .build();
    }
}
