import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Teste {
    public static void main(String[] args) {
        LocalDate dia = LocalDate.of(2025, 5, 5);
        LocalDateTime inicio = dia.atStartOfDay();
        System.out.println(inicio);
        LocalDate day = LocalDate.of(2025, 10,1 );
        day.lengthOfYear();
        System.out.println(day.lengthOfMonth());
        Map<LocalDate, Boolean> map = new HashMap<>();
        for (int i = 1; i <= day.lengthOfMonth(); i++) {
            LocalDate currentDate = day.withDayOfMonth(i);
            boolean verdadeiro = true;
            map.put(currentDate, verdadeiro);
            
            System.out.println(map);
            
        }
        
    }

}
