import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClassTest {

    @Test
    public void testGetClassString() {
        MainClass mainClass = new MainClass();
        assertTrue(mainClass.getClassString().matches(".*[Hh]ello.*"),
                "метод getClassString не вернул строку, в которой есть подстрока “hello” или “Hello");
    }
    @Test
    public void testGetClassNumber() {
        MainClass mainClass = new MainClass();
        assertTrue(mainClass.getClassNumber() > 45,
                "Метод getClassNumber вернул число не больше 45");
    }
}
