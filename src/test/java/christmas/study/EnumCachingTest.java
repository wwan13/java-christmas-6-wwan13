package christmas.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Enum을 caching하는 법을 익히기 위한 학습테스트 이다.")
public class EnumCachingTest {

    enum TestEnum {
        ONE(1), TWO(2);

        final int value;

        TestEnum(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        static final Map<Integer, TestEnum> ENUMS = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(TestEnum::getValue, Function.identity())));

        static TestEnum of(int value) {
            return ENUMS.get(value);
        }
    }

    @DisplayName("캐싱을 통해 Enum을 찾을 때 마다 발생하는 순회를 방지할 수 있다.")
    @Test
    void objectsAreSameAfterCaching() {
        // given
        int value = 1;

        // when
        TestEnum testEnum = TestEnum.of(value);

        // then
        assertThat(testEnum).isEqualTo(TestEnum.ONE);
    }
}
