
import io.reactivex.rxjava3.core.Observable;
import javafx.util.Pair;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class RxJavaMerge {

    public static void main(String[] args) {
        Observable<String> alice = speak("To be, or not to be: that is the question", 110);

        Observable<String> bob = speak("Though this be madness, yet there is methods in't", 90);

        Observable<String> jane = speak("There are more things in Heaven and Earth," +
                " Horatio, than are dreamt of in your philosophy", 100);


        Observable.merge(
                alice.map(w -> "Alice: " + w),
                bob.map(w -> "Bob: " + w),
                jane.map(w -> "Jane: " + w)
        )
        .subscribe(System.out::println);


        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r).start();
    }

    public static Observable<String> speak(String quote, long millisPerChar) {
        String[] tokens = quote.replaceAll("[:,]", "").split(" ");
        Observable<String> words = Observable.fromArray(tokens);
        Observable<Long> absoluteDelay = words
                .map(String::length)
                .map(len -> len * millisPerChar)
                .scan((total, current) -> total + current);

        return words
                .zipWith(absoluteDelay.startWithItem(0L), Pair::new)
                .flatMap(pair -> Observable.just(pair.getKey())
                    .delay(pair.getValue(), TimeUnit.MILLISECONDS));
    }
}
