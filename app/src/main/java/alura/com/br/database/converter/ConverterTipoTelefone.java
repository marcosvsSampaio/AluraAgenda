package alura.com.br.database.converter;

import androidx.room.TypeConverter;

import alura.com.br.entity.TipoTelefone;

public class ConverterTipoTelefone {
    @TypeConverter
    public String toString(TipoTelefone tipo) {
        return tipo.name();
    }

    @TypeConverter
    public TipoTelefone toTipoTelefone(String valor) {
        if (valor != null) {
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
