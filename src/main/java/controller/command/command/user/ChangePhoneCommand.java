package controller.command.command.user;

import controller.command.ActionCommand;
import controller.command.util.Constant;
import controller.content.SessionRequestContent;
import controller.util.ActionPageContainer;
import controller.util.URLAction;
import domain.dto.UserDTO;
import domain.entity.User;
import resource.ConfigurationManager;
import resource.MessageManager;
import service.exception.ServiceException;
import service.factory.ServiceFactory;
import service.UserService;
import service.exception.ExistEmptyFieldException;

public class ChangePhoneCommand implements ActionCommand {

    @Override
    public ActionPageContainer execute(SessionRequestContent sessionRequestContent) {
        ActionPageContainer actionPageContainer = null;
        String page = null;

        UserDTO userDTO = createUser(sessionRequestContent);

        User user = (User) sessionRequestContent.getSessionAttribute(Constant.USER);

        UserService userService = ServiceFactory.getInstance().getUserService();

        try{
            userService.changePhone(userDTO);

            user.setPhone(userDTO.getPhone());

            sessionRequestContent.add2SessionAttributes(Constant.USER, user);
            page = ConfigurationManager.getProperty("path.page.contacts");
            actionPageContainer = new ActionPageContainer(page, URLAction.REDIRECT);
        } catch (ExistEmptyFieldException e) {
            sessionRequestContent.add2RequestAttributes(Constant.UPDATE_PHONE_ERROR,
                    MessageManager.getProperty("message.emptyfield"));
        } catch (ServiceException e){
            e.printStackTrace();
        }

        if(page == null){
            page = ConfigurationManager.getProperty("path.page.contacts");
            actionPageContainer = new ActionPageContainer(page, URLAction.FORWARD);
        }

        return actionPageContainer;
    }

    private UserDTO createUser(SessionRequestContent sessionRequestContent){
        UserDTO userDTO = new UserDTO();

        userDTO.setLogin(((User) sessionRequestContent.getSessionAttribute(Constant.USER)).getLogin());
        userDTO.setPhone(sessionRequestContent.getRequestParameter(Constant.PHONE));
        return userDTO;
    }
}
