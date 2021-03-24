package alura.com.br.aynctask;

import android.os.AsyncTask;

import java.util.List;

import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.entity.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void, Void, List<Aluno>> {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;

    public BuscaAlunosTask(ListaAlunosAdapter adapter, AlunoDAO dao) {
        this.adapter = adapter;
        this.dao = dao;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        return dao.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
