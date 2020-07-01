package alura.com.br.ui;

import android.app.Application;

import alura.com.br.DAO.AlunoDAO;
import alura.com.br.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunoDeTeste();
    }

    private void criaAlunoDeTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Marcos", "11963197188", "marcos@gmail.com"));
        dao.salva(new Aluno("Bruna", "11963194567", "bruna@gmail.com"));
    }
}
