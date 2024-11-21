package com.university.TpProcessor.Part3;
import com.university.TpProcessor.Common.DataCreator;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.entity.Grading.criteria.Criterion;

import java.util.Arrays;
import java.util.List;

public class Part3DataCreator implements DataCreator {
    @Override
    public void create(String parts, University university, CriteriaProcessor criteriaProcessor) {
        String[] params = parts.split(",");
        String subjectName = params[0];
        String criteriaType = params[1];
        double criteriaValue = Double.parseDouble(params[2]);
        String[] evaluationNames = Arrays.copyOfRange(params, 3, params.length);
         Criterion criterion = new Criterion(subjectName, criteriaType, criteriaValue, List.of(evaluationNames));
        criteriaProcessor.addCriteria(criterion);
    }
}