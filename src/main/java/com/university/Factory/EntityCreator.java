package com.university.Factory;

import com.university.University.University;

import java.util.List;

public interface EntityCreator <T>{
    T getOrCreate(String parts, List<T> entities, University university);
}
