package ua.netcracker.model.filtering;

import ua.netcracker.model.entity.Candidate;

import java.util.ArrayList;

public interface Filter {
    ArrayList<Candidate> filter(ArrayList<Candidate> list);
}