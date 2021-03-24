package alura.com.br.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alura.com.br.R;
import alura.com.br.aynctask.BuscaPrimeiroTelefoneDoAlunoTask;
import alura.com.br.database.AgendaDatabase;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.entity.Aluno;

public class ListaAlunosAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;
    private final TelefoneDAO dao;

    public ListaAlunosAdapter(Context context) {
        this.context = context;
        dao = AgendaDatabase.getInstance(context).getTelefoneDAO();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = criaView(parent);
        Aluno alunoDevolvido = alunos.get(position);

        vincula(viewCriada, alunoDevolvido);

        return viewCriada;
    }

    private void vincula(View view, Aluno aluno) {
        TextView nomeAluno = view.findViewById(R.id.nomeAluno);
        nomeAluno.setText(aluno.getNome());
        final TextView telefoneAluno = view.findViewById(R.id.telefoneAluno);
        new BuscaPrimeiroTelefoneDoAlunoTask(dao, aluno.getId(), telefoneEncontrado ->
            telefoneAluno.setText(telefoneEncontrado.getNumero())
        ).execute();
    }

    private View criaView(ViewGroup parent) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_aluno, parent, false);
    }

    public void atualiza(List<Aluno> alunos) {
        clear();
        addAll(alunos);
    }

    private void clear() {
        alunos.clear();
    }

    private void addAll(List<Aluno> alunos) {
        this.alunos.addAll(alunos);
        notifyDataSetChanged();
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
