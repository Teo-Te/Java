public interface UserOperations {
    public Users getUser(String username, String password);
    public Users getUser(String username);
    public void registerUser(String username, String password, String name);
}
