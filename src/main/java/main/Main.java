package main;

import controller.*;
import repository.IRepository;
import repository.Repository;
import service.CheckParticipantStrength;
import service.ICheckParticipantStrength;
import service.IService;
import service.Service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IRepository repository = new Repository();
        ICheckParticipantStrength validParticipant = new CheckParticipantStrength();
        IService service = new Service(repository,validParticipant);
        IController controller = new Controller(scanner,service,repository);
        ICandidateRegistration candidateRegistration = new CandidateRegistration(scanner,service,repository);
        IParticipantRegistration participantRegistration = new ParticipantRegistration(scanner,service,repository);
        IVotingProcess votingProcess = new VotingProcess(scanner,service,repository);

        candidateRegistration.addCandidate();
//        participantRegistration.registrationOfParticipant();
//        votingProcess.electionFromCandidates();


        controller.startInteraction();

        scanner.close();
    }
}