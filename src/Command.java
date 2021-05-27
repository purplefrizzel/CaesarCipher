public abstract class Command {

  private String command;
  private String[] args;

  public Command(String command, String[] args) {
    this.command = command;
    this.args = args;
  }
}