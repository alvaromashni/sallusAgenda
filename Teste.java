import java.time.LocalDate;
import java.time.LocalDateTime;

public class Teste {
    public static void main(String[] args) {
        LocalDate dia = LocalDate.of(2025, 5, 5);
        LocalDateTime inicio = dia.atStartOfDay();
        System.out.println(inicio);
    }

}
