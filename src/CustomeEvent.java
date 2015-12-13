interface CustomeEventListener {
    void newsAdded(News n);
    void newsDeleted(News n);
    void newsEdited(News n);
    void followRequest(String s);
}