package blacklinden.com.cannabisgrowthsimulator.serv;

public class Constants {


    public interface ACTION {
         String MAIN_ACTION = "blacklinden.com.servicetest.action.main";
         String INIT_ACTION = "blacklinden.com.servicetest.action.init";
         String PREV_ACTION = "blacklinden.com.servicetest.action.prev";
         String PLAY_ACTION = "blacklinden.com.servicetest.action.play";
         String NEXT_ACTION = "blacklinden.com.servicetest.action.next";
         String STARTFOREGROUND_ACTION = "blacklinden.com.servicetest.action.startforeground";
         String STOPFOREGROUND_ACTION = "blacklinden.com.servicetest.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }
}