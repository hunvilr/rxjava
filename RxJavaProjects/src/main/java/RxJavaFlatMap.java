import io.reactivex.rxjava3.core.Observable;
import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class RxJavaFlatMap {
    public static void main(String[] args) {

        // Emits the dat of the week,
        // SUNDAY emits 5 numbers every 90 msec
        // MONDAY emits 5 numbers every 65 msec
        // Monday may appear before Sunday
        Observable.just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
                .flatMap(RxJavaFlatMap::loadRecordsFor)
                .subscribe(System.out::println);

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

    private static @io.reactivex.rxjava3.annotations.NonNull Observable<String> loadRecordsFor(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case SUNDAY:
                return Observable.interval(90, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> "Sun-" + i);
            case MONDAY:
                return Observable.interval(65, TimeUnit.MILLISECONDS)
                        .take(5)
                        .map(i -> "Mon-" + i);
        }

        return null;
    }
}