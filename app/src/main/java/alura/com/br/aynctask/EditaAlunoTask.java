package alura.com.br.aynctask;

import java.util.List;

import alura.com.br.database.dao.AlunoDAO;
import alura.com.br.database.dao.TelefoneDAO;
import alura.com.br.entity.Aluno;
import alura.com.br.entity.Telefone;
import alura.com.br.entity.TipoTelefone;

public class EditaAlunoTask extends BaseAlunoComTelefoneTask {
    private final AlunoDAO alunoDAO;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;
    private final List<Telefone> telefonesDoAluno;

    public EditaAlunoTask(AlunoDAO alunoDAO,
                          Aluno aluno,
                          Telefone telefoneFixo,
                          Telefone telefoneCelular,
                          TelefoneDAO telefoneDAO,
                          List<Telefone> telefonesDoAluno,
                          FinalizadaListener listener) {
        super(listener);
        this.alunoDAO = alunoDAO;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
        this.telefonesDoAluno = telefonesDoAluno;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        alunoDAO.edita(aluno);
        vinculaAlunoComTelefone(aluno.getId(), telefoneFixo, telefoneCelular);
        atualizaIdDoTelefone(telefoneFixo, telefoneCelular);
        telefoneDAO.atualiza(telefoneFixo, telefoneCelular);
        return null;
    }

    private void atualizaIdDoTelefone(Telefone telefoneFixo, Telefone telefoneCelular) {
        for (Telefone telefone : telefonesDoAluno) {
            if (telefone.getTipo() == TipoTelefone.FIXO) {
                telefoneFixo.setId(telefone.getId());
            } else {
                telefoneCelular.setId(telefone.getId());
            }
        }
    }
}
