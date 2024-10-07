package service;

import entity.Candidate;
import entity.Participant;
import entity.ResultOfElection;
import repository.IRepository;

public class Service implements IService {
    private IRepository repository;
    private ICheckParticipantStrength validParticipant;


    public Service(IRepository repository, ICheckParticipantStrength validParticipant) {
        this.repository = repository;
        this.validParticipant = validParticipant;
    }

    @Override
    public boolean registrationParticipant(int id, String name) {
        Participant participant = new Participant(id, name);
        if (validParticipant.isIdValid(id)) {
            repository.registrationParticipant(participant);
            return true;
        }
        return false;
    }

    @Override
    public boolean isParticipantRegistered(int id) {
        // Проверяем, существует ли участник с данным ID в репозитории
        return repository.findById(id) != null;
        // return false;
    }

    @Override
    public boolean electionFromCandidates(Candidate candidate, int numberOfVotes) {
        if (candidate != null) {

            ResultOfElection existingResult = repository.findResultByCandidate(candidate);
            if (existingResult != null) {
                // Увеличиваем количество голосов на 1
                int updatedVotes = existingResult.getNumberOfVotes() + 1;
                existingResult = new ResultOfElection(candidate, updatedVotes);
                repository.addResultOfElection(existingResult); // добавляем обновленный результат
            } else {
                // Если результата еще нет, создаем новый
                ResultOfElection result = new ResultOfElection(candidate, numberOfVotes);
                repository.addResultOfElection(result);
            }
            return true;
        }
        return false;
    }
}