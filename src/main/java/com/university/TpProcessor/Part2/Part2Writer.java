package com.university.TpProcessor.Part2;

import com.university.TpProcessor.Common.DataWriter;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;
import com.university.CSVUtils.Sorter;
import com.university.entity.Grading.Evaluation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Part2Writer implements DataWriter {
    @Override
    public void write(University university, CriteriaProcessor criteriaProcessor, BufferedWriter writer) throws IOException {
        writer.write("Subject_Name,Evaluation_Name,Student_Name,Grade");
        writer.newLine();
        List<Evaluation> evaluations = university.getEvaluations();
        List<Evaluation> sortedEvaluations = Sorter.sortEvaluations(evaluations); // Orden correcto
        for (Evaluation eval : sortedEvaluations) {
            writer.write(eval.getSubjectName() + "," +
                    eval.getName() + "," +
                    eval.getStudentName() + "," +
                    eval.getGrade());
            writer.newLine();
        }
    }
}
