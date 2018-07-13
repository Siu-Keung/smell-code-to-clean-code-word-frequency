import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {
        List<String> wordsList = getWords(inputStr);
        List<Word> resultModels = countFrequency(wordsList);
        return formatOutputStr(resultModels);
    }

    private String formatOutputStr(List<Word> list) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : list) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<String> getWords(String input){
        String[] arr = input.split("\\s+");
        return Arrays.asList(arr);
    }

    private List<Word> countFrequency(List<String> inputList){
        Map<String, Long> resultMap = inputList.stream().sorted()
                .collect(Collectors.groupingBy(String :: toString, Collectors.counting()));
        Set<Map.Entry<String, Long>> entries = resultMap.entrySet();
        List<Word> resultList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : entries) {
            resultList.add(new Word(entry.getKey(), entry.getValue().intValue()));
        }
        resultList = resultList.stream()
                .sorted((x, y) -> y.getWordCount() - x.getWordCount())
                .collect(Collectors.toList());
        return resultList;
    }

}
