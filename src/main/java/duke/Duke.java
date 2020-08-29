/**
 * Duke is a chatbot that allows users to create, delete and complete tasks, which include todos,\
 * events, and deadlines
 */
package duke;

import command.Command;
import exception.DukeException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for the Duke class
     * @param filePath path of file to be imported
     */
    public Duke(String filePath) {

        ui = new Ui();
        storage = new Storage(filePath);

        // Retrieve data from the file to the taskList
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * This method runs the chatbot, accepting user input and adding it to a task list.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
