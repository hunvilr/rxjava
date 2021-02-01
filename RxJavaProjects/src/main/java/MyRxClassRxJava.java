//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class MyRxClassRxJava {

    public static void main(String[] args) {

        Observable<String> createObserver = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("Hello World");
                emitter.onComplete();
            }
        });

        Consumer<String> onNextConsumer = new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                System.out.println("MySubscriber onNext(): "+ s);
            }
        };

        Consumer<Throwable> onErrorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                System.out.println("OnError");
            }
        };

        Action onCompleteAction = new Action() {
            @Override
            public void run() throws Throwable {
                System.out.println("Subscriber completed");
            }
        };

        Observer<String> myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("MyObserver onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("MyObserver onNext(): "+ s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("OnError");
            }

            @Override
            public void onComplete() {
                System.out.println("Observer completed");
            }
        };

        createObserver.subscribe(onNextConsumer, onErrorConsumer, onCompleteAction);
        createObserver.subscribe(myObserver);

    }

//    compile 'io.reactivex:rxjava:1.3.0' Below code will compile with older rxjava
//    public static void main(String[] args) {
//
//        Observable<String> createObserver = Observable.unsafeCreate(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//
//                subscriber.onNext("Hello World");
//                subscriber.onCompleted();
//            }
//        });
//
//        Subscriber<String> mySubscriber = new Subscriber<String>() {
//            @Override
//            public void onNext(String s) {
//                System.out.println("MySubscriber onNext(): "+ s);
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Subscriber completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("OnError");
//            }
//        };
//
//
//
//        Observer<String> myObserver = new Observer<String>() {
//            @Override
//            public void onNext(String s) {
//                System.out.println("MyObserver onNext(): "+ s);
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Observer completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//            }
//        };
//
//        createObserver.subscribe(mySubscriber);
//        createObserver.subscribe(myObserver);
//
//    }
}