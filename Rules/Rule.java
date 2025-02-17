import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Rule <T, G>
{
    private final Predicate<T> predicate;
    private final Function<T, G> function;

    public Rule(Predicate<T> predicate, Function<T, G> function)
    {
        this.predicate = predicate;
        this.function = function;
    }

    public Optional<G> apply(T input)
    {
        if(predicate.test(input))
           return Optional.of(function.apply(input));
        else return Optional.empty();
    }

}
