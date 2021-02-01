import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class RxJavaTimer {
    public static void main(String[] args) {
        // Timer operator is used when we want to do something after a span of time that we specify.
        // Here we specify to emit after 1 second
//        System.out.println("Emit list of integers after 5 seconds");
//        // Emit list of integers after 5 seconds
//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
//        Observable.timer(5, TimeUnit.SECONDS)
//                .flatMap(i -> {
//                            System.out.println("i: " + i);
//                            return Observable.just(list);
//                        }
//                )
//                .subscribe((List<Integer> i) -> System.out.println("Integer list: " + i));
//
//        System.out.println("Emit x, y, z after 5 seconds");
//        // Emit x, y, z after 5 seconds
//        Observable.timer(5, TimeUnit.SECONDS)
//                .flatMap(i -> Observable.just("x", "y", "z"))
//                .subscribe((String s) -> System.out.println("string: " + s));

//        // Emit each item 5 seconds after the previous item:
//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
//        // emits a sequential number every specified interval of time
//        System.out.println("Emit each item 5 seconds after the previous item:");
//        Observable
//                .interval(5, TimeUnit.SECONDS)
//                .map(i -> list.get(i.intValue()))  // i is the sequential number that is emitted every specified interval, we then query the index of the list
//                .take(list.size())
//                .subscribe(System.out::println);

//        //Emit everything after 5 seconds passed:
//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
//        System.out.println("Emit everything after 5 seconds passed:");
//        Observable.fromArray(list)
//                .delay(5, TimeUnit.SECONDS)
//                .subscribe(System.out::println);

//        // The zip operator combines from each source. if 1 source completes then the other may not emit at all
//        System.out.println("Emit 5 emissions every second on 1 stream, The other emits array of numbers");
//        Observable.zip(
//                Observable.interval(1000, TimeUnit.MILLISECONDS).take(5),
//                Observable.fromArray(1, 2, 3, 4, 5),
//                (aLong, integer) -> integer)
//                .subscribe(System.out::println);

//        Observable.interval(1000000/60, TimeUnit.MICROSECONDS)
//                .subscribe((Long i) -> System.out.println("i " + i));

//        Observable.just("x", "y", "z")
//                .delay(1, TimeUnit.SECONDS)
//                .subscribe((String i) -> System.out.println("i " + i));


//        Observable.timer(2, TimeUnit.SECONDS)
//                .flatMap(it -> {
//                    return Observable.create((ObservableOnSubscribe<String>) emitter -> {
//                        System.out.println("Create");
//                        emitter.onNext("MindOrks");
//                        emitter.onComplete();
//                    });
//                })
//                .subscribeOn(Schedulers.io())
//                .subscribe(i -> System.out.println("Received : " + i));

//        Flowable.range(0, 10)
//                .scan(0, (last_result, item) -> last_result + item)
//                .subscribe(i -> System.out.println("[Scan] Got " + i));

//        Flowable.range(0, 10)
//                .reduce(0, (last_result, item) -> last_result + item)
//                .subscribe(i -> System.out.println("[Reduce] Got " + i));

//        // The items are emitted after a time based on the word length.
//        // delay() can be written to timer() plus flatMap()
//        System.out.println("The items are emitted after a time based on the word length");
//        Observable.just("Lorem", "ipsum", "color", "sit",
//                "catdfdfdfd", "dogdffdfdfdf", "armadillo")
//                .delay(word -> {
//                    System.out.println("Word: " + word + ", Length: " + word.length());
//                    return Observable.timer(word.length(), TimeUnit.SECONDS);
//                })
//                .subscribe(System.out::println);


//        // flatmap does not preserve the original order of events.
//        System.out.println("flatmap does not preserve the original order of events.");
//        Observable.just("Lorem", "ipsum", "color", "sit",
//                "catdfdfdfd", "dogdffdfdfdf", "armadillo")
//                .flatMap(word -> {
//                            System.out.println("Word: " + word + ", Length: " + word.length());
//                            return Observable.timer(word.length(), TimeUnit.SECONDS)
//                                    .map(x -> word);
//                        }
//                )
//                .subscribe(System.out::println);


//        System.out.println("Events are delayed 10L by 10 seconds and 1L by 1 second");
//        Observable.just(10L, 1L)
//                .flatMap(x -> {
//                        System.out.println("x: " + x);
//                        return Observable.just(x).delay(x, TimeUnit.SECONDS);
//                })
//                .subscribe(System.out::println);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(r).start();
    }
}
