package com.ze.aurora.behaviour;

import java.util.ArrayList;

/**
 * 观察者
 *
 * 多个观察者监测天气的变化，一旦发生变化，观察者将会收到通知。
 * @author Aurora
 * @date 2021/11/14 21:28
 */
public class Observer {
    interface Subject {
        void addObserver(Observable observer);
        void deleteObserver(Observable observer);
        void notifyAllObservers();
    }

    interface Observable {
        void update();
    }

    class WeatherCenter implements Subject {
        ArrayList<Observable> observers;

        WeatherCenter() {
            observers = new ArrayList<>();
        }

        @Override
        public void addObserver(Observable observer) {
            observers.add(observer);
        }

        @Override
        public void deleteObserver(Observable observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyAllObservers() {
            for (Observable observer: observers) {
                observer.update();
            }
        }
    }

    class WeatherObserver implements Observable{
        public void update() {
            System.out.println(this.toString() + " find that weather has changed");
        }
    }

    public void test() {
        WeatherCenter sub = new WeatherCenter();
        sub.addObserver(new WeatherObserver());
        sub.addObserver(new WeatherObserver());

        sub.notifyAllObservers();
    }
}
