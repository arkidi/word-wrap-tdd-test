
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class WordWrapTddTest {

    @Test
    @DisplayName("TDD 환경 테스트")
    public void nothing() {

    }

    @Test
    @Disabled
    @DisplayName("Getting Stuck Test")
    public void should_wrap_stuck() {
        assertThat(wrap_stuck("word word", 4), is("word\nword"));
        assertThat(wrap_stuck("a dog", 5), is("a dog"));
        assertThat(wrap_stuck("a dog with a bone", 6), is("a dog\nwidth a\nbone"));
    }

    private String wrap_stuck(String s, int width) {
        //return s.replaceAll(" ", "\n");
        return s.length() > width ? s.replaceAll(" ", "\n") : s; //specific production code
    }

    private void assertWraps(String s, int width,  String expected) {
        assertThat(wrap(s, width), is(expected));
    }

    @Test
    @DisplayName("Getting Unstuck Test")
    public void should_wrap() {

//        assertWraps(null, 1, "");
//        assertWraps("", 1, "");
//        assertWraps("x", 1, "x");
//        assertWraps("xx", 1, "x\nx");
//        assertWraps("xxx", 1, "x\nx\nx");
//        assertWraps("x x", 1, "x\nx");
//        assertWraps("x xx", 3, "x\nxx");
//        assertWraps("four score and seven years ago our fathers brought forth upon this continent", 7,
//                "four\nscore\nand\nseven\nyears\nago our\nfathers\nbrought\nforth\nupon\nthis\ncontine\nnt");

        Assertions.assertAll(
                () -> assertWraps(null, 1, ""),
                () -> assertWraps("", 1, ""),
                () -> assertWraps("x", 1, "x"),
                () -> assertWraps("xx", 1, "x\nx"),
                () -> assertWraps("xxx", 1, "x\nx\nx"),
                () -> assertWraps("x x", 1, "x\nx"),
                () -> assertWraps("x xx", 3, "x\nxx"),
                () -> assertWraps("four score and seven years ago our fathers brought forth upon this continent", 7,
                "four\nscore\nand\nseven\nyears\nago our\nfathers\nbrought\nforth\nupon\nthis\ncontine\nnt")
        );
    }

    private String wrap(String s, int width) {
        if(s == null)
            return "";
        if(s.length() <= width)
            return s;
        else {
            int breakPoint = s.lastIndexOf(" ", width);
            if(breakPoint == -1)
                breakPoint = width;
            return s.substring(0, breakPoint) + "\n" + wrap(s.substring(breakPoint).trim(), width);
        }
    }
}
