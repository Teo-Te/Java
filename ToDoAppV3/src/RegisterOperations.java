public interface RegisterOperations {
    public Users getUser(String username);
    public void registerUser(String username, String password, String name);
    public void validateUsername(String username);
    public void validatePassword(String password);
    public void validateName(String name);
}
