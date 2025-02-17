import java.util.List;

public class RuleProcessor
{
    public static <T, G> void process(List<T> inputList, List<Rule<T, G>> rules)
    {
        for(int i=0; i<inputList.size(); i++)
        {
            T input = inputList.get(i);

            rules.stream().map(rule -> rule.apply(input)).forEach(System.out::println);
        }
    }
}
