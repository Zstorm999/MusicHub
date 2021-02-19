package musichub.business;

public interface IHasAnID {

    void setID(int newID) throws IDAlreadySetException;
    int getID();
}
