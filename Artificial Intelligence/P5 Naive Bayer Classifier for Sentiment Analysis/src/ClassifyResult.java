import java.util.Map;

/**
 * Classification result of a movie review instance.
 * <p>
 * DO NOT MODIFY.
 */
public class ClassifyResult {
    /**
     * Positive or negative
     */
    public Label label;
    /**
     * The log probability for each label. Does not have to be normalized.
     */
    public Map<Label, Double> logProbPerLabel;

}
