package FactoryElements;

//abstract factor for all elements

/**
 * @author David Lindeman
 * The interface for our abstract factory pattern
 */
public abstract class AbstractElementFactory<T> {
    abstract T create(InputObject input);
}