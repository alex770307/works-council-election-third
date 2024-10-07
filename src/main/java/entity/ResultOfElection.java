package entity;

import java.util.Objects;

public class ResultOfElection {
    private Candidate candidateName;
    private int numberOfVotes;

    public ResultOfElection(Candidate candidateName, int numberOfVotes) {
        this.candidateName = candidateName;
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public String toString() {
        return "ResultOfElection{" +
                "candidateName=" + candidateName +
                ", numberOfVotes=" + numberOfVotes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultOfElection that = (ResultOfElection) o;
        return numberOfVotes == that.numberOfVotes
                && Objects.equals(candidateName, that.candidateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateName, numberOfVotes);
    }

    public Candidate getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(Candidate candidateName) {
        this.candidateName = candidateName;
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }
}