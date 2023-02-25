import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClass {

    public int getLocalNumber() {
        return 14;
    }
    @Test
    public void testGetLocalNumber()
    {
        assertTrue(getLocalNumber() == 14, "При вызове метода getLocalNumber не пришло число 14");
    }
}
