package alura.com.br.aynctask;

import android.os.AsyncTask;

import java.util.List;

import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.entity.Aluno;
import alura.com.br.entity.Telefone;

public class BuscaTodosTelefonesTask extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final QuandoEncontrarTodosTelefonesListener listener;

    public BuscaTodosTelefonesTask(TelefoneDAO telefoneDAO, Aluno aluno, QuandoEncontrarTodosTelefonesListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quanoEncontrado(telefones);
    }

    public interface QuandoEncontrarTodosTelefonesListener {
        void quanoEncontrado(List<Telefone> telefones);
    }
}
