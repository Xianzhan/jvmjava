package test.com.github.xianzhan.jvmjava.java.cmd;

import com.github.xianzhan.jvmjava.java.cmd.Args;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Args test
 *
 * @author xianzhan
 * @since 2020-04-06
 */
public class ArgsTest {

    @Test
    public void testIsVersion() {
        Args args = Args.parseArgs("-version");
        Assertions.assertTrue(args.isVersion());
    }

    @Test
    public void testIsHelp() {
        Args args = Args.parseArgs("-help");
        Assertions.assertTrue(args.isHelp());
    }

    @Test
    public void testToString() {
        Args args = Args.parseArgs("-cp", "--version");
        System.out.println(args);
    }
}
