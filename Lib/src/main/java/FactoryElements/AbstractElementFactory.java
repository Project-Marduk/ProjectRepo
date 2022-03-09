package FactoryElements;

//abstract factor for all elements

/**
 * @author David Lindeman
 * The interface for our abstract factory pattern
 */
public interface AbstractElementFactory<T> {
    T create(String elementType);
}