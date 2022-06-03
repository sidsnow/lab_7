package src.serverstuff.commands.woargs;

import src.serverstuff.commands.CommonCommand;

public class Help implements CommonCommand {
    /**
     *
     */
    /** main execute
     *
     */
    public String execute() {
       return "- help : вывести справку по доступным командам\n" +
                "- info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "- show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "- insert <id> <new movie edit> : добавить новый элемент с заданным ключом\n" +
                "- update <id> <movie edit> : обновить значение элемента коллекции, id которого равен заданному\n" +
                "- remove_key <id> : удалить элемент из коллекции по его ключу\n" +
                "- clear : очистить коллекцию\n" +
                "- execute_script <file_name> : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "- exit : завершить программу\n" +
                //"- remove_greater <movie_name> : удалить из коллекции все элементы, превышающие заданный\n" +
                "- remove_greater_key <id> : удалить из коллекции все элементы, ключ которых превышает заданный\n" +
                "- remove_lower_key <id> : удалить из коллекции все элементы, ключ которых меньше, чем заданный\n" +
                "- group_counting_by_director : сгруппировать элементы коллекции по значению поля director, вывести количество элементов в каждой группе\n" +
                "- count_by_genre <genre> (ADVENTURE, THRILLER, COMEDY) : вывести количество элементов, значение поля genre которых равно заданному\n" +
                "- print_descending : вывести элементы коллекции в порядке убывания\n";
    }

    /**
     *
     * @return
     */
    public String getName() {
        return "help";
    }

}
