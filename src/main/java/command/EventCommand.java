package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import java.time.LocalDateTime;
import task.EventTask;
import task.Task;

public class EventCommand extends Command {
  String taskName;
  LocalDateTime timing;

  @Override
  public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
    Task eventTask = new EventTask(this.taskName, this.timing);
    taskList.addTask(eventTask);
    return ui.showAdd(eventTask, taskList.getSize());
  }

  public EventCommand(String taskName, LocalDateTime timing) {
    super(CommandType.Event);
    this.taskName = taskName;
    this.timing = timing;
  }
}
