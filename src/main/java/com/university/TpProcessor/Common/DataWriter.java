package com.university.TpProcessor.Common;

import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;

import java.io.BufferedWriter;
import java.io.IOException;

public interface DataWriter {
    void write(University university, CriteriaProcessor criteriaProcessor, BufferedWriter writer) throws IOException;
}