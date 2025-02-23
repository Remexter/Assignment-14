import java.util.Scanner;

public class Days {
    public static void main(String[] args) {
        System.out.println(dayOfWeek(4));
    }    
public static String dayOfWeek(int d) {
    String[] dow = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
    return dow[d];
}


}
