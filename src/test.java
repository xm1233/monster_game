import javax.xml.stream.events.Characters;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.lang.String;

public class test {
    public static int dfs(int a){
        if(a==1)return 1;
        if(a==2)return 2;
        return dfs(a-1)+dfs(a-2);
    }
    public static void main(String[] args){
        System.out.println(dfs(7));
    }
}
