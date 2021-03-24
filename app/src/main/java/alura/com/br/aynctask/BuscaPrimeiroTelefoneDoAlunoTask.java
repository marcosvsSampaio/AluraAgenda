package alura.com.br.aynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.entity.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDAO dao;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO dao, int alunoId, PrimeiroTelefoneEncontradoListener primeiroTelefoneEncontradoListener) {
        this.dao = dao;
        this.alunoId = alunoId;
        this.listener = primeiroTelefoneEncontradoListener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return dao.getPrimeiroTelefone(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);
    }

    public interface PrimeiroTelefoneEncontradoListener {
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
