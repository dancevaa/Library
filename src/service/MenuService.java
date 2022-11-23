package service;

import dto.Action;
import java.io.IOException;

public interface MenuService{
    Action choosingAction();
    boolean isDoSomething(boolean doSomething);

}
