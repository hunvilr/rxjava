import io.reactivex.rxjava3.core.Single;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxJavaDelay {
    public static void main(String[] args) {
        System.out.println("Before operation");
        getBlockingSuperVillain()
                .subscribe(value -> System.out.println("Operation completed: " + value));
    }

    private static Single<Character> getBlockingSuperVillain() {
        return Single.just(new Character("Frog-Man",
                Arrays.asList("super strength", "leaping", "mega agility", "French"),
                false))
                .delay(1, TimeUnit.SECONDS)
                .doAfterTerminate(() -> System.out.println("Operation done"));
    }
}

class Character {
    String name;
    List<String> strengths;

    public Character(String name, List<String> strengths, boolean fail) {
        this.name = name;
        this.strengths = strengths;
    }
}