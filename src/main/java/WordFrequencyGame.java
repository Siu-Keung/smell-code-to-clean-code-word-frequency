import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {

            try {
                List<String> inputList = getWords(inputStr);

                //get the map for the next step of sizing the same word
                Map<String, List<String>> map = getListMap(inputList);

                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }

                list.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : list) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<String> getWords(String input){
        String[] arr = input.split("\\s+");
        return Arrays.asList(arr);
    }

    private Map<String, List<String>> getListMap(List<String> inputList) {
        Map<String, List<String>> map = new HashMap<>();
        for (String input : inputList) {
            if (!map.containsKey(input)) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input, arr);
            } else {
                map.get(input).add(input);
            }
        }
        return map;
    }
}
