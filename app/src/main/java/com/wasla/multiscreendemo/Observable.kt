package com.wasla.multiscreendemo

class Observable {

    companion object {
        var observers = ArrayList<Observer>()

        fun addObserver(observer: Observer) {
            observers.add(observer)
        }

        fun updateObserver(string: String) {
            for (observer in observers) {
                observer.update(string)
            }
        }

    }
}