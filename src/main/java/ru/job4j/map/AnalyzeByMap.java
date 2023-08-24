package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double sum = 0D;
        int counter = 0;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                sum += subject.getScore();
                counter++;
            }
        }
        return sum / counter;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0D;
            int counter = 0;
            for (Subject subject : pupil.subjects()) {
                sum += subject.getScore();
                counter++;
            }
            Label label = new Label(pupil.name(), sum / counter);
            labels.add(label);
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        int countPupils = 0;
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> subjectsMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                subjectsMap.merge(subject.getName(), subject.getScore(), Integer::sum);
            }
            countPupils++;
        }
            for (String name : subjectsMap.keySet()) {
                Label label = new Label(name, subjectsMap.get(name) / countPupils);
                labels.add(label);
            }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
            List<Label> labels = new ArrayList<>();
            for (Pupil pupil : pupils) {
                double sum = 0D;
                for (Subject subject : pupil.subjects()) {
                    sum += subject.getScore();
            }
                labels.add(new Label(pupil.name(), sum));
            }
                labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
        }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> labels = new ArrayList<>();
        Map<String, Integer> subjectsMap = new LinkedHashMap<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {

                subjectsMap.merge(subject.getName(), subject.getScore(), Integer::sum);
            }
        }
        for (String name : subjectsMap.keySet()) {
            labels.add(new Label(name, subjectsMap.get(name)));
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }
}
