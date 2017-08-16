package org.yava.dao;

public interface Dao<E> {

    E create( E entity );

    E read( Long id );

    E update( E entity );

    E delete( Long id );

}
