package repository;

import entity.Candidate;
import entity.Participant;
import entity.ResultOfElection;

public interface IRepository {
    void registrationParticipant(Participant participant);
    Participant findById(int id);
    ResultOfElection findResultByCandidate(Candidate candidate);
    void addResultOfElection(ResultOfElection result);
    void addCandidate(Candidate candidateClass);
    Candidate findCandidateById(int id);
}