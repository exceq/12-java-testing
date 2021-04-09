import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class IntegerDecodeTest {

    @ParameterizedTest
    @EmptySource
    void decodeShouldThrowExceptionForEmptyString(String input) {
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234q", "letters", "0xABCDEFG", "08"})
    void wrongValueThrowsNumberFormatException(String input) {
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode(input));
    }

    @Test
    void signAtWrongPositionThrowsNumberFormatException(){
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode("1-332"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-2147483649","2147483648"})
        //Integer.MAX_VALUE + 1 and Integer.MIN_VALUE - 1;
    void decodeShouldThrowsNumberFormatExceptionForNumbersOutOfBoundsOfInteger(String input) {
        Assertions.assertThrows(NumberFormatException.class, () -> Integer.decode(input));
    }

    @ParameterizedTest
    @CsvSource({"1, 1", "-1, -1", "-0, 0", "+0, 0","-2147483648, -2147483648", "2147483647, 2147483647"})
    void decodeShouldCorrectlyDecodingDecimalNumbers(String input, int expected) {
        Assertions.assertEquals(expected, Integer.decode(input));
    }

    @ParameterizedTest
    @CsvSource({"0xFF, 255", "0XFF, 255","-0xFF, -255", "-0XFF, -255"})
    void decodeShouldCorrectlyDecodingHexNumbers(String input, int expected) {
        Assertions.assertEquals(expected, Integer.decode(input));
    }

    @ParameterizedTest
    @CsvSource({"0377, 255","-0377, -255"})
    void decodeShouldCorrectlyDecodingOctNumbers(String input, int expected) {
        Assertions.assertEquals(expected, Integer.decode(input));
    }
}
