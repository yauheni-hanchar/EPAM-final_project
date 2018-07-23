package controller.command.client;

import controller.command.ActionCommand;
import controller.command.command.LoginCommand;
import controller.command.command.LogoutCommand;
import controller.command.command.SignupCommand;
import controller.command.command.user.*;

public enum CommandEnum {
    LOGIN{
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT{
        {
            this.command = new LogoutCommand();
        }
    },
    SIGNUP{
        {
            this.command = new SignupCommand();
        }
    },
    CHANGE_LOGIN{
        {
            this.command = new ChangeLoginCommand();
        }
    },
    CHANGE_PASSWORD{
        {
            this.command = new ChangePasswordCommand();
        }
    },
    CHANGE_NAME_SURNAME{
        {
            this.command = new ChangeNameSurnameCommand();
        }
    },
    CHANGE_EMAIL{
        {
            this.command = new ChangeEmailCommand();
        }
    },
    CHANGE_PHONE{
        {
            this.command = new ChangePhoneCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand(){
        return command;
    }
}