package com.university.TpProcessor.Common;

import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;

public interface DataCreator {
    void create(String parts, University university, CriteriaProcessor criteriaProcessor);
}