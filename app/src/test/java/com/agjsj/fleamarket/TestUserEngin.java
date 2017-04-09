package com.agjsj.fleamarket;

import com.agjsj.fleamarket.bean.UserInfo;
import com.agjsj.fleamarket.engine.TestEngin;
import com.agjsj.fleamarket.engine.UserEngine;
import com.agjsj.fleamarket.engine.impl.TestEnginImpl;
import com.agjsj.fleamarket.engine.impl.UserEngineImpl;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MyPC on 2016/12/24.
 */

public class TestUserEngin {

    @Test
    public void testObjectDes(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("dddddde");
        userInfo.setUserpassword("123456");
        TestEngin testEngin = new TestEnginImpl();
        testEngin.testClientDes(userInfo);
    }

    @Test
    public void testMessageDes(){
        String s = "{\"header\":{\"messengereid\":\"20170101115530658654\",\"timestamp\":\"20170101115530\",\"transactiontype\":\"10003\",\"digest\":\"1d32a1ac1681e57b22397e8ff64a804b\",\"ipaddress\":\"192.168.3.3\\n\",\"source\":\"Client\",\"compress\":\"DES\"},\"body\":\"HVenWhph9LeeMNDGrrFKPgXVdFB7fyRotEfesmX+IHzjz7y5su0VqVlaiFtwn6isC0pKXRP1iwL7e82A1pAuuW2fSvzGScii\"}";
        TestEngin testEngin = new TestEnginImpl();
        testEngin.testServerDes(s);

    }

    @Test
    public void testListsDes(){
        List<UserInfo> lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUsername("dddddde");
            userInfo.setUserpassword("123456");
            lists.add(userInfo);
        }
        TestEngin testEngin = new TestEnginImpl();
        testEngin.testClientDes(lists);
    }

    @Test
    public void testListMessageDes(){
        String s = "{\"header\":{\"messengereid\":\"20170101124843377339\",\"timestamp\":\"20170101124843\",\"transactiontype\":\"10003\",\"digest\":\"51b74b14e86790326e185d4dd0c147eb\",\"ipaddress\":\"192.168.3.3\\n\",\"source\":\"Client\",\"compress\":\"DES\"},\"body\":\"HVenWhph9Lc5J+b9tJZYqwG7YoaXJd+E+QcqJGgN/rsu5gZja9t4QKmfokqhk5I5DAVI+ganSMaCiPwZSkAg+nz0pRQ04Q5hBdV0UHt/JGi0R96yZf4gfOPPvLmy7RWpWVqIW3CfqKwLSkpdE/WLAvt7zYDWkC65OV/g1UZ9G1CIkg9aCDXpgF7dS80kX78RP6paMWpTElDI/tYyIcDf6hC0byUjhQIs4yqgiJNoPIoT1gKQKXuMDXEkhGRdmVAcVPWBoRSWsR6vLHRdveAY4gCdDuSpKNU53bioZimGqnXsyHd7PpZ1dqn8dAfGl0SrtbwNMKM9hqcw2rV6HtspbSYR9Wekk7FqDvV44gMNEoysW7PADl2qjBjf7dlaKvlqNPtUBSYlW4I\\u003d\"}";
        TestEngin testEngin = new TestEnginImpl();
        testEngin.testServerDes(s);

    }


}
