package alura.com.br.aynctask;

import android.os.AsyncTask;

import alura.com.br.entity.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void, Void, Void> {
    private final FinalizadaListener listener;

    BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        listener.quandoFinalizada();
    }

    protected void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone:
                telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    public interface FinalizadaListener {
        void quandoFinalizada();
    }
}
