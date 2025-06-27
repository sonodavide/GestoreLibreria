package com.sonodavide.gestorelibreria.commands;

public class ConcreteCommandHandler implements CommandHandler {
    @Override
    public void execute(Command command) {
        command.execute();
    }
}
