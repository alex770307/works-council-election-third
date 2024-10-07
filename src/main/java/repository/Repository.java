package repository;

import entity.Candidate;
import entity.Participant;
import entity.ResultOfElection;

import java.util.HashMap;
import java.util.Map;

public class Repository implements IRepository {

    private Map<Integer, Participant> participants;
    private Map<String, ResultOfElection> results;
    private Map<Integer, Candidate> candidates;

    public Repository() {
        participants = new HashMap<>();
        results = new HashMap<>();
        candidates = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Repository{" +
                "participants=" + participants +
                ", results=" + results +
                ", candidates=" + candidates +
                '}';
    }

    @Override
    public void registrationParticipant(Participant participant) {
        if (participants.containsKey(participant.getId())) {
            System.out.println("Участник с таким ID уже зарегистрирован.");
            return; // Просто выходим из метода
        }
        participants.put(participant.getId(), participant);
    }

    @Override
    public Participant findById(int id) {
        return participants.get(id);
    }

    @Override
    public ResultOfElection findResultByCandidate(Candidate candidate) {
        return results.get(candidate.getId());
    }

    @Override
    public void addResultOfElection(ResultOfElection result) {
        ResultOfElection existingResult = results.get(result.getCandidateName().getName());
        if (existingResult != null) {
            // Обновляем количество голосов
            existingResult.setNumberOfVotes(existingResult.getNumberOfVotes() + result.getNumberOfVotes());
        } else {
            // Если результата нет, добавляем новый
            results.put(result.getCandidateName().getName(),  result);
        }
    }

    @Override
    public void addCandidate(Candidate candidateClass) {
        candidates.put(candidateClass.getId(),candidateClass);
    }

    @Override
    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }


    public Map<Integer, Participant> getParticipants() {
        return participants;
    }

    public Map<String, ResultOfElection> getResults() {
        return results;
    }

    public Map<Integer, Candidate> getCandidates() {
        return candidates;
    }
}