package service;

public class CheckParticipantStrength implements ICheckParticipantStrength{
    private static final String ID_REGEX = "^\\d{4}$";

    @Override
    public boolean isIdValid(int id) {
        // Преобразуем int в String
       return String.valueOf(id).matches(ID_REGEX);
    }
}
