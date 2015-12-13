interface EditorEvents {
    void newsAdded(News n);
    void newsDeleted(News n);
    void newsEdited(News n);
    void followRequest(long s);
}