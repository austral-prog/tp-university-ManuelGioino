package com.university.TpProcessor.Part2;

import com.university.Factory.EvaluationCreator;
import com.university.TpProcessor.Common.DataCreator;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.entity.Grading.Evaluation;

public class Part2DataCreator implements DataCreator {
    @Override
    public void create(String parts, University university, CriteriaProcessor criteriaProcessor) {
        EvaluationCreator evaluationCreator = new EvaluationCreator(university.getEvaluations());
        Evaluation evaluation = evaluationCreator.getOrCreate(parts, university.getEvaluations(), university);
        university.addEvaluation(evaluation);
    }
}
