import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Do NOT modify.
 * <p>
 * This is the class with the main function
 */

public class DigitClassifier {
    private static int[] digits;
    /**
     * Runs the tests for DigitClassifier
     */
    public static void main(String[] args) {
        //Checking for correct number of arguments
        args = new String[] {"50", "0.01", "20", "src/train1.txt","src/test1.txt"}; // For testing purposes 
        if (args.length != 5) {
            System.out.println("usage: java DigitClassifier <noHiddenNode> <learningRate> <maxEpoch>" +
                    " <trainFile> <testFile>");
            System.exit(-1);
        }

        //Reading the training set
        ArrayList<Instance> trainingSet = getData(args[3]);

        //Create a random number
        Random random = new Random(0);

        //Reading the weights
        Double[][] hiddenWeights = new Double[Integer.parseInt(args[0])][];

        for (int i = 0; i < hiddenWeights.length; i++) {
            hiddenWeights[i] = new Double[trainingSet.get(0).attributes.size() + 1];
        }

        Double[][] outputWeights = new Double[trainingSet.get(0).classValues.size()][];
        for (int i = 0; i < outputWeights.length; i++) {
            outputWeights[i] = new Double[hiddenWeights.length + 1];
        }

        generateWeights(hiddenWeights, outputWeights, random);

        Double learningRate = Double.parseDouble(args[1]);

        if (learningRate > 1 || learningRate <= 0) {
            System.out.println("Incorrect value for learning rate\n");
            System.exit(-1);
        }

        NNImpl nn = new NNImpl(trainingSet, Integer.parseInt(args[0]), Double.parseDouble(args[1]), Integer.parseInt(args[2]), random,
                hiddenWeights, outputWeights);
        nn.train();

        //Reading the training set
        ArrayList<Instance> testSet = getData(args[4]);

        Integer[] outputs = new Integer[testSet.size()];


        int correct = 0;
        for (int i = 0; i < testSet.size(); i++) {
            //Getting output from network
            outputs[i] = nn.predict(testSet.get(i));
            int actual_idx = -1;
            for (int j = 0; j < testSet.get(i).classValues.size(); j++) {
                if (testSet.get(i).classValues.get(j) > 0.5)
                    actual_idx = j;
            }

            if (outputs[i] == actual_idx) {
                correct++;
            } else {
                System.out.println(i + "th instance got misclassified, expected: " + digits[actual_idx]
                        + ". But actual: " + digits[outputs[i]]);
            }
        }

        System.out.println("Total instances: " + testSet.size());
        System.out.println("Correctly classified: " + correct);

    }

    // Reads a file and gets the list of instances
    private static ArrayList<Instance> getData(String file) {
        ArrayList<Instance> data = new ArrayList<>();
        BufferedReader in;
        int attributeCount = 0;

        try {
            in = new BufferedReader(new FileReader(file));
            while (in.ready()) {
                String line = in.readLine();
                String prefix = line.substring(0, 2);
                switch (prefix) {
                    case "//":
                        break;
                    case "##":
                        attributeCount = Integer.parseInt(line.substring(2));
                        break;
                    case "**":
                        digits = Arrays.stream(line.substring(2).split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
                        break;
                    default:
                        String[] vals = line.split(" ");
                        Instance inst = new Instance();
                        for (int i = 0; i < attributeCount; i++)
                            inst.attributes.add(Double.parseDouble(vals[i]));
                        for (int i = attributeCount; i < vals.length; i++)
                            inst.classValues.add(Integer.parseInt(vals[i]));
                        data.add(inst);
                        break;
                }

            }
            in.close();

        } catch (Exception e) {
            System.out.println("Could not read instances: " + e);
        }

        return data;
    }

    // Gets weights randomly
    private static void generateWeights(Double[][] hiddenWeights, Double[][] outputWeights, Random r) {

        for (int i = 0; i < hiddenWeights.length; i++) {
            for (int j = 0; j < hiddenWeights[i].length; j++) {
                hiddenWeights[i][j] = r.nextGaussian() * 0.01;
            }
        }

        for (int i = 0; i < outputWeights.length; i++) {
            for (int j = 0; j < outputWeights[i].length; j++) {
                outputWeights[i][j] = r.nextGaussian() * 0.01;
            }
        }

    }
}
