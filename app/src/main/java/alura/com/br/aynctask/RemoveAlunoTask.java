package alura.com.br.aynctask;

import android.os.AsyncTask;

import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.entity.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class RemoveAlunoTask extends AsyncTask<Void, Void, Void> {
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Aluno aluno;

    public RemoveAlunoTask(ListaAlunosAdapter adapter, AlunoDAO dao, Aluno aluno) {
        this.adapter = adapter;
        this.dao = dao;
        this.aluno = aluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remover(aluno);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(aluno);
    }
}
