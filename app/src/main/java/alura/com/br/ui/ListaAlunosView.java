package alura.com.br.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import alura.com.br.aynctask.BuscaAlunosTask;
import alura.com.br.aynctask.RemoveAlunoTask;
import alura.com.br.database.AgendaDatabase;
import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.entity.Aluno;
import alura.com.br.ui.adapter.ListaAlunosAdapter;

public class ListaAlunosView {

    private final ListaAlunosAdapter adapter;
    private final Context context;
    private final AlunoDAO dao;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(context);
        dao = AgendaDatabase.getInstance(context).getRoomAlunoDao();
    }

    public void confirmaRemocaoDoAluno(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setTitle("Remover Aluno")
                .setMessage("Deseja Remover o aluno?")
                .setNegativeButton("Não", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeAluno(item);
                    }
                })
                .show();
    }

    public void removeAluno(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
        remove(alunoEscolhido);
    }

    public void atualizaAluno() {
        new BuscaAlunosTask(adapter, dao).execute();
    }

    public void remove(Aluno aluno) {
        new RemoveAlunoTask(adapter, dao, aluno).execute();
    }

    public void configuraAdapter(ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(adapter);
    }
}
