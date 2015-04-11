package utilities;

/**
 * Basic sequencer for enumerating labels.
 *
 * @author krc
 */
public class Sequencer {

    protected static int Sequence = 0;

    public static int nextInt() {
        return ++Sequence;
    }
}
