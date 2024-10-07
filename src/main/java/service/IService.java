package service;
import entity.Candidate;

public interface IService {
    boolean registrationParticipant(int id, String name);
    boolean isParticipantRegistered(int id);
    boolean electionFromCandidates(Candidate candidate, int numberOfVotes);
}