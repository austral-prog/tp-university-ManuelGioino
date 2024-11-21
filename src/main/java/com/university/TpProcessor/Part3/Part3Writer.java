package com.university.TpProcessor.Part3;

import com.university.TpProcessor.Common.DataWriter;
import com.university.University.University;
import com.university.entity.Grading.criteria.CriteriaProcessor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Part3Writer implements DataWriter {
    @Override
    public void write(University university, CriteriaProcessor criteriaProcessor, BufferedWriter writer) throws IOException {
        writer.write("Student,Subject,Status\n");

        // Obtener los resultados, eliminar duplicados y ordenar alfabéticamente
        List<String> results = criteriaProcessor.evaluateStudents(university).stream()
                .distinct()  // Eliminar duplicados
                .sorted()    // Ordenar alfabéticamente
                .collect(Collectors.toList());

        for (String result : results) {
            writer.write(result);
            writer.write("\n");
        }
    }
}
