package alura.com.br.database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import alura.com.br.entity.TipoTelefone;

import static alura.com.br.entity.TipoTelefone.FIXO;

class AgendaMigrations {

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };

    //    Migrar Banco de dados para Versão anterior
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            Criar nova tabela com as informações desejadas
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT," +
                    " `telefone` TEXT," +
                    " `email` TEXT)");

//            Copiar dados da tabela antiga  para a nova
            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                    "SELECT id, nome, telefone, email FROM Aluno");

//            Remove tabela antiga
            database.execSQL("DROP TABLE Aluno");

//            Renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN criadoEm INTEGER");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT," +
                    " `telefoneCelular` TEXT," +
                    " `email` TEXT," +
                    " `criadoEm` INTEGER," +
                    " `telefoneFixo` TEXT)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, telefoneFixo, email)" +
                    "SELECT id, nome, telefone, email FROM Aluno");

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };


    private static final Migration MIGRATION_5_6 = new Migration(5, 6) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "`nome` TEXT," +
                    "`email` TEXT," +
                    "`criadoEm` INTEGER)");

            database.execSQL("INSERT INTO Aluno_novo (id, nome, email, criadoEm)" +
                    "SELECT id, nome, email, criadoEm FROM Aluno");

            database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "`numero` TEXT," +
                    "`tipo` TEXT," +
                    "`alunoId` INTEGER NOT NULL)");

            database.execSQL("INSERT INTO Telefone (numero, alunoId) " +
                    "SELECT telefoneFixo, id FROM Aluno");

            database.execSQL("UPDATE Telefone SET tipo = ?", new TipoTelefone[] {FIXO});

            database.execSQL("DROP TABLE Aluno");

            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };
    static final Migration[] MIGRATIONS = {MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6};
}