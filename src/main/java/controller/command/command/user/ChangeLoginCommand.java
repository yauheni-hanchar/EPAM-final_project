package controller.command.command.user;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import service.exception.LoginExistException;
import pojo.dto.UserDTO;
import pojo.entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangeLoginCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute(Constant.USER);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changeLogin(userDTO);

            user.setLogin(userDTO.getLogin());

            sessionRequestContent.add2SessionAttributes(Constant.USER, user);
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_LOGIN_ERROR,
                    MessageManager.getProperty("message.emptyfield"));
        } catch (LoginExistException e){
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_LOGIN_ERROR,
                    MessageManager.getProperty("message.loginexist"));
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.account");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setEmail(((User) sessionRequestContent.getSessionAttribute(Constant.USER)).getEmail());
        userDTO.setLogin(sessionRequestContent.getRequestParameter(Constant.LOGIN));

        return userDTO;
    }

}
