package alura.com.br.database.converter;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class ConverterCalendar {
    @TypeConverter
    public Long toLong(Calendar valor) {
        return valor.getTimeInMillis();
    }

    @TypeConverter
    public Calendar toCalendar(Long valor) {
        Calendar momentoAtual = Calendar.getInstance();
        if (valor != null) {
            momentoAtual.setTimeInMillis(valor);
        }
        return momentoAtual;
    }
}
